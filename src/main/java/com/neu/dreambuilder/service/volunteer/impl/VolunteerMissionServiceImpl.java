package com.neu.dreambuilder.service.volunteer.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.neu.dreambuilder.common.utils.BaseContext;
import com.neu.dreambuilder.dto.kid.KidVieDto;
import com.neu.dreambuilder.dto.mission.MissionDto;
import com.neu.dreambuilder.dto.mission.MissionVolViewDto;
import com.neu.dreambuilder.entity.kid.Mission;
import com.neu.dreambuilder.entity.kid.Reply;
import com.neu.dreambuilder.entity.kid.ToMission;
import com.neu.dreambuilder.entity.volunteer.VolunToMission;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
        Map<Long, Integer> missionSubmitCount = volunteerStatisticMapper.submitCount(volunId);
        Mission missionNew = volunteerStatisticMapper.volunRandomMission(volunId);
        String randomTitle = missionNew.getTitle();
        Integer randomSubmitTotal = missionSubmitCount.get(missionNew.getId());


        Map<Long, Integer> hasCheck = volunteerStatisticMapper.hasCheck();
        Integer hasCheckCount = hasCheck.get(missionNew.getId());

        int waiting = randomSubmitTotal - hasCheckCount;

        missionVolViewDto.setTitle(randomTitle);
        missionVolViewDto.setTotalSubmit(randomSubmitTotal);
        missionVolViewDto.setWaitingCheck(waiting);


        return missionVolViewDto;
    }

    @Override
    public List<MissionVolViewDto> getAllMission() {
        Long volunId = BaseContext.getCurrentIUserDetails().getId();
        List<Mission> missions = volunteerStatisticMapper.volunAllMission(volunId);
        Map<Long, Integer> missionSubmitCount = volunteerStatisticMapper.submitCount(volunId);
        Map<Long, Integer> hasCheckMissionCount = volunteerStatisticMapper.hasCheck();

        List<MissionVolViewDto> missionVolViewDtos = missions.stream().map(mission -> {
            Long missionId = mission.getId();
            Integer total = missionSubmitCount.get(missionId);
            Integer hasCheckCount = hasCheckMissionCount.get(missionId);
            String title = mission.getTitle();

            MissionVolViewDto missionDto = new MissionVolViewDto();
            missionDto.setTitle(title);
            missionDto.setTotalSubmit(total);
            missionDto.setWaitingCheck(total - hasCheckCount);
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

        return volunteerStatisticMapper.volunMissionKid(missionId);
    }

    @Override
    public MissionDto getMissionDetail(long missionId) {
        LambdaQueryWrapper<Mission> missionQuery = new LambdaQueryWrapper<>();
        missionQuery.eq(Mission::getId,missionId);
        Mission mission = missionMapper.selectOne(missionQuery);
        Long volunId = BaseContext.getCurrentIUserDetails().getId();
        Map<Long, Integer> missionSubmitCount = volunteerStatisticMapper.submitCount(volunId);
        Map<Long, Integer> hasCheckMissionCount = volunteerStatisticMapper.hasCheck();
        MissionDto missionDto = new MissionDto();
        BeanUtils.copyProperties(mission,missionDto);
        missionDto.setTotalNum(missionSubmitCount.get(missionId));
        missionDto.setDoneNum(hasCheckMissionCount.get(missionId));
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
    public Map<String, Integer> getMissionTatal() {
        Long volunId = BaseContext.getCurrentIUserDetails().getId();
        List<Mission> missions = volunteerStatisticMapper.volunAllMission(volunId);
        Map<Long, Integer> missionSubmitCount = volunteerStatisticMapper.submitCount(volunId);
        Map<Long, Integer> hasCheckMissionCount = volunteerStatisticMapper.hasCheck();
        int total = 0;
        for(Long key: missionSubmitCount.keySet()){
           total += missionSubmitCount.get(key);
        }
        int has = 0;
        for(Long key : hasCheckMissionCount.keySet()){
            has += hasCheckMissionCount.get(key);
        }
        Map<String,Integer> map = new HashMap<>();
        map.put("total",total);
        map.put("has",has);

        return map;
    }


}
