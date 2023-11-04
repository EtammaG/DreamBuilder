package com.neu.dreambuilder.dto.kid;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 志愿者端中每一个任务的未被批改的孩子信息
 */
@Getter
@Setter
@ApiModel(value = "KidVieDto", description = "志愿者端中每一个任务的未被批改的孩子信息")
public class KidVieDto {
    @ApiModelProperty("孩子的照片")
    private String kidPic;
    @ApiModelProperty("孩子的名称")
    private String kidName;

}
