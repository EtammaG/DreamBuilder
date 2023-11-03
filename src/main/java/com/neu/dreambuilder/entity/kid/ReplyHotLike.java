package com.neu.dreambuilder.entity.kid;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 热门提交成果的点赞
 * </p>
 *
 * @author 作者
 * @since 2023-11-03
 */
@Getter
@Setter
@TableName("kid_reply_hot_like")
@ApiModel(value = "ReplyHotLike对象", description = "热门提交成果的点赞")
public class ReplyHotLike {

    @ApiModelProperty("热门提交成果的热门ID")
    private Long hotId;

    @ApiModelProperty("点赞孩子的ID")
    private Integer kidId;


}
