package com.neu.dreambuilder.entity.kid;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 对任务的提交
 * </p>
 *
 * @author 作者
 * @since 2023-11-03
 */
@Getter
@Setter
@TableName("kid_reply")
@ApiModel(value = "Reply对象", description = "对任务的提交")
public class Reply {

    @ApiModelProperty("唯一ID")
    private Long id;

    @ApiModelProperty("提交媒体文件的路径")
    private String media;

    @ApiModelProperty("对提交的评分")
    private Integer score;

    @ApiModelProperty("对提交的评语")
    private Integer comment;


}
