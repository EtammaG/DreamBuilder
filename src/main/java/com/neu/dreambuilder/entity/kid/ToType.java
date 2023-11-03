package com.neu.dreambuilder.entity.kid;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 孩子和类别关系
 * </p>
 *
 * @author 作者
 * @since 2023-11-03
 */
@Getter
@Setter
@TableName("kid_to_type")
@ApiModel(value = "ToType对象", description = "孩子和类别关系")
public class ToType {

    @ApiModelProperty("唯一ID")
    private Long kidId;

    @ApiModelProperty("类别ID")
    private Long typeId;


}
