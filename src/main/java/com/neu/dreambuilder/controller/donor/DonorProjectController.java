package com.neu.dreambuilder.controller.donor;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.dreambuilder.dto.PageExample;
import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.dto.donor.ProjectSimDto;
import com.neu.dreambuilder.entity.donor.Project;
import com.neu.dreambuilder.entity.donor.ProjectExample;
import com.neu.dreambuilder.entity.donor.ProjectType;
import com.neu.dreambuilder.service.donor.DonorProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donor/project")
@PreAuthorize("hasAuthority('DONOR')")
@Api(tags = "捐赠者端获取项目信息相关接口")
public class DonorProjectController {

    private final DonorProjectService donorProjectService;

    @Autowired
    public DonorProjectController(DonorProjectService donorProjectService) {
        this.donorProjectService = donorProjectService;
    }

    @GetMapping("/type")
    @ApiOperation("获取项目类别列表")
    public Result<List<ProjectType>> type() {
        return Result.success(donorProjectService.getAllType());
    }

    @PostMapping("/search")
    @ApiOperation("搜索项目简单信息")
    public Result<PageInfo<ProjectSimDto>> searchByType(@RequestBody PageExample<ProjectExample> pageExample) {
        PageHelper.startPage(pageExample.getPageNum(), pageExample.getPageSize());
        return Result.success(new PageInfo<>(donorProjectService.getSimByType(pageExample.getExample().getTypeId())));
    }

    @PostMapping("/list")
    @ApiOperation("搜索项目简单信息")
    public Result<PageInfo<ProjectSimDto>> list(@RequestBody PageExample<Object> pageExample) {
        PageHelper.startPage(pageExample.getPageNum(), pageExample.getPageSize());
        return Result.success(new PageInfo<>(donorProjectService.getSim()));
    }

    @GetMapping("/{id}")
    @ApiOperation("获取指定项目的详细信息")
    public Result<Project> getById(@PathVariable @ApiParam("项目ID") String id) {
        return Result.success(donorProjectService.getById(id));
    }


}
