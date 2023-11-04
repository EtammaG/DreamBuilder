package com.neu.dreambuilder.dto.donor;

import com.neu.dreambuilder.entity.donor.ProjectDonation;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "ProjectDonationDto", description = "捐赠者捐助项目善款记录")
public class ProjectDonationDto extends ProjectDonation {

    @ApiModelProperty("项目名称")
    private String projectName;

    @ApiModelProperty("项目图片")
    private String projectPic;
}
