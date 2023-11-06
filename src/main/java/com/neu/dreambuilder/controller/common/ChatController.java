package com.neu.dreambuilder.controller.common;

import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.service.common.ChatService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@RestController
@RequestMapping("chat")
@Slf4j
@Api(tags = "聊天室相关接口")
public class ChatController {

    private final ChatService chatService;

    @Autowired
    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @GetMapping("/start")
    public Result<SseEmitter> start() {
        return Result.success(chatService.start());
    }

    @GetMapping("/receive/{id}")
    public Result<Object> receive(@PathVariable String id) {
        try {
            chatService.receive(id);
            return Result.success();
        } catch (IOException e) {
            return Result.error("接受信息失败");
        }
    }

    @PostMapping("/send")
    public Result<Object> send(String id, String msg) {
        try {
            chatService.send(id, msg);
            return Result.success();
        } catch (IOException e) {
            return Result.error("发送信息失败");
        }
    }

    @PostMapping("/close")
    public Result<Object> close() {
        chatService.close();
        return Result.success();
    }

}
