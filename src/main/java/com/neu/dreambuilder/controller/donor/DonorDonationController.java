package com.neu.dreambuilder.controller.donor;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.dreambuilder.common.utils.BaseContext;
import com.neu.dreambuilder.dto.PageExample;
import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.dto.donor.DonationStaDto;
import com.neu.dreambuilder.dto.donor.KidDonationDto;
import com.neu.dreambuilder.dto.donor.KidThingDto;
import com.neu.dreambuilder.dto.donor.ProjectDonationDto;
import com.neu.dreambuilder.service.donor.DonorDonationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/donor/donation")
@PreAuthorize("hasAuthority('DONOR')")
@Api(tags = "捐赠者端捐助相关接口")
public class DonorDonationController {

    private final DonorDonationService donorDonationService;

    @Autowired
    public DonorDonationController(DonorDonationService donorDonationService) {
        this.donorDonationService = donorDonationService;
    }

    @PostMapping("/money")
    @ApiOperation("捐助善款")
    public Result<Object> money(@RequestBody Map<String, String> map) {
    //public Result<Object> money(@ApiParam(value = "孩子ID") Long kidId, @ApiParam(value = "金额") int amount) {
        String kidId = map.get("kidId");
        String amount = map.get("amount");
        donorDonationService.addMoney(BaseContext.getCurrentIUserDetails().getId(), Long.parseLong(kidId), Integer.parseInt(amount));
        return Result.success();
    }

    @PostMapping("/thing")
    @ApiOperation("捐助物品")
    public Result<Object> thing(@RequestBody Map<String, String> map) {
    //public Result<Object> thing(@ApiParam(value = "孩子ID") Long kidId, @ApiParam(value = "物品名称") String name) {
        String kidId = map.get("kidId");
        String name = map.get("name");
        donorDonationService.addThing(BaseContext.getCurrentIUserDetails().getId(), Long.valueOf(kidId), name);
        return Result.success();
    }

    @PostMapping("/project")
    @ApiOperation("对项目捐助")
    public Result<Object> project(@RequestBody Map<String, String> map) {
    //public Result<Object> project(@ApiParam(value = "项目ID") Long projectId, @ApiParam(value = "金额") int amount) {
        String projectId = map.get("projectId");
        String amount = map.get("amount");
        donorDonationService.addProject(BaseContext.getCurrentIUserDetails().getId(), Long.parseLong(projectId), Integer.parseInt(amount));
        return Result.success();
    }

    @GetMapping("/statistic")
    @ApiOperation("获得捐助统计信息")
    public Result<DonationStaDto> statistic() {
        return Result.success(donorDonationService.getStatistic(BaseContext.getCurrentIUserDetails().getId()));
    }

    @PostMapping("/money/list")
    @ApiOperation("获取善款捐赠记录")
    public Result<PageInfo<KidDonationDto>> getMoney(@RequestBody PageExample<Object> pageExample) {
        PageHelper.startPage(pageExample.getPageNum(), pageExample.getPageSize());
        return Result.success(new PageInfo<>(donorDonationService.getMoney(BaseContext.getCurrentIUserDetails().getId())));
    }

    @PostMapping("/thing/list")
    @ApiOperation("获取物品捐赠记录")
    public Result<PageInfo<KidThingDto>> getThing(@RequestBody PageExample<Object> pageExample) {
        PageHelper.startPage(pageExample.getPageNum(), pageExample.getPageSize());
        return Result.success(new PageInfo<>(donorDonationService.getThing(BaseContext.getCurrentIUserDetails().getId())));
    }

    @PostMapping("/project/list")
    @ApiOperation("获取对项目捐赠记录")
    public Result<PageInfo<ProjectDonationDto>> getProject(@RequestBody PageExample<Object> pageExample) {
        PageHelper.startPage(pageExample.getPageNum(), pageExample.getPageSize());
        return Result.success(new PageInfo<>(donorDonationService.getProject(BaseContext.getCurrentIUserDetails().getId())));
    }

}
