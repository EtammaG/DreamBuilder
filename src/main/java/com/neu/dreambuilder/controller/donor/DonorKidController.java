package com.neu.dreambuilder.controller.donor;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.dreambuilder.dto.PageExample;
import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.dto.kid.KidDto;
import com.neu.dreambuilder.dto.kid.KidRecDto;
import com.neu.dreambuilder.dto.kid.KidSimDto;
import com.neu.dreambuilder.entity.kid.KidExample;
import com.neu.dreambuilder.entity.kid.Type;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donor/kid")
@PreAuthorize("hasAuthority('DONOR')")
@Api(tags = "捐赠者端获取孩子信息相关接口")
public class DonorKidController {

    @GetMapping("/recent")
    @ApiOperation("获取某些孩子近况列表")
    public Result<List<KidRecDto>> recent() {
        return Result.success();
    }

    @GetMapping("/type")
    @ApiOperation("获取孩子类别列表")
    public Result<List<Type>> type() {
        return Result.success();
    }

    @PostMapping("/search")
    @ApiOperation("搜索孩子简单信息")
    public Result<PageInfo<KidSimDto>> search(@RequestBody PageExample<KidExample> pageExample) {
        PageHelper.startPage(pageExample.getPageNum(), pageExample.getPageSize());
        return Result.success();
    }

    @GetMapping("/{id}}")
    @ApiOperation("获取指定孩子的详细信息")
    public Result<KidDto> getById(@PathVariable @ApiParam("孩子ID") String id) {
        return Result.success();
    }

}
