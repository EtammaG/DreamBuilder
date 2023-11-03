package com.neu.dreambuilder.entity.kid;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 被捐助的孩子
 * </p>
 *
 * @author 作者
 * @since 2023-11-03
 */
@Getter
@Setter
@ApiModel(value = "Kid对象", description = "被捐助的孩子")
public class Kid {

    @ApiModelProperty("唯一ID")
    private Long id;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("年龄")
    private Integer age;

    @ApiModelProperty("孩子照片路径")
    private String photo;

    @ApiModelProperty("学校ID")
    private Long schoolId;

    @ApiModelProperty("年级")
    private Integer grade;

    @ApiModelProperty("住址")
    private String address;

    @ApiModelProperty("简介")
    private String description;

    @ApiModelProperty("总点数（学习点数）")
    private Integer totalPoint;

    @ApiModelProperty("商城点数")
    private Integer mallPoint;

    @ApiModelProperty("一个周前的total_point")
    private Integer pointWeekAgo;

    @ApiModelProperty("一周前的total_point排行")
    private Integer rankWeekAgo;

    private Integer weekrankWeekAgo;


}
