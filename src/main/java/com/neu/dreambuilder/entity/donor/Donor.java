package com.neu.dreambuilder.entity.donor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 捐赠者
 * </p>
 *
 * @author 作者
 * @since 2023-11-03
 */
@Getter
@Setter
@ApiModel(value = "Donor对象", description = "捐赠者")
public class Donor {

    @ApiModelProperty("唯一ID")
    private Long id;

    @ApiModelProperty("昵称")
    private String nickname;


}
