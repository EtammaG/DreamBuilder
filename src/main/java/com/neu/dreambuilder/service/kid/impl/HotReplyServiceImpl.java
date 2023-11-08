package com.neu.dreambuilder.service.kid.impl;

import com.neu.dreambuilder.dto.CommentDto;
import com.neu.dreambuilder.dto.kid.HotReplyDto;
import com.neu.dreambuilder.entity.kid.ReplyHotComment;
import com.neu.dreambuilder.entity.kid.ReplyHotLike;
import com.neu.dreambuilder.mapper.kid.HotReplyMapper;
import com.neu.dreambuilder.mapper.kid.ReplyHotCommentMapper;
import com.neu.dreambuilder.mapper.kid.ReplyHotLikeMapper;
import com.neu.dreambuilder.service.kid.HotReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class HotReplyServiceImpl implements HotReplyService {

    private final HotReplyMapper hotReplyMapper;

    private final ReplyHotCommentMapper replyHotCommentMapper;
    private final ReplyHotLikeMapper replyHotLikeMapper;

    @Autowired
    public HotReplyServiceImpl(HotReplyMapper hotReplyMapper, ReplyHotCommentMapper replyHotCommentMapper, ReplyHotLikeMapper replyHotLikeMapper) {
        this.hotReplyMapper = hotReplyMapper;
        this.replyHotCommentMapper = replyHotCommentMapper;
        this.replyHotLikeMapper = replyHotLikeMapper;
    }

    @Override
    public List<HotReplyDto> getAll(Long id) {
        return hotReplyMapper.selectAll(id);
    }

    @Override
    public void like(Long id, long hotId) {
        replyHotLikeMapper.insert(new ReplyHotLike(hotId, id));
    }

    @Override
    public List<CommentDto> getComment(String hotId) {
        return hotReplyMapper.selectCommentsByHotId(hotId);
    }

    @Override
    public void addComment(Long id, long hotId, String comment) {
        replyHotCommentMapper.insert(new ReplyHotComment(hotId, id, comment, LocalDateTime.now()));
    }
}
