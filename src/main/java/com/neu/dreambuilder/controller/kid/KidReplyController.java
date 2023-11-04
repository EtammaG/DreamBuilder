package com.neu.dreambuilder.controller.kid;

import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.entity.kid.Reply;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kid/reply")
@PreAuthorize("hasAuthority('KID')")
@Api(tags = "孩子任务提交相关接口")
public class KidReplyController {

    @GetMapping("/{missionId}")
    @ApiOperation("获取任务的答题情况")
    public Result<Reply> getByMission(@PathVariable @ApiParam(value = "任务ID") String missionId) {
        return null;
    }

    @PostMapping
    @ApiOperation("答题")
    public Result<Object> post(
            @ApiParam(value = "任务ID") String missionId,
            @ApiParam(value = "提交的媒体文件地址") String replyMedia
    ) {
        return Result.success();
    }


}
