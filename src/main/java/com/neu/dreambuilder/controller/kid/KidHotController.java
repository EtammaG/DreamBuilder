package com.neu.dreambuilder.controller.kid;

import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.dto.kid.KidRecDto;
import com.neu.dreambuilder.service.kid.KidInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/kid/hot")
@PreAuthorize("hasAuthority('KID')")
@Api(tags = "孩子端推送热门消息相关接口")
public class KidHotController {

    private final KidInfoService kidInfoService;

    @Autowired
    public KidHotController(KidInfoService kidInfoService) {
        this.kidInfoService = kidInfoService;
    }

    @GetMapping("/recent")
    @ApiOperation("获取某些孩子近况列表")
    public Result<List<KidRecDto>> get() {
        return Result.success(kidInfoService.getRandomRecs());
    }

}
