package com.neu.dreambuilder.dto.volunteer;

import com.neu.dreambuilder.entity.kid.Mission;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

/**
 * 志愿者任务
 */
@Getter
public class VolunteerMissonDto {

    @ApiModelProperty("该任务的标题")
    private String title;
    @ApiModelProperty("该任务提交总数")
    private int totalsubmit;
    @ApiModelProperty("该任务未批改数")
    private int waitingcheck;



}
