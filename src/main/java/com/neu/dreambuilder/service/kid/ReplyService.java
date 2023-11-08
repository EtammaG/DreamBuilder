package com.neu.dreambuilder.service.kid;

import com.neu.dreambuilder.entity.kid.Reply;

public interface ReplyService {
    Reply getByKidIdAndMissionId(Long id, String missionId);

    void add(Long id, Long missionId, String replyMedia);
}
