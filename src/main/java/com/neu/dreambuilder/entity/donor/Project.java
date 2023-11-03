package com.neu.dreambuilder.entity.donor;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 爱心项目
 * </p>
 *
 * @author 作者
 * @since 2023-11-03
 */
@Getter
@Setter
@TableName("donor_project")
@ApiModel(value = "Project对象", description = "爱心项目")
public class Project {

    @ApiModelProperty("项目ID")
    private Long id;

    @ApiModelProperty("项目名称")
    private String name;

    @ApiModelProperty("项目描述")
    private String description;

    @ApiModelProperty("项目照片地址")
    private String pic;

    @ApiModelProperty("项目实施地")
    private String location;


}
