package com.neu.dreambuilder.controller.donor;

import com.neu.dreambuilder.dto.DesDto;
import com.neu.dreambuilder.dto.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/donor/hot")
@PreAuthorize("hasAuthority('DONOR')")
@Api(tags = "捐赠者端推送热门消息相关接口")
public class DonorHotController {

    @GetMapping("/des")
    @ApiOperation("获得系统的宣传信息")
    public Result<DesDto> get() {
        return null;
    }
}
