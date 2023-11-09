package com.neu.dreambuilder.mapper.kid;

import com.neu.dreambuilder.entity.kid.Reply;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReplyInfoMapper {
    Reply selectByKidIdAndMissionId(Long id, String missionId);

}
