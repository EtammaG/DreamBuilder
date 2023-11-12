package com.neu.dreambuilder.service.volunteer.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.neu.dreambuilder.common.utils.BaseContext;
import com.neu.dreambuilder.dto.kid.KidVieDto;
import com.neu.dreambuilder.dto.mission.MissionDto;
import com.neu.dreambuilder.dto.mission.MissionVolViewDto;
import com.neu.dreambuilder.entity.kid.Mission;
import com.neu.dreambuilder.entity.kid.Reply;
import com.neu.dreambuilder.entity.kid.ToMission;
import com.neu.dreambuilder.mapper.kid.MissionMapper;
import com.neu.dreambuilder.mapper.kid.ReplyMapper;
import com.neu.dreambuilder.mapper.kid.ToMissionMapper;
import com.neu.dreambuilder.mapper.volunteer.VolunteerStatisticMapper;
import com.neu.dreambuilder.service.volunteer.VolunteerKidService;
import com.neu.dreambuilder.service.volunteer.VolunteerMissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
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

    @Resource
    private VolunteerStatisticMapper volunteerStatisticMapper;

    @Value("${dream-builder.sys.mission-initial-value}")
    //@Value("${dream-builder.sys.mission-initial-value}")
    private Long INITIAL_VALUE;

    @Override
    public MissionVolViewDto getRandomMission() {
        MissionVolViewDto missionVolViewDto = new MissionVolViewDto();

//        LambdaQueryWrapper<Mission> missQuery = new LambdaQueryWrapper<>();
//        missQuery.orderByDesc(Mission::getDate);
//        Mission mission = missionMapper.selectList(missQuery).get(0);
//        missionVolViewDto.setTitle(mission.getTitle());
//
//        List<Kid> allKidInfo = volunteerKidService.getAllKidInfo();
//        List<Long> kididlist = allKidInfo.stream().map(kid -> {
//            return kid.getId();
//        }).collect(Collectors.toList());
//
//        LambdaQueryWrapper<ToMission> toMissionQuery = new LambdaQueryWrapper<>();
//        toMissionQuery.eq(ToMission::getMissionId,mission.getId());
//        toMissionQuery.in(ToMission::getKidId,kididlist);
//        toMissionQuery.isNull(ToMission::getReplyId);
//        Integer count = toMissionMapper.selectCount(toMissionQuery);
//        missionVolViewDto.setTotalSubmit(count);
//
//        List<ToMission> toMissions = toMissionMapper.selectList(toMissionQuery);
//        List<Reply> replyList = toMissions.stream().map(toMission -> {
//            Long replyId = toMission.getReplyId();
//            LambdaQueryWrapper<Reply> replyQuery = new LambdaQueryWrapper<>();
//            replyQuery.eq(Reply::getId, replyId);
//            return replyMapper.selectOne(replyQuery);
//
//        }).collect(Collectors.toList());
//
//
//
//        LambdaQueryWrapper<Reply> replyLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        replyLambdaQueryWrapper.isNull(Reply::getScore);
//        Integer wating = replyMapper.selectCount(replyLambdaQueryWrapper);
//
//        missionVolViewDto.setWaitingCheck(wating);
        Long volunId = BaseContext.getCurrentIUserDetails().getId();

        Map<Long, Map<String, Object>> missionSubmitCount = volunteerStatisticMapper.submitCount(volunId);
        Mission missionNew = volunteerStatisticMapper.volunRandomMission(volunId);
        String randomTitle = missionNew.getTitle();
        Long randomSubmitTotal = (Long) missionSubmitCount.get(missionNew.getId()).get("count");
        Integer randomSubmitTotalInt = randomSubmitTotal.intValue();

        Map<Long, Map<String, Object>> hasCheck = volunteerStatisticMapper.hasCheck();
        Long hasCheckCount =(Long)hasCheck.get(missionNew.getId()).get("count");
        Integer hasCheckCountInt = hasCheckCount.intValue();

        Integer waiting = randomSubmitTotalInt - hasCheckCountInt;

        missionVolViewDto.setTitle(randomTitle);
        missionVolViewDto.setTotalSubmit(randomSubmitTotalInt);
        missionVolViewDto.setWaitingCheck(waiting);

        return missionVolViewDto;
    }

    @Override
    public List<MissionVolViewDto> getAllMission() {
        Long volunId = BaseContext.getCurrentIUserDetails().getId();
        List<Mission> missions = volunteerStatisticMapper.volunAllMission(volunId);
        Map<Long, Map<String, Object>> missionSubmitCount = volunteerStatisticMapper.submitCount(volunId);
        Map<Long, Map<String, Object>> hasCheckMissionCount = volunteerStatisticMapper.hasCheck();

        List<MissionVolViewDto> missionVolViewDtos = missions.stream().map(mission -> {
            Long missionId = mission.getId();
            Long total = (Long) missionSubmitCount.get(missionId).get("count");
            Integer totalInt = total.intValue();
            Long hasCheckCount =(Long) hasCheckMissionCount.get(missionId).get("count");
            Integer hasCheckCountInt = hasCheckCount.intValue();
            String title = mission.getTitle();
            MissionVolViewDto missionDto = new MissionVolViewDto();
            missionDto.setTitle(title);
            missionDto.setTotalSubmit(totalInt);
            missionDto.setWaitingCheck(totalInt - hasCheckCountInt);
            missionDto.setId(missionId);
            return missionDto;

        }).collect(Collectors.toList());


        return missionVolViewDtos;

    }

    @Override
    public List<KidVieDto> getKidInfo(Long missionId) {
//        LambdaQueryWrapper<ToMission> toMissionLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        toMissionLambdaQueryWrapper.eq(ToMission::getMissionId,missionId);
//        toMissionMapper.selectList(toMissionLambdaQueryWrapper);
        List<KidVieDto> kidVieDtos = volunteerStatisticMapper.volunMissionKid(missionId);
        return volunteerStatisticMapper.volunMissionKid(missionId);
    }

    @Override
    public MissionDto getMissionDetail(long missionId) {
        LambdaQueryWrapper<Mission> missionQuery = new LambdaQueryWrapper<>();
        missionQuery.eq(Mission::getId,missionId);
        Mission mission = missionMapper.selectOne(missionQuery);
        Long volunId = BaseContext.getCurrentIUserDetails().getId();
        Map<Long,Map<String, Object>> missionSubmitCount = volunteerStatisticMapper.submitCount(volunId);
        Map<Long,Map<String,Object>> hasCheckMissionCount = volunteerStatisticMapper.hasCheck();
        MissionDto missionDto = new MissionDto();
        BeanUtils.copyProperties(mission,missionDto);
        Long total = (Long) missionSubmitCount.get(missionId).get("count");
        Integer totalInt = total.intValue();
        missionDto.setTotalNum(totalInt);
        Long done = (Long) hasCheckMissionCount.get(missionId).get("count");
        missionDto.setDoneNum(done.intValue());
        return missionDto;

    }

    @Override
    public void putScore(Long missionId, Long kidId, int score) {
        LambdaQueryWrapper<ToMission> toMissionLambdaQueryWrapper = new LambdaQueryWrapper<>();
        toMissionLambdaQueryWrapper.eq(ToMission::getMissionId,missionId);
        toMissionLambdaQueryWrapper.eq(ToMission::getKidId,kidId);
        ToMission toMission = toMissionMapper.selectOne(toMissionLambdaQueryWrapper);
        Long replyId = toMission.getReplyId();
        Reply reply = new Reply();
        reply.setId(replyId);
        reply.setScore(score);
        replyMapper.updateById(reply);

    }

    @Override
    public Map<String, Long> getMissionTatal() {
        Long volunId = BaseContext.getCurrentIUserDetails().getId();

        Map<Long,Map<String,Object>> missionSubmitCount = volunteerStatisticMapper.submitCount(volunId);
        Map<Long,Map<String,Object>> hasCheckMissionCount = volunteerStatisticMapper.hasCheck();

        Long total = INITIAL_VALUE;
        for(Long key: missionSubmitCount.keySet()){
           total += (Long) missionSubmitCount.get(key).get("count");
        }
        Long has = INITIAL_VALUE;
        for(Long key : hasCheckMissionCount.keySet()){
            has += (Long)hasCheckMissionCount.get(key).get("count");
        }
        Map<String,Long> map = new HashMap<>();
        map.put("total",total);
        map.put("has",has);

        return map;

    }


}
