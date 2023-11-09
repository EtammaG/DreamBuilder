package com.neu.dreambuilder.service.common.impl;

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

    private final HashMap<Long, SseEmitter> session;

    @Autowired
    public ChatServiceImpl(ChatMapper chatMapper) {
        this.chatMapper = chatMapper;
        this.session = new HashMap<>();
    }

    @Override
    public SseEmitter start(Long id) {
        SseEmitter sseEmitter = new SseEmitter();
        session.put(id, sseEmitter);
        return sseEmitter;
    }

    @Override
    public void receive(Long fromId, IUserDetails to) throws IOException {
        SseEmitter sseEmitter = session.get(to.getId());
        if (to.getType() != 2 && to.getType() != 3) return;
        sseEmitter.send(
                chatMapper.selectAllMsgByBoth(
                        to.getId(),
                        fromId,
                        to.getType() == 2 ? 0 : 1));
    }


    @Override
    public void send(Long toId, String msg) throws IOException {
        session.get(toId).send(msg);
    }

    @Override
    public void close(Long id) {
        session.remove(id);
    }
}
