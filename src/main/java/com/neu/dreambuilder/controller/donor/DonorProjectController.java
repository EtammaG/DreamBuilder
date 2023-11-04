package com.neu.dreambuilder.controller.donor;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.dreambuilder.dto.PageExample;
import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.dto.donor.ProjectSimDto;
import com.neu.dreambuilder.entity.donor.Project;
import com.neu.dreambuilder.entity.donor.ProjectExample;
import com.neu.dreambuilder.entity.donor.ProjectType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donor/project")
@PreAuthorize("hasAuthority('DONOR')")
@Api(tags = "捐赠者端获取项目信息相关接口")
public class DonorProjectController {

    @GetMapping("/type")
    @ApiOperation("获取项目类别列表")
    public Result<List<ProjectType>> type() {
        return Result.success();
    }

    @PostMapping("/search")
    @ApiOperation("搜索项目简单信息")
    public Result<PageInfo<ProjectSimDto>> search(@RequestBody PageExample<ProjectExample> pageExample) {
        PageHelper.startPage(pageExample.getPageNum(), pageExample.getPageSize());
        return Result.success();
    }

    @GetMapping("/{id}}")
    @ApiOperation("获取指定项目的详细信息")
    public Result<Project> getById(@PathVariable @ApiParam("项目ID") String id) {
        return Result.success();
    }


}
