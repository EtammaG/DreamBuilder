package com.neu.dreambuilder.controller.kid;

import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.dto.kid.KidHotReplyCommentDto;
import com.neu.dreambuilder.dto.kid.KidHotReplyDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kid/hot/reply")
@PreAuthorize("hasAuthority('KID')")
public class KidHotReplyController {

    @GetMapping
    public Result<KidHotReplyDto> get() {
        return null;
    }

    @PostMapping("/like")
    public Result<Object> like() {
        return Result.success();
    }

    @GetMapping("/{replyId}/comment")
    public Result<KidHotReplyCommentDto> getComment(@PathVariable("replyId") String replyId) {
        return null;
    }

    @PostMapping("/comment")
    public Result<Object> addComment(@RequestParam String replyId, @RequestParam String comment) {
        return null;
    }



}
