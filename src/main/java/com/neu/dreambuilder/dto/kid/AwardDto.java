package com.neu.dreambuilder.dto.kid;

import com.neu.dreambuilder.entity.kid.Award;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "AwardDto", description = "奖品")
public class AwardDto extends Award {

    @ApiModelProperty("是否被已经被收藏了")
    private boolean liked;

}
