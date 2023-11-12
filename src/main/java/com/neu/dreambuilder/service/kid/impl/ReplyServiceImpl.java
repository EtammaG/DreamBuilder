package com.neu.dreambuilder.service.kid.impl;

import com.neu.dreambuilder.common.utils.IdWorker;
import com.neu.dreambuilder.entity.kid.Reply;
import com.neu.dreambuilder.entity.kid.ToMission;
import com.neu.dreambuilder.mapper.kid.ReplyInfoMapper;
import com.neu.dreambuilder.mapper.kid.ReplyMapper;
import com.neu.dreambuilder.mapper.kid.ToMissionMapper;
import com.neu.dreambuilder.service.kid.ReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyServiceImpl implements ReplyService {

    private final ReplyInfoMapper replyInfoMapper;
    private final ReplyMapper replyMapper;
    private final ToMissionMapper toMissionMapper;

    @Autowired
    public ReplyServiceImpl(ReplyInfoMapper replyInfoMapper, ReplyMapper replyMapper, ToMissionMapper toMissionMapper) {
        this.replyInfoMapper = replyInfoMapper;
        this.replyMapper = replyMapper;
        this.toMissionMapper = toMissionMapper;
    }

    @Override
    public Reply getByKidIdAndMissionId(Long id, String missionId) {
        return replyInfoMapper.selectByKidIdAndMissionId(id, missionId);
    }

    @Override
    public void add(Long id, Long missionId, String replyMedia) {
        Long replyId = IdWorker.nextId();
        replyMapper.insert(new Reply(replyId, replyMedia, null, null));
        toMissionMapper.updateReply(new ToMission(id, missionId, replyId));
    }
}
