package com.neu.dreambuilder.dto.kid;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "KidRecentDto", description = "孩子近况的信息")
public class KidRecDto {

    @ApiModelProperty("孩子姓名")
    private String name;

    @ApiModelProperty("孩子照片路径")
    private String photo;

    @ApiModelProperty("孩子近况")
    public String recent;

}
