package com.neu.dreambuilder.entity.donor;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel(value = "ProjectExample", description = "Project搜索样式")
public class ProjectExample extends Project{
    @ApiModelProperty("类别ID")
    private Long typeId;
}
