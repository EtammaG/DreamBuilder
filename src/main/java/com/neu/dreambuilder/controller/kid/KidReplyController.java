package com.neu.dreambuilder.controller.kid;

import com.neu.dreambuilder.common.utils.BaseContext;
import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.entity.kid.Reply;
import com.neu.dreambuilder.service.kid.ReplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/kid/reply")
@PreAuthorize("hasAuthority('KID')")
@Api(tags = "孩子端孩子任务提交相关接口")
public class KidReplyController {

    private final ReplyService replyService;

    @Autowired
    public KidReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @GetMapping("/{missionId}")
    @ApiOperation("获取任务的答题情况")
    public Result<Reply> getByMission(@PathVariable @ApiParam(value = "任务ID") String missionId) {
        return Result.success(replyService.getByKidIdAndMissionId(BaseContext.getCurrentIUserDetails().getId(), missionId));
    }

    @PostMapping
    @ApiOperation("答题")
    public Result<Object> post(@RequestBody Map<String, String> map) {
//        public Result<Object> post
//    }(
//            @ApiParam(value = "任务ID") String missionId,
//            @ApiParam(value = "提交的媒体文件地址") String replyMedia
//    ) {
        String missionId = map.get("missionId");
        String replyMedia = map.get("replyMedia");
        replyService.add(BaseContext.getCurrentIUserDetails().getId(), Long.valueOf(missionId), replyMedia);
        return Result.success();
    }


}
