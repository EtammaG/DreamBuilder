package com.neu.dreambuilder.entity.volunteer;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 志愿者与任务关系
 * </p>
 *
 * @author 作者
 * @since 2023-11-07
 */
@Getter
@Setter
@TableName("volun_to_mission")
@ApiModel(value = "VolunToMission对象", description = "志愿者与任务关系")
public class VolunToMission {

    @ApiModelProperty("志愿者ID")
    private Long volunId;

    @ApiModelProperty("任务ID")
    private Long missionId;


}
