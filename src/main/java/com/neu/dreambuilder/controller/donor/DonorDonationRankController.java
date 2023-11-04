package com.neu.dreambuilder.controller.donor;

import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.dto.donor.DonationStaDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/donor/donation/rank")
@PreAuthorize("hasAuthority('DONOR')")
@Api(tags = "捐赠者端捐助排行榜相关接口")
public class DonorDonationRankController {

    @GetMapping("/all")
    @ApiOperation("总排行")
    public Result<List<DonationStaDto>> all() {
        return Result.success();
    }

    @GetMapping("/month")
    @ApiOperation("月排行")
    public Result<List<DonationStaDto>> month() {
        return Result.success();
    }

    @GetMapping("/week")
    @ApiOperation("周排行")
    public Result<List<DonationStaDto>> week() {
        return Result.success();
    }

}
