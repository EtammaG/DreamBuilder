package com.neu.dreambuilder.service.common;

import com.neu.dreambuilder.entity.user.IUserDetails;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface ChatService {

    SseEmitter receive(Long fromId, IUserDetails to);

    void send(IUserDetails from, Long toId, String msg);

}
