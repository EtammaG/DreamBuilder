package com.neu.dreambuilder.controller.donor;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.dreambuilder.dto.PageExample;
import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.dto.donor.DonationStaDto;
import com.neu.dreambuilder.dto.donor.KidDonationDto;
import com.neu.dreambuilder.dto.donor.KidThingDto;
import com.neu.dreambuilder.dto.donor.ProjectDonationDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/donor/donation")
@PreAuthorize("hasAuthority('DONOR')")
@Api(tags = "捐赠者端捐助相关接口")
public class DonorDonationController {

    @PostMapping("/money")
    @ApiOperation("捐助善款")
    public Result<Object> money(@ApiParam(value = "孩子ID") String kidId, @ApiParam(value = "金额") int amount) {
        return Result.success();
    }

    @PostMapping("/thing")
    @ApiOperation("捐助物品")
    public Result<Object> thing(@ApiParam(value = "孩子ID") String kidId, @ApiParam(value = "物品名称") int name) {
        return Result.success();
    }

    @PostMapping("/project")
    @ApiOperation("对项目捐助")
    public Result<Object> project(@ApiParam(value = "项目ID") String projectId, @ApiParam(value = "金额") int amount) {
        return Result.success();
    }

    @GetMapping("/statistic")
    @ApiOperation("获得捐助统计信息")
    public Result<DonationStaDto> statistic() {
        return Result.success();
    }

    @PostMapping("/money/list")
    @ApiOperation("获取善款捐赠记录")
    public Result<PageInfo<KidDonationDto>> getMoney(@RequestBody PageExample<Object> pageExample) {
        PageHelper.startPage(pageExample.getPageNum(), pageExample.getPageSize());
        return Result.success();
    }

    @PostMapping("/thing/list")
    @ApiOperation("获取物品捐赠记录")
    public Result<PageInfo<KidThingDto>> getThing(@RequestBody PageExample<Object> pageExample) {
        PageHelper.startPage(pageExample.getPageNum(), pageExample.getPageSize());
        return Result.success();
    }

    @PostMapping("/project/list")
    @ApiOperation("获取对项目捐赠记录")
    public Result<PageInfo<ProjectDonationDto>> getProject(@RequestBody PageExample<Object> pageExample) {
        PageHelper.startPage(pageExample.getPageNum(), pageExample.getPageSize());
        return Result.success();
    }

}
