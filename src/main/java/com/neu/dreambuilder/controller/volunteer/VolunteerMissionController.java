package com.neu.dreambuilder.controller.volunteer;

import com.neu.dreambuilder.dto.PageExample;
import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.dto.volunteer.VolunteerMissionKidDto;
import com.neu.dreambuilder.dto.volunteer.VolunteerMissionInfoDto;
import com.neu.dreambuilder.dto.volunteer.VolunteerMissonDto;
import com.neu.dreambuilder.entity.kid.Mission;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/volunteer/mission")
@PreAuthorize("hasAuthority('VOLUNTEER')")
@Api(value = "志愿者任务相关信息接口")
public class VolunteerMissionController {

    @GetMapping("/random")
    @ApiOperation(value = "志愿者主页面滑动的我的任务")
    public Result<VolunteerMissonDto> getMission(){
        return null;
    }

    @PostMapping("/list")
    @ApiOperation(value = "志愿者端任务列表")
    public Result<PageExample<VolunteerMissonDto>> getAllMission(@RequestBody PageExample<Mission> missionPageExample){
        return null;
    }


    @PostMapping("/allkid/detail")
    @ApiOperation("单独任务孩子的完成情况")
    public Result<PageExample<VolunteerMissionKidDto>> getMissionAllDetail(@RequestBody PageExample<Mission> missionPageExample){
        return null;
    }


    @GetMapping("/detail")
    @ApiOperation("该任务的详细信息")
    public Result<VolunteerMissionInfoDto> getMissionDetail(@ApiParam(name = "id",value = "任务id") @RequestBody String id){
        return null;
    }


    @PostMapping("/inputscore")
    @ApiOperation("给孩子完成的任务打分")
    public void saveScore(@ApiParam("前端传过来的missionId") Long missionId,
                          @ApiParam("前端传过来的kidId")Long kidId,
                          @ApiParam("前端传过来的score")int score){

    }




}