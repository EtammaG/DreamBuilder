package com.neu.dreambuilder.entity.volunteer;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 文章点赞
 * </p>
 *
 * @author 作者
 * @since 2023-11-09
 */
@Getter
@Setter
@TableName("volun_article_love")
@ApiModel(value = "ArticleLove对象", description = "文章点赞")
public class ArticleLove {

    @ApiModelProperty("志愿者id")
    private Long volunId;

    @ApiModelProperty("文章id")
    private Long articleId;

    @ApiModelProperty("点赞id")
    private Long loveId;


}
