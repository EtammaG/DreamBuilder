package com.neu.dreambuilder.entity.kid;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "AwardExample", description = "Award搜索样式")
public class AwardExample extends Award{

    @ApiModelProperty("类别ID")
    private Long typeId;

}
