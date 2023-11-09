package com.neu.dreambuilder.controller.kid;

import com.neu.dreambuilder.common.utils.BaseContext;
import com.neu.dreambuilder.dto.CommentDto;
import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.dto.kid.HotReplyDto;
import com.neu.dreambuilder.service.kid.HotReplyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kid/hot/reply")
@PreAuthorize("hasAuthority('KID')")
@Api(tags = "孩子端推送热门提交相关接口")
public class KidHotReplyController {

    private final HotReplyService hotReplyService;

    @Autowired
    public KidHotReplyController(HotReplyService hotReplyService) {
        this.hotReplyService = hotReplyService;
    }

    @GetMapping
    @ApiOperation("获取热门提交列表")
    public Result<List<HotReplyDto>> get() {
        return Result.success(hotReplyService.getAll(BaseContext.getCurrentIUserDetails().getId()));
    }

    @PostMapping("/like")
    @ApiOperation("对热门提交点赞")
    public Result<Object> like(@ApiParam(value = "热门提交的ID") String hotId) {
        hotReplyService.like(BaseContext.getCurrentIUserDetails().getId(), Long.parseLong(hotId));
        return Result.success();
    }

    @GetMapping("/{hotId}/comment")
    @ApiOperation("获取热门提交的评论列表")
    public Result<List<CommentDto>> getComment(@PathVariable @ApiParam(value = "热门提交的ID") String hotId) {
        return Result.success(hotReplyService.getComment(hotId));
    }

    @PostMapping("/comment")
    @ApiOperation("对热门提交评论")
    public Result<Object> addComment(
            @ApiParam(value = "热门提交的ID") String hotId,
            @ApiParam(value = "评论内容文本") String comment) {
        hotReplyService.addComment(BaseContext.getCurrentIUserDetails().getId(), Long.parseLong(hotId), comment);
        return Result.success();
    }


}
