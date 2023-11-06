package com.neu.dreambuilder.service.volunteer;

import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.dto.volunteer.ArticleDto;

public interface VolunteerArticleService {
    Result<ArticleDto> getRandomArticle();
}
