package com.neu.dreambuilder.controller.common;

import com.neu.dreambuilder.common.annotation.LimitRequest;
import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.service.common.SignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController

@Api(tags = "登录登出相关接口")

public class SignController {

    private final SignService signService;

    @Autowired
    public SignController(SignService signService) {
        this.signService = signService;
    }

    @PostMapping("/login")
    @PreAuthorize("isAnonymous()")
    @LimitRequest(count = 20)
    public Result<String> login(String username, String password, @ApiParam("1表示donor，2表示kid，3表示volunteer") int type) {
        String jwt = signService.login(username, password, type);
        return jwt == null
                ? Result.error("账号或密码错误")
                : Result.success(jwt);
    }

    @PostMapping("/logout")
    @PreAuthorize("hasAuthority('LOGIN')")
    public Result<Object> logout(HttpServletRequest request) {
        signService.logout();
        return Result.success();
    }
}
