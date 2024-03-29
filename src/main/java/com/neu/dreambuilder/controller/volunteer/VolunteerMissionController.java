package com.neu.dreambuilder.controller.volunteer;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.dreambuilder.dto.PageExample;
import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.dto.kid.KidVieDto;
import com.neu.dreambuilder.dto.mission.MissionDto;
import com.neu.dreambuilder.dto.mission.MissionVolViewDto;
import com.neu.dreambuilder.entity.kid.Mission;
import com.neu.dreambuilder.mapper.volunteer.VolunteerStatisticMapper;
import com.neu.dreambuilder.service.volunteer.VolunteerMissionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/volunteer/mission")
@PreAuthorize("hasAuthority('VOLUNTEER')")
@Api(tags = "志愿者任务相关信息接口")
public class VolunteerMissionController {

    @Resource
    private VolunteerMissionService volunteerMissionService;



    @GetMapping(value = "/random",produces = "application/json; charset=utf-8")
    @ApiOperation(value = "志愿者主页面滑动的我的任务")
    public Result<MissionVolViewDto> getMission(){

        return Result.success(volunteerMissionService.getRandomMission());
    }

    @GetMapping(value = "/totalMissionCount",produces = "application/json; charset=utf-8")
    @ApiOperation(value = "志愿者总任务，和未完成的总任务数")
    public Result<Map<String,Long>> getMissionTatal(){
        return Result.success( volunteerMissionService.getMissionTatal());
    }


    @PostMapping(value = "/list",produces = "application/json; charset=utf-8")
    @ApiOperation(value = "志愿者端任务列表")
    public Result<PageInfo<MissionVolViewDto>> getAllMission(@RequestBody PageExample<Mission> missionPageExample){

        PageHelper.startPage(missionPageExample.getPageNum(),missionPageExample.getPageSize());
        List<MissionVolViewDto> allMission = volunteerMissionService.getAllMission();
        PageInfo<MissionVolViewDto> pageInfo = new PageInfo<>(allMission);
        return Result.success(pageInfo);
    }


    @PostMapping(value = "/allkid/detail",produces = "application/json; charset=utf-8")
    @ApiOperation("单独任务孩子的完成情况")
    public Result<PageInfo<KidVieDto>> getMissionAllDetail(@RequestBody PageExample<Mission> missionPageExample){
        PageHelper.startPage(missionPageExample.getPageNum(),missionPageExample.getPageSize());
        Long missionid = Long.valueOf(missionPageExample.getExample().getId());
        List<KidVieDto> kidVieDtos = volunteerMissionService.getKidInfo(missionid);
        PageInfo<KidVieDto> pageInfo = new PageInfo<>(kidVieDtos);
        return Result.success(pageInfo);
    }


    @GetMapping(value = "/detail",produces = "application/json; charset=utf-8")
    @ApiOperation("该任务的详细信息")
    public Result<MissionDto> getMissionDetail(@ApiParam(name = "id",value = "任务id") @RequestParam String id){

        Long ids = Long.parseLong(id);
        return Result.success(volunteerMissionService.getMissionDetail(ids));
    }



    @PostMapping(value = "/inputscore",produces = "application/json; charset=utf-8")
    @ApiOperation("给孩子完成的任务打分")
    public void saveScore(@RequestBody Map<String,Object> map){
        volunteerMissionService.putScore(Long.valueOf((String)map.get("missionId")),Long.valueOf((String) map.get("kidId")),(Integer)map.get("score"));
    }


}