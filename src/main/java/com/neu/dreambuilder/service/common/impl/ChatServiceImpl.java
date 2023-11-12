package com.neu.dreambuilder.service.common.impl;

import com.neu.dreambuilder.entity.Chat;
import com.neu.dreambuilder.entity.user.IUserDetails;
import com.neu.dreambuilder.exception.bean.CustomException;
import com.neu.dreambuilder.mapper.common.ChatMapper;
import com.neu.dreambuilder.service.common.ChatService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;

@Service
@Slf4j
public class ChatServiceImpl implements ChatService {

    private final ChatMapper chatMapper;

    private final HashMap<Long, SseEmitter> session;

    @Autowired
    public ChatServiceImpl(ChatMapper chatMapper) {
        this.chatMapper = chatMapper;
        this.session = new HashMap<>();
    }

    @Override
    public SseEmitter receive(Long fromId, IUserDetails to) {
        if (to.getType() != 2 && to.getType() != 3) return null;
        SseEmitter sseEmitter = new SseEmitter();
        try {
            sseEmitter.send(chatMapper.selectAllMsgByBoth(
                    to.getId(),
                    fromId,
                    to.getType() == 2 ? 0 : 1));
        } catch (IOException e) {
            log.warn("连接失败", e);
            throw new CustomException("连接失败");
        }
        sseEmitter.onCompletion(() -> session.remove(to.getId()));
        sseEmitter.onTimeout(() -> session.remove(to.getId()));
        sseEmitter.onError((throwable) -> session.remove(to.getId()));
        session.put(to.getId(), sseEmitter);
        return sseEmitter;
    }

    @Override
    public void send(IUserDetails from, Long toId, String msg) {
        SseEmitter sseEmitter = session.get(toId);
        if (sseEmitter != null) {
            try {
                sseEmitter.send(msg);
            } catch (IOException e) {
                log.error("发送失败", e);
                throw new CustomException("发送失败");
            }
        } else {
            chatMapper.insert(new Chat(
                    msg,
                    from.getId(),
                    toId,
                    from.getType() == 2 ? 1 : 0, LocalDateTime.now()));
        }
    }

}
