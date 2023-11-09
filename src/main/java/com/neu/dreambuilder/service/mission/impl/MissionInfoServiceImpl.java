package com.neu.dreambuilder.service.mission.impl;

import com.alibaba.fastjson.JSON;
import com.neu.dreambuilder.dto.mission.MissionDto;
import com.neu.dreambuilder.dto.mission.MissionKidViewDto;
import com.neu.dreambuilder.entity.kid.Mission;
import com.neu.dreambuilder.mapper.kid.MissionMapper;
import com.neu.dreambuilder.mapper.mission.MissionInfoMapper;
import com.neu.dreambuilder.service.mission.MissionInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MissionInfoServiceImpl implements MissionInfoService {

    private final MissionInfoMapper missionInfoMapper;
    private final MissionMapper missionMapper;

    @Autowired
    public MissionInfoServiceImpl(MissionInfoMapper missionInfoMapper, MissionMapper missionMapper) {
        this.missionInfoMapper = missionInfoMapper;
        this.missionMapper = missionMapper;
    }

    @Override
    public List<MissionKidViewDto> getKidViewByKidIdAndDate(Long kidId, LocalDate date) {
        List<Map<String, Object>> maps = missionInfoMapper.selectKidViewByKidIdAndDate(kidId, date);
        List<MissionKidViewDto> res = new ArrayList<>(maps.size());
        for(Map<String, Object> map : maps) {
            MissionKidViewDto missionKidViewDto = JSON.parseObject(JSON.toJSONString(map), MissionKidViewDto.class);
            missionKidViewDto.setDone(map.get("reply_id") != null);
            missionKidViewDto.setChecked(map.get("score") != null);
            res.add(missionKidViewDto);
        }
        return res;
    }

    @Override
    public MissionDto getById(Long id) {
        Mission mission = missionMapper.selectById(id);
        List<Long> replyIds = missionInfoMapper.selectReplyIds(id);
        int count = 0;
        for(Long replyId : replyIds)
            if(replyId != null) count++;
        MissionDto missionDto = new MissionDto();
        BeanUtils.copyProperties(mission, missionDto);
        missionDto.setTotalNum(replyIds.size());
        missionDto.setDoneNum(count);
        return missionDto;
    }
}
