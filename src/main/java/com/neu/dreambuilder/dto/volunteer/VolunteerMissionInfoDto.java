package com.neu.dreambuilder.dto.volunteer;

import com.neu.dreambuilder.entity.kid.Mission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

/**
 * 单独一个任务的详细信息，以及该任务的完成人数和未完成人数
 */
@Api("单独一个任务的详细信息，以及该任务的完成人数和未完成人数")
public class VolunteerMissionInfoDto extends Mission {
    @ApiModelProperty("该任务已完成的人数")
    private int done;
    @ApiModelProperty("该任务未完成的人数")
    private int waiting;
}
