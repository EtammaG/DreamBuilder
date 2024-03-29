package com.neu.dreambuilder.entity.kid;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 孩子的学校信息
 * </p>
 *
 * @author 作者
 * @since 2023-11-03
 */
@Getter
@Setter
@TableName("kid_school")
@ApiModel(value = "School对象", description = "孩子的学校信息")
public class School {

    @ApiModelProperty("唯一ID")
    private Long id;

    @ApiModelProperty("学校名称")
    private String name;


}
