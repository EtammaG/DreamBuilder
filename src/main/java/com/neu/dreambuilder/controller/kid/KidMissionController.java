package com.neu.dreambuilder.controller.kid;

import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.dto.mission.MissionDto;
import com.neu.dreambuilder.dto.mission.MissionKidViewDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/kid/mission")
@PreAuthorize("hasAuthority('KID')")
@Api(tags = "孩子任务相关接口")
public class KidMissionController {

    @GetMapping("/date/{date}")
    @ApiOperation("获取指定date的mission列表")
    public Result<List<MissionKidViewDto>> date(@PathVariable @ApiParam(value = "日期") String date) {
        return null;
    }

    @GetMapping("/{id}")
    @ApiOperation("获取指定mission的详情")
    public Result<List<MissionDto>> getById(@PathVariable @ApiParam(value = "任务ID") String id) {
        return null;
    }

}
