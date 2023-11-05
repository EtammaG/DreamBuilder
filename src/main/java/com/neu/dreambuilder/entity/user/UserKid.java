package com.neu.dreambuilder.entity.user;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 孩子用户
 * </p>
 *
 * @author 作者
 * @since 2023-11-05
 */
@Getter
@Setter
@TableName("user_kid")
@ApiModel(value = "UserKid对象", description = "孩子用户")
public class UserKid {

    private Long kidId;

    private String username;

    private String password;


}
