package com.neu.dreambuilder.service.common;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

public interface ChatService {
    SseEmitter start();

    void receive(String id) throws IOException;

    void send(String id, String msg) throws IOException;

    void close();
}
