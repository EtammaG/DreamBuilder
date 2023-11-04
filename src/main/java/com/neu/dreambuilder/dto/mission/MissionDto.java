package com.neu.dreambuilder.dto.mission;

import com.neu.dreambuilder.entity.kid.Mission;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "MissionDto", description = "任务详情")
public class MissionDto extends Mission{

    @ApiModelProperty("进行人数")
    private int totalNum;

    @ApiModelProperty("完成人数")
    private int doneNum;
}
