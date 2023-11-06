package com.neu.dreambuilder.service.volunteer.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.dto.volunteer.ArticleDto;
import com.neu.dreambuilder.entity.volunteer.Article;
import com.neu.dreambuilder.entity.volunteer.ArticleComment;
import com.neu.dreambuilder.mapper.volunteer.ArticleCommentMapper;
import com.neu.dreambuilder.mapper.volunteer.ArticleMapper;
import com.neu.dreambuilder.service.volunteer.VolunteerArticleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.neu.dreambuilder.dto.Result.success;

@Service
public class VolunteerArticleServiceImpl implements VolunteerArticleService {

    @Resource
    private ArticleMapper articleMapper;

    @Resource
     private ArticleCommentMapper commentMapper;

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


}
