package com.neu.dreambuilder.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 志愿者用户
 * </p>
 *
 * @author 作者
 * @since 2023-11-05
 */
@Getter
@Setter
@TableName("user_volunteer")
@ApiModel(value = "UserVolunteer对象", description = "志愿者用户")
public class UserVolunteer {

    private Long volunteerId;

    private String username;

    private String password;


}
