package com.neu.dreambuilder.controller.common;

import com.neu.dreambuilder.common.annotation.LimitRequest;
import com.neu.dreambuilder.common.utils.BaseContext;
import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.service.common.SignService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
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

//    @PostMapping("/login")
//    @PreAuthorize("isAnonymous()")
//    @LimitRequest(timesInAUnit = 20, unit = TimeUnit.MINUTES)
//    public Result<String> login(String username, String password, @ApiParam("1表示donor，2表示kid，3表示volunteer") int type) {
//        String jwt = signService.login(username, password, type);
//        return jwt == null
//                ? Result.error("账号或密码错误")
//                : Result.success(jwt);
//    }
    @PostMapping("/login")
    @PreAuthorize("isAnonymous()")
    @LimitRequest(timesInAUnit = 20, unit = TimeUnit.MINUTES)
    public Result<String> login(@RequestBody Map<String,Object> map) {
        String username = (String)map.get("username");
        String password = (String) map.get("password");
        Integer type = (Integer)map.get("type");

        String jwt = signService.login(username, password, type);
        return jwt == null
                ? Result.error("账号或密码错误")
                : Result.success(jwt);
    }

    @PostMapping("/logout")
    @PreAuthorize("hasAuthority('LOGIN')")
    public Result<Object> logout() {
        signService.logout(BaseContext.getCurrentIUserDetails());
        return Result.success();
    }
}
