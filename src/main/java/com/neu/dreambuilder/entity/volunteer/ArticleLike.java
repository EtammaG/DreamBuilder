package com.neu.dreambuilder.entity.volunteer;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 志愿者收藏的文章
 * </p>
 *
 * @author 作者
 * @since 2023-11-03
 */
@Getter
@Setter
@TableName("volun_article_like")
@AllArgsConstructor
@ApiModel(value = "ArticleLike对象", description = "志愿者收藏的文章")
public class ArticleLike {

    @ApiModelProperty("收藏者ID")
    private Long volunId;

    @ApiModelProperty("文章ID")
    private Long articleId;


}
