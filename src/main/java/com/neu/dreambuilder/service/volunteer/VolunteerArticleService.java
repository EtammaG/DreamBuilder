package com.neu.dreambuilder.service.volunteer;

import com.neu.dreambuilder.dto.CommentDto;
import com.neu.dreambuilder.dto.PageExample;
import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.dto.volunteer.ArticleDto;
import com.neu.dreambuilder.entity.volunteer.Article;

import java.util.List;

public interface VolunteerArticleService {
    Result<ArticleDto> getRandomArticle();

    List<ArticleDto> getAllArticle(String title);


    Result<Article> getArticleDetails(long id);

    Result<List<CommentDto>> getArticleComments(long id);

    List<ArticleDto> getArticleColleted(Long id);

    Boolean getIfLove(String articleId);

    Integer getLoveCount(String articleId);

    void putArticeLove(String articleId);

    void deleteArticleLove(Long articleId);
}
