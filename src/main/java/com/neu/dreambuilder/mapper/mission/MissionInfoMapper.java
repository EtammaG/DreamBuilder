package com.neu.dreambuilder.mapper.mission;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Mapper
public interface MissionInfoMapper {
    List<Map<String, Object>> selectKidViewByKidIdAndDate(
            @Param("kidId") Long kidId,
            @Param("date") LocalDate date);

    List<Long> selectReplyIds(Long id);
}
