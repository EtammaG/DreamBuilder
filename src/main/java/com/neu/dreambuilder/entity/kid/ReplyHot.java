package com.neu.dreambuilder.entity.kid;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 热门提交成果
 * </p>
 *
 * @author 作者
 * @since 2023-11-03
 */
@Getter
@Setter
@TableName("kid_reply_hot")
@ApiModel(value = "ReplyHot对象", description = "热门提交成果")
public class ReplyHot {

    @ApiModelProperty("唯一ID")
    private Long id;

    @ApiModelProperty("提交成果ID")
    private Long replyId;


}
