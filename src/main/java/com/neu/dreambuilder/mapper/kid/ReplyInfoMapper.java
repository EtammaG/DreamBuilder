package com.neu.dreambuilder.mapper.kid;

import com.neu.dreambuilder.entity.kid.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReplyInfoMapper {
    Reply selectByKidIdAndMissionId(@Param("kidId") Long id, @Param("missionId") String missionId);

}
