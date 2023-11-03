package com.neu.dreambuilder.entity.kid;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 奖品和奖品类别关系
 * </p>
 *
 * @author 作者
 * @since 2023-11-03
 */
@Getter
@Setter
@TableName("kid_award_to_type")
@ApiModel(value = "AwardToType对象", description = "奖品和奖品类别关系")
public class AwardToType {

    @ApiModelProperty("奖品ID")
    private Long awardId;

    @ApiModelProperty("类别ID")
    private Long typeId;


}
