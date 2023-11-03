package com.neu.dreambuilder.dto.volunteer;

import com.neu.dreambuilder.entity.volunteer.Article;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
@Api("文章的相关信息")
public class VolunteerArticleDto extends Article {
    @ApiModelProperty("文章的评论数")
    private int amount;

}
