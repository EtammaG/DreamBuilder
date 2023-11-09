package com.neu.dreambuilder.service.common;

import com.neu.dreambuilder.entity.user.IUserDetails;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

public interface ChatService {

    SseEmitter start(Long id);

    void receive(Long fromId, IUserDetails to) throws IOException;

    void send(Long toId, String msg) throws IOException;

    void close(Long id);
}
