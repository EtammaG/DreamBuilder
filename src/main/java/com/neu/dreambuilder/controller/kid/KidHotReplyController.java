package com.neu.dreambuilder.controller.kid;

import com.neu.dreambuilder.dto.CommentDto;
import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.dto.kid.HotReplyDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kid/hot/reply")
@PreAuthorize("hasAuthority('KID')")
@Api(tags = "孩子端推送热门提交相关接口")
public class KidHotReplyController {

    @GetMapping
    @ApiOperation("获取热门提交列表")
    public Result<List<HotReplyDto>> get() {
        return null;
    }

    @PostMapping("/like")
    @ApiOperation("对热门提交点赞")
    public Result<Object> like(@ApiParam(value = "热门提交的ID") String replyId) {
        return Result.success();
    }

    @GetMapping("/{replyId}/comment")
    @ApiOperation("获取热门提交的评论列表")
    public Result<List<CommentDto>> getComment(@PathVariable @ApiParam(value = "热门提交的ID") String replyId) {
        return null;
    }

    @PostMapping("/comment")
    @ApiOperation("对热门提交评论")
    public Result<Object> addComment(
            @ApiParam(value = "热门提交的ID") String replyId,
            @ApiParam(value = "评论内容文本") String comment) {
        return Result.success();
    }


}
