package com.neu.dreambuilder.controller.common;

import com.neu.dreambuilder.common.annotation.LimitRequest;
import com.neu.dreambuilder.common.utils.BaseContext;
import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.service.common.SignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@Api(tags = "登录登出相关接口")
public class SignController {

    private final SignService signService;

    @Autowired
    public SignController(SignService signService) {
        this.signService = signService;
    }

    @PostMapping("/signin")
    @PreAuthorize("isAnonymous()")
    @LimitRequest(timesInAUnit = 60, unit = TimeUnit.MINUTES)
    //public Result<String> login(String username, String password, @ApiParam("1表示donor，2表示kid，3表示volunteer") int type) {
    public Result<String> login(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        String type = map.get("type");
        String jwt = signService.login(username, password, Integer.parseInt(type));
        return jwt == null
                ? Result.error("账号或密码错误")
                : Result.success(jwt);
    }

    @PostMapping("/signout")
    @PreAuthorize("hasAuthority('LOGIN')")
    public Result<Object> logout() {
        signService.logout(BaseContext.getCurrentIUserDetails());
        return Result.success();
    }
}
