package com.neu.dreambuilder.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 捐赠者用户
 * </p>
 *
 * @author 作者
 * @since 2023-11-05
 */
@Getter
@Setter
@TableName("user_donor")
@ApiModel(value = "UserDonor对象", description = "捐赠者用户")
public class UserDonor {

    private Long donorId;

    private String username;

    private String password;


}
