package com.neu.dreambuilder.controller.volunteer;

import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.dto.kid.KidRecDto;
import com.neu.dreambuilder.entity.kid.Kid;
import com.neu.dreambuilder.service.volunteer.VolunteerKidService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/volunteer/kid")
@PreAuthorize("hasAuthority('VOLUNTEER')")
@Api(tags = "志愿者端孩子信息接口")
public class VolunteerKidController {

    @Resource
    private VolunteerKidService volunteerKidService;

    /**
     *志愿者主页面随机孩子的近况
     * @return
     */
    @GetMapping("/random/recent")
    @ApiOperation(value = "主页面孩子近况")
    public Result<KidRecDto> getRandom(){
        return Result.success(volunteerKidService.getKidRandomRec());
    }

    /**
     * 所有孩子列表
     * @return
     */
    @GetMapping("/all")
    @ApiOperation(value = "孩子信息列表")
    public Result<List<Kid>> getAll(){

        return Result.success(volunteerKidService.getAllKidInfo());
    }



}