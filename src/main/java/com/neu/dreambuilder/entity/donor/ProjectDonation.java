package com.neu.dreambuilder.entity.donor;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * 捐赠者捐助项目善款记录
 * </p>
 *
 * @author 作者
 * @since 2023-11-03
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@TableName("donor_project_donation")
@ApiModel(value = "ProjectDonation对象", description = "捐赠者捐助项目善款记录")
public class ProjectDonation {

    @ApiModelProperty("项目ID")
    private Long projectId;

    @ApiModelProperty("捐助者ID")
    private Long donorId;

    @ApiModelProperty("捐助项目的金额")
    private Integer amount;

    @ApiModelProperty("捐助的时间")
    private LocalDateTime time;


}
