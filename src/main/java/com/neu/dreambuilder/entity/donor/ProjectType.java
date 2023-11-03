package com.neu.dreambuilder.entity.donor;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 项目类别
 * </p>
 *
 * @author 作者
 * @since 2023-11-03
 */
@Getter
@Setter
@TableName("donor_project_type")
@ApiModel(value = "ProjectType对象", description = "项目类别")
public class ProjectType {

    private Long id;

    @ApiModelProperty("名称")
    private String name;


}
