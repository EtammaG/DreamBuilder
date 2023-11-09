package com.neu.dreambuilder.controller.volunteer;

import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.entity.volunteer.Volunteer;
import com.neu.dreambuilder.mapper.volunteer.VolunteerMapper;
import com.neu.dreambuilder.service.volunteer.VolunteerInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/volunteer")
//@PreAuthorize("hasAuthority('VOLUNTEER')")
@Api(tags = "志愿者端相关信息接口")
public class VolunteerInfoController {

    @Resource
    private VolunteerInfoService volunteerInfoService;

    @GetMapping("/info")
    @ApiOperation(value = "志愿者信息")
    public Result<Volunteer> getVolunInfo(){
        return Result.success(volunteerInfoService.getVolunteerInfo());
    }

}
