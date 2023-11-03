package com.neu.dreambuilder.entity.volunteer;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 文章的评论
 * </p>
 *
 * @author 作者
 * @since 2023-11-03
 */
@Getter
@Setter
@TableName("volun_article_comment")
@ApiModel(value = "ArticleComment对象", description = "文章的评论")
public class ArticleComment {

    @ApiModelProperty("文章ID")
    private Long articleId;

    @ApiModelProperty("评论发布者ID")
    private Long volunId;

    @ApiModelProperty("评论内容")
    private String content;

    @ApiModelProperty("评论时间")
    private LocalDateTime time;


}
