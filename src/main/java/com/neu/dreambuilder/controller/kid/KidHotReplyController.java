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
import java.util.Map;

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
    public Result<Object> like(@RequestBody Map<String, String> map) {
        //    public Result<Object> like(@ApiParam(value = "热门提交的ID") String hotId) {
        String hotId = map.get("hotId");
        hotReplyService.like(BaseContext.getCurrentIUserDetails().getId(), Long.parseLong(hotId));
        return Result.success();
    }

    @PostMapping("/unlike")
    @ApiOperation("对热门提交取消点赞")
    public Result<Object> unlike(@RequestBody Map<String, String> map) {
        //    public Result<Object> unlike(@ApiParam(value = "热门提交的ID") String hotId) {
        String hotId = map.get("hotId");
        hotReplyService.unlike(BaseContext.getCurrentIUserDetails().getId(), Long.parseLong(hotId));
        return Result.success();
    }

    @GetMapping("/{hotId}/comment")
    @ApiOperation("获取热门提交的评论列表")
    public Result<List<CommentDto>> getComment(@PathVariable @ApiParam(value = "热门提交的ID") String hotId) {
        return Result.success(hotReplyService.getComment(hotId));
    }

    @PostMapping("/comment")
    @ApiOperation("对热门提交评论")
    public Result<Object> addComment(@RequestBody Map<String, String> map) {
        //    public Result<Object> addComment(
        //            @ApiParam(value = "热门提交的ID") String hotId,
        //            @ApiParam(value = "评论内容文本") String comment) {
        String hotId = map.get("hotId");
        String comment = map.get("content");
        hotReplyService.addComment(BaseContext.getCurrentIUserDetails().getId(), Long.parseLong(hotId), comment);
        return Result.success();
    }


}
