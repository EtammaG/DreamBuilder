package com.neu.dreambuilder.entity.donor;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 捐赠者捐助孩子善款记录
 * </p>
 *
 * @author 作者
 * @since 2023-11-03
 */
@Getter
@Setter
@TableName("donor_kid_donation")
@ApiModel(value = "KidDonation对象", description = "捐赠者捐助孩子善款记录")
public class KidDonation {

    @ApiModelProperty("捐赠者ID")
    private Long donorId;

    @ApiModelProperty("孩子ID")
    private Long kidId;

    @ApiModelProperty("额度")
    private Integer amount;

    @ApiModelProperty("时间")
    private LocalDateTime time;


}
