package com.neu.dreambuilder.service.volunteer.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.neu.dreambuilder.common.utils.BaseContext;
import com.neu.dreambuilder.dto.CommentDto;
import com.neu.dreambuilder.dto.PageExample;
import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.dto.volunteer.ArticleDto;
import com.neu.dreambuilder.entity.volunteer.Article;
import com.neu.dreambuilder.entity.volunteer.ArticleComment;
import com.neu.dreambuilder.entity.volunteer.ArticleLike;
import com.neu.dreambuilder.entity.volunteer.Volunteer;
import com.neu.dreambuilder.mapper.volunteer.*;
import com.neu.dreambuilder.service.volunteer.VolunteerArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.xml.stream.events.Comment;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

import static com.neu.dreambuilder.dto.Result.success;

@Service
public class VolunteerArticleServiceImpl implements VolunteerArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
     private ArticleCommentMapper commentMapper;

    @Resource
    private VolunteerMapper volunteerMapper;

    @Resource
    private ArticleLikeMapper articleLikeMapper;

    @Resource
    private VolunteerStatisticMapper volunteerStatisticMapper;



    @Override
    public Result<ArticleDto> getRandomArticle() {

        LambdaQueryWrapper<Article> articleQuery = new LambdaQueryWrapper<>();
        articleQuery.orderByDesc(Article::getArticleTime);

        List<Article> articles = articleMapper.selectList(articleQuery);
        ArticleDto articleDto = new ArticleDto();
        BeanUtils.copyProperties(articles.get(0),articleDto);

        Long articleId = articleDto.getId();
        LambdaQueryWrapper<ArticleComment> commentQuery = new LambdaQueryWrapper<>();
        commentQuery.eq(ArticleComment::getArticleId,articleId);
        Integer count = commentMapper.selectCount(commentQuery);
        articleDto.setAmount(count);


        return Result.success(articleDto);
    }

    @Override
    public List<ArticleDto> getAllArticle(String title) {


//        LambdaQueryWrapper<Article> articleQuery = new LambdaQueryWrapper<>();
//        articleQuery.orderByDesc(Article::getArticleTime);
//
//        if (title!=null||title!=""){
//               articleQuery.like(Article::getTitle,"%"+title+"%");
//        }
//
//        List<Article> articles = articleMapper.selectList(articleQuery);
//        List<ArticleDto> articleDtos = articles.stream().map((article -> {
//            ArticleDto articleDto = new ArticleDto();
//            BeanUtils.copyProperties(article, articleDto);
//            Long articleId = articleDto.getId();
//            LambdaQueryWrapper<ArticleComment> commentQuery = new LambdaQueryWrapper<>();
//            commentQuery.eq(ArticleComment::getArticleId, articleId);
//            Integer count = commentMapper.selectCount(commentQuery);
//            articleDto.setAmount(count);
//            return articleDto;
//        })).collect(Collectors.toList());
        if (title==null){
            title = "";
        }
        List<ArticleDto> articleDtos = volunteerStatisticMapper.allArticle(title);
        return articleDtos;
    }

    @Override
    public Result<Article> getArticleDetails(long id) {
        LambdaQueryWrapper<Article> articleQuery = new LambdaQueryWrapper<>();
        articleQuery.eq(Article::getId,id);
        Article article = articleMapper.selectOne(articleQuery);

        return Result.success(article);
    }

    @Override
    public Result<List<CommentDto>> getArticleComments(long id) {
//        LambdaQueryWrapper<ArticleComment> commentQuery = new LambdaQueryWrapper<>();
//        commentQuery.eq(ArticleComment::getArticleId,id);
//        commentQuery.orderByDesc(ArticleComment::getTime);
//
//        List<ArticleComment> articleComments = commentMapper.selectList(commentQuery);
//        List<CommentDto> commentDtos = articleComments.stream().map(articleComment -> {
//            Long volunId = articleComment.getVolunId();
//
//            LambdaQueryWrapper<Volunteer> volunQuery = new LambdaQueryWrapper<>();
//            volunQuery.eq(Volunteer::getId, volunId);
//            Volunteer volunteer = volunteerMapper.selectOne(volunQuery);
//            CommentDto commentDto = new CommentDto();
//            commentDto.setName(volunteer.getName());
//            commentDto.setPhoto(volunteer.getPhoto());
//            commentDto.setTime(articleComment.getTime());
//            commentDto.setContent(articleComment.getContent());
//            return commentDto;
//
//        }).collect(Collectors.toList());

        return Result.success( volunteerStatisticMapper.articleComment(id));
    }

    @Override
    public List<ArticleDto> getArticleColleted(Long id) {

//        LambdaQueryWrapper<ArticleLike> articleLikeQuery = new LambdaQueryWrapper<>();
//        articleLikeQuery.eq(ArticleLike::getVolunId,id);
//        List<ArticleLike> articleLikes = articleLikeMapper.selectList(articleLikeQuery);
//
//        List<ArticleDto> articleDtos = articleLikes.stream().map(articleLike -> {
//            LambdaQueryWrapper<Article> articleQuery = new LambdaQueryWrapper<>();
//            articleQuery.eq(Article::getId, articleLike.getArticleId());
//            Article article = articleMapper.selectOne(articleQuery);
//
//            ArticleDto articleDto = new ArticleDto();
//            BeanUtils.copyProperties(article, articleDto);
//            LambdaQueryWrapper<ArticleComment> commentQuery = new LambdaQueryWrapper<>();
//            commentQuery.eq(ArticleComment::getArticleId, articleLike.getArticleId());
//            Integer count = commentMapper.selectCount(commentQuery);
//            articleDto.setAmount(count);
//
//            return articleDto;
//
//        }).collect(Collectors.toList());


        return volunteerStatisticMapper.allArticleLike(id);
    }

    @Override
    public Boolean getIfLove(String articleId) {
        long artId = Long.parseLong(articleId);
        Long volunId = BaseContext.getCurrentIUserDetails().getId();
        Long ifLove = volunteerStatisticMapper.getIfLove(volunId, artId);
        if (ifLove == 0L) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Integer getLoveCount(String articleId) {
        long artId = Long.parseLong(articleId);
        Integer loveCount = volunteerStatisticMapper.loveCount(artId).intValue();
        return loveCount;
    }

    @Override
    public void putArticeLove(String articleId) {
        long artId = Long.parseLong(articleId);
        Long volunId = BaseContext.getCurrentIUserDetails().getId();
        volunteerStatisticMapper.inputLove(volunId,artId);
    }

    @Override
    public void deleteArticleLove(Long articleId) {
        Long volunId = BaseContext.getCurrentIUserDetails().getId();
        volunteerStatisticMapper.deleteLove(volunId,articleId);
    }

    @Override
    public void putArticleLike(Long articleId) {
        Long volunId = BaseContext.getCurrentIUserDetails().getId();

        articleLikeMapper.insert(new ArticleLike(volunId,articleId));

    }


}
