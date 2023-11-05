package com.neu.dreambuilder.entity.volunteer;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 志愿者、孩子的对应关系
 * </p>
 *
 * @author 作者
 * @since 2023-11-05
 */
@Getter
@Setter
@TableName("volun_to_kid")
@ApiModel(value = "VolunToKid对象", description = "志愿者、孩子的对应关系")
public class VolunToKid {

    @ApiModelProperty("志愿者ID")
    private Long volunId;

    @ApiModelProperty("孩子ID")
    private Long kidId;


}
