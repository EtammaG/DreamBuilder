package com.neu.dreambuilder.entity.kid;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 孩子、任务和提交关系
 * </p>
 *
 * @author 作者
 * @since 2023-11-03
 */
@Getter
@Setter
@AllArgsConstructor
@TableName("kid_to_mission")
@ApiModel(value = "ToMission对象", description = "孩子、任务和提交关系")
public class ToMission {

    @ApiModelProperty("孩子ID")
    private Long kidId;

    @ApiModelProperty("任务ID")
    private Long missionId;

    @ApiModelProperty("提交ID，为空说明未提交")
    private Long replyId;


}
