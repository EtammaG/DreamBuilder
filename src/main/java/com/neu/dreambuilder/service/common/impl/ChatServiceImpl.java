package com.neu.dreambuilder.service.common.impl;

import com.neu.dreambuilder.common.utils.BaseContext;
import com.neu.dreambuilder.entity.user.IUserDetails;
import com.neu.dreambuilder.mapper.common.ChatMapper;
import com.neu.dreambuilder.service.common.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.HashMap;

@Service
public class ChatServiceImpl implements ChatService {

    private final ChatMapper chatMapper;

    private final HashMap<String, SseEmitter> session;

    @Autowired
    public ChatServiceImpl(ChatMapper chatMapper) {
        this.chatMapper = chatMapper;
        this.session = new HashMap<>();
    }

    @Override
    public SseEmitter start() {
        IUserDetails user = BaseContext.getCurrentIUserDetails();
        SseEmitter sseEmitter = new SseEmitter();
        session.put(user.getId().toString(), sseEmitter);
        return sseEmitter;
    }

    @Override
    public void receive(String id) throws IOException {
        IUserDetails user = BaseContext.getCurrentIUserDetails();
        SseEmitter sseEmitter = session.get(user.getId().toString());
        if (user.getType() != 2 && user.getType() != 3) return;
        sseEmitter.send(
                chatMapper.selectAllMsgByBoth(
                        user.getId(),
                        Long.valueOf(id),
                        user.getType() == 2 ? 0 : 1));
    }


    @Override
    public void send(String id, String msg) throws IOException {
        session.get(id).send(msg);
    }

    @Override
    public void close() {
        session.remove(BaseContext.getCurrentIUserDetails().getId().toString());
    }
}
