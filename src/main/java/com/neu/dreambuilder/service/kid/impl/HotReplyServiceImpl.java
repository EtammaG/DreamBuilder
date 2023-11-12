package com.neu.dreambuilder.service.kid.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.neu.dreambuilder.bo.Comment;
import com.neu.dreambuilder.dto.CommentDto;
import com.neu.dreambuilder.dto.kid.HotReplyDto;
import com.neu.dreambuilder.entity.kid.Kid;
import com.neu.dreambuilder.entity.kid.ReplyHotLike;
import com.neu.dreambuilder.exception.bean.CustomException;
import com.neu.dreambuilder.mapper.kid.HotReplyMapper;
import com.neu.dreambuilder.mapper.kid.KidMapper;
import com.neu.dreambuilder.mapper.kid.ReplyHotCommentMapper;
import com.neu.dreambuilder.mapper.kid.ReplyHotLikeMapper;
import com.neu.dreambuilder.service.kid.HotReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class HotReplyServiceImpl implements HotReplyService {

    private final String redisPrefix;
    private final String allKey;
    private final String sizeKey;
    private final String likeKeyPrefix;
    private final String commentKeyPrefix;

    private final HotReplyMapper hotReplyMapper;

    private final ReplyHotCommentMapper replyHotCommentMapper;
    private final ReplyHotLikeMapper replyHotLikeMapper;

    private final KidMapper kidMapper;

    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public HotReplyServiceImpl(
            @Value("${dream-builder.redis.prefix.hot.reply}") String redisPrefix,
            HotReplyMapper hotReplyMapper, ReplyHotCommentMapper replyHotCommentMapper, ReplyHotLikeMapper replyHotLikeMapper, KidMapper kidMapper, StringRedisTemplate stringRedisTemplate) {
        this.redisPrefix = redisPrefix;
        this.hotReplyMapper = hotReplyMapper;
        this.replyHotCommentMapper = replyHotCommentMapper;
        this.replyHotLikeMapper = replyHotLikeMapper;
        this.kidMapper = kidMapper;
        this.stringRedisTemplate = stringRedisTemplate;
        this.allKey = redisPrefix + "all";
        this.sizeKey = redisPrefix + "size";
        this.likeKeyPrefix = redisPrefix + "like:";
        this.commentKeyPrefix = redisPrefix + "comment:";
    }

    @Override
    public List<HotReplyDto> getAll(Long id) {
        List<HotReplyDto> hots = JSON.parseArray(stringRedisTemplate.opsForValue().get(allKey), HotReplyDto.class);
        if (hots == null || hots.isEmpty()) return new LinkedList<>();
        String kidId = id.toString();
        for (HotReplyDto hot : hots) {
            hot.setLiked(stringRedisTemplate.opsForSet().isMember(likeKeyPrefix + hot.getHotId(), kidId));
            Long size = stringRedisTemplate.opsForSet().size(likeKeyPrefix + hot.getHotId());
            hot.setLikeNum(size == null ? 0 : size.intValue());
        }
        return hots;
//        return hotReplyMapper.selectAll(id);
    }

    @Override
    public void like(Long id, long hotId) {
        Long num = stringRedisTemplate.opsForSet().add(likeKeyPrefix + hotId, id.toString());
        if (num == null || num == 0) throw new CustomException("不能重复点赞");
//        String key = redisPrefix + "like:";
//        ReplyHotLike replyHotLike = new ReplyHotLike(hotId, id);
//        if (replyHotLikeMapper.cat(replyHotLike) != null) throw new CustomException("不能重复点赞");
//        replyHotLikeMapper.insert(replyHotLike);
    }

    @Override
    public void unlike(Long id, long hotId) {
        Long num = stringRedisTemplate.opsForSet().remove(likeKeyPrefix + hotId, id.toString());
        if (num == null || num == 0) throw new CustomException("并未点赞");
//        ReplyHotLike replyHotLike = new ReplyHotLike(hotId, id);
//        if(replyHotLikeMapper.cat(replyHotLike) != null) throw new CustomException("不能重复点赞");
//        replyHotLikeMapper.insert(replyHotLike);
    }

    @Override
    public List<CommentDto> getComment(String hotId) {
        String key = commentKeyPrefix + hotId;
        Long size = stringRedisTemplate.opsForList().size(key);
        if (size == null || size == 0) return new LinkedList<>();
        List<String> jsons = stringRedisTemplate.opsForList().range(key, 0, size);
        if (jsons == null || jsons.isEmpty()) return new LinkedList<>();
        return jsons.stream().map((json) -> JSON.parseObject(json, CommentDto.class)).toList();
        // todo redis直接存了CommentDto对象，其中包括孩子的姓名和头像，但应该只存孩子的id，在另一个缓存中存孩子的信息
//        return hotReplyMapper.selectCommentsByHotId(hotId);
    }

    @Override
    public void addComment(Long id, long hotId, String content) {
        Kid kid = kidMapper.selectById(id);
        stringRedisTemplate.opsForList().leftPush(
                commentKeyPrefix + hotId,
                JSON.toJSONString(new CommentDto(content, kid.getName(), kid.getPhoto(), LocalDateTime.now())));
        // todo 其实可以在BaseContext中存下当前用户的基本信息，name、avatar等
//        replyHotCommentMapper.insert(new ReplyHotComment(hotId, id, content, LocalDateTime.now()));
    }

    @Transactional
//    @Scheduled(cron = "* * * * * *")
    public void updateCache() {
        updateCacheHot();
        updateCacheLike();
        updateCacheComment();
    }

    private void updateCacheHot() {
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().add("liked");
        filter.getExcludes().add("likeNum");
        List<HotReplyDto> hots = hotReplyMapper.selectAll();
        stringRedisTemplate.opsForValue().set(
                allKey,
                JSON.toJSONString(hots, filter));
        stringRedisTemplate.opsForValue().set(sizeKey, String.valueOf(hots.size()));
    }

    private void updateCacheLike() {
        List<ReplyHotLike> likes = replyHotLikeMapper.selectAll();
        for (ReplyHotLike like : likes)
            stringRedisTemplate.opsForSet().add(
                    likeKeyPrefix + like.getHotId(),
                    like.getKidId().toString());
    }

    private void updateCacheComment() {
        List<Comment> comments = hotReplyMapper.selectAllComments();
        SimplePropertyPreFilter filter = new SimplePropertyPreFilter();
        filter.getExcludes().add("hotId");
        Set<String> keys = stringRedisTemplate.keys(commentKeyPrefix + "*");
        if (keys != null) stringRedisTemplate.delete(keys);
        for (Comment comment : comments) {
            stringRedisTemplate.opsForList().leftPush(
                    commentKeyPrefix + comment.getHotId(),
                    JSON.toJSONString(comment, filter));
        }
    }

    @Transactional
    //        @Scheduled(cron = "* * * * * *")
    public void updateDB() {
        updateDbHot();
        updateDbLike();
        updateDbComment();
    }

    private void updateDbHot() {

    }

    private void updateDbLike() {
        String size = stringRedisTemplate.opsForValue().get(sizeKey);
        if (size == null) return;
        int s = Integer.parseInt(size);
        List<ReplyHotLike> likes = new ArrayList<>();
        for (int hotId = 1; hotId <= s; hotId++) {
            String key = redisPrefix + hotId;
            Set<String> kidIds = stringRedisTemplate.opsForSet().members(key);
            if (kidIds == null) continue;
            for (String kidId : kidIds)
                likes.add(new ReplyHotLike((long) hotId, Long.parseLong(kidId)));
        }
        replyHotLikeMapper.deleteAll();
        replyHotLikeMapper.insertAll(likes);
    }

    private void updateDbComment() {

    }


}
