package com.neu.dreambuilder.controller.kid;

import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.dto.kid.KidMalDto;
import com.neu.dreambuilder.dto.kid.KidMeeDto;
import com.neu.dreambuilder.dto.kid.KidMisDto;
import com.neu.dreambuilder.dto.kid.KidRecDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kid/info")
@PreAuthorize("hasAuthority('KID')")
@Api(tags = "孩子端孩子信息相关接口")
public class KidInfoController {

    @GetMapping("mission")
    @ApiOperation("获取孩子任务相关的信息")
    public Result<KidMisDto> mission() {
        return Result.success();
    }

    @GetMapping("mall")
    @ApiOperation("获取孩子商城相关的信息")
    public Result<KidMalDto> mall() {
        return Result.success();
    }

    @GetMapping("me")
    @ApiOperation("获取孩子我的相关的信息")
    public Result<KidMeeDto> me() {
        return Result.success();
    }

    @GetMapping("recent")
    @ApiOperation("获取孩子最近信息")
    public Result<KidRecDto> recent() {
        return Result.success();
    }

}
