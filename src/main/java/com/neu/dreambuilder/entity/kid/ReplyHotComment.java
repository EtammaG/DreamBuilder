package com.neu.dreambuilder.entity.kid;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * <p>
 * 热门提交成果的评论
 * </p>
 *
 * @author 作者
 * @since 2023-11-03
 */
@Getter
@Setter
@AllArgsConstructor
@TableName("kid_reply_hot_comment")
@ApiModel(value = "ReplyHotComment对象", description = "热门提交成果的评论")
public class ReplyHotComment {

    @ApiModelProperty("提交成果的热门ID")
    private Long hotId;

    @ApiModelProperty("发布评论孩子的ID")
    private Long kidId;

    @ApiModelProperty("评论的内容")
    private String content;

    @ApiModelProperty("评论时间")
    private LocalDateTime time;


}
