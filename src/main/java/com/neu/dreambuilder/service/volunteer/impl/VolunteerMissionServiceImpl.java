package com.neu.dreambuilder.service.volunteer.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.neu.dreambuilder.dto.mission.MissionDto;
import com.neu.dreambuilder.dto.mission.MissionVolViewDto;
import com.neu.dreambuilder.entity.kid.Kid;
import com.neu.dreambuilder.entity.kid.Mission;
import com.neu.dreambuilder.entity.kid.Reply;
import com.neu.dreambuilder.entity.kid.ToMission;
import com.neu.dreambuilder.mapper.kid.MissionMapper;
import com.neu.dreambuilder.mapper.kid.ReplyMapper;
import com.neu.dreambuilder.mapper.kid.ToMissionMapper;
import com.neu.dreambuilder.service.volunteer.VolunteerKidService;
import com.neu.dreambuilder.service.volunteer.VolunteerMissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VolunteerMissionServiceImpl implements VolunteerMissionService {
    @Resource
    private MissionMapper missionMapper;
    @Resource
    private VolunteerKidService volunteerKidService;
    @Resource
    private ToMissionMapper toMissionMapper;
    @Resource
    private ReplyMapper replyMapper;

    @Override
    public MissionVolViewDto getRandomMission() {
        MissionVolViewDto missionVolViewDto = new MissionVolViewDto();

        LambdaQueryWrapper<Mission> missQuery = new LambdaQueryWrapper<>();
        missQuery.orderByDesc(Mission::getDate);
        Mission mission = missionMapper.selectList(missQuery).get(0);
        missionVolViewDto.setTitle(mission.getTitle());

        List<Kid> allKidInfo = volunteerKidService.getAllKidInfo();
        List<Long> kididlist = allKidInfo.stream().map(kid -> {
            return kid.getId();
        }).collect(Collectors.toList());

        LambdaQueryWrapper<ToMission> toMissionQuery = new LambdaQueryWrapper<>();
        toMissionQuery.eq(ToMission::getMissionId,mission.getId());
        toMissionQuery.in(ToMission::getKidId,kididlist);
        toMissionQuery.isNull(ToMission::getReplyId);
        Integer count = toMissionMapper.selectCount(toMissionQuery);
        missionVolViewDto.setTotalSubmit(count);

        List<ToMission> toMissions = toMissionMapper.selectList(toMissionQuery);
        List<Reply> replyList = toMissions.stream().map(toMission -> {
            Long replyId = toMission.getReplyId();
            LambdaQueryWrapper<Reply> replyQuery = new LambdaQueryWrapper<>();
            replyQuery.eq(Reply::getId, replyId);
            return replyMapper.selectOne(replyQuery);

        }).collect(Collectors.toList());

        LambdaQueryWrapper<Reply> replyLambdaQueryWrapper = new LambdaQueryWrapper<>();
        replyLambdaQueryWrapper.isNull(Reply::getScore);
        Integer wating = replyMapper.selectCount(replyLambdaQueryWrapper);

        missionVolViewDto.setWaitingCheck(wating);


        return missionVolViewDto;
    }





}
