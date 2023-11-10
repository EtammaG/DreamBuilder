package com.neu.dreambuilder.controller.common;

import com.neu.dreambuilder.common.utils.BaseContext;
import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.service.common.ChatService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;

@RestController
@RequestMapping("/chat")
@Slf4j
@Api(tags = "聊天室相关接口")
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/receive/{id}")
    @PreAuthorize("hasAuthority('LOGIN')")
    public SseEmitter receive(@PathVariable String id) {
        return chatService.receive(Long.valueOf(id), BaseContext.getCurrentIUserDetails());

    }

    @PostMapping("/send")
    @PreAuthorize("hasAuthority('LOGIN')")
    public Result<Object> send(@RequestBody Map<String, String> map) {
        //public Result<Object> send(Long id, String msg) {
        String id = map.get("id");
        String msg = map.get("msg");
        chatService.send(BaseContext.getCurrentIUserDetails(), Long.valueOf(id), msg);
        return Result.success();
    }

}
