package com.neu.dreambuilder.dto.donor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@ApiModel(value = "DonationStaDto", description = "捐助统计信息")
public class DonationStaDto {

    @ApiModelProperty("捐赠者昵称")
    private String nickname;

    @ApiModelProperty("捐助次数")
    private int times;

    @ApiModelProperty("捐赠金额")
    private int amount;

    @ApiModelProperty("排行")
    private int rank;
}
