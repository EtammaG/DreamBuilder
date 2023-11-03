package com.neu.dreambuilder.dto.volunteer;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * 志愿者端中每一个任务的未被批改的孩子信息
 */
@Api("志愿者端中每一个任务的未被批改的孩子信息")
@Getter
public class VolunteerMissionKidDto {
    @ApiModelProperty("孩子的照片")
    private String kidPic;
    @ApiModelProperty("孩子的名称")
    private String kidName;

}
