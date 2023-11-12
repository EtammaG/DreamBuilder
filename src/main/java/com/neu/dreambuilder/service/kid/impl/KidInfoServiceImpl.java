package com.neu.dreambuilder.service.kid.impl;

import com.alibaba.fastjson.JSON;
import com.neu.dreambuilder.dto.kid.*;
import com.neu.dreambuilder.entity.donor.KidDonation;
import com.neu.dreambuilder.entity.donor.KidThing;
import com.neu.dreambuilder.entity.kid.Kid;
import com.neu.dreambuilder.entity.kid.School;
import com.neu.dreambuilder.entity.kid.Type;
import com.neu.dreambuilder.mapper.donor.DonorMapper;
import com.neu.dreambuilder.mapper.donor.KidDonationMapper;
import com.neu.dreambuilder.mapper.donor.KidThingMapper;
import com.neu.dreambuilder.mapper.kid.KidInfoMapper;
import com.neu.dreambuilder.mapper.kid.KidMapper;
import com.neu.dreambuilder.mapper.kid.SchoolMapper;
import com.neu.dreambuilder.mapper.kid.TypeMapper;
import com.neu.dreambuilder.service.kid.KidInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class KidInfoServiceImpl implements KidInfoService {

    private final String redisPrefix;
    private final String typeKey;

    private final TypeMapper typeMapper;
    private final KidMapper kidMapper;
    private final SchoolMapper schoolMapper;
    private final KidInfoMapper kidInfoMapper;

    private final DonorMapper donorMapper;

    private final KidThingMapper kidThingMapper;
    private final KidDonationMapper kidDonationMapper;

    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public KidInfoServiceImpl(
            @Value("${dream-builder.redis.prefix.kid.i}") String redisPrefix,
            TypeMapper typeMapper, KidMapper kidMapper, SchoolMapper schoolMapper, KidInfoMapper kidInfoMapper, DonorMapper donorMapper, KidThingMapper kidThingMapper, KidDonationMapper kidDonationMapper, StringRedisTemplate stringRedisTemplate) {
        this.redisPrefix = redisPrefix;
        this.typeMapper = typeMapper;
        this.kidMapper = kidMapper;
        this.schoolMapper = schoolMapper;
        this.kidInfoMapper = kidInfoMapper;
        this.donorMapper = donorMapper;
        this.kidThingMapper = kidThingMapper;
        this.kidDonationMapper = kidDonationMapper;
        this.stringRedisTemplate = stringRedisTemplate;
        this.typeKey = redisPrefix + "type";
    }

    private KidRecDto toKidRecDto(Kid kid) {
        String award = null;
        Long donorId = null;
        KidDonation kidDonation = kidDonationMapper.selectOneByKidId(kid.getId());
        if (kidDonation == null) {
            KidThing kidThing = kidThingMapper.selectOneByKidId(kid.getId());
            if (kidThing != null) {
                award = kidThing.getThingName();
                donorId = kidThing.getDonorId();
            }
        } else {
            award = kidDonation.getAmount() + "元";
            donorId = kidDonation.getDonorId();
        }
        String recent = donorId == null
                ? "暂无"
                : kid.getName()
                + "小朋友通过自己的努力，获得了来自"
                + donorMapper.selectById(donorId).getNickname() + "的" + award + "捐助";
        return new KidRecDto(kid.getName(), kid.getPhoto(), recent);
    }

    @Override
    public List<KidRecDto> getRandomRecs() {
        return kidMapper.selectRandom(10).stream().map(this::toKidRecDto).toList();
    }

    @Override
    public List<Type> getAllType() {
        return JSON.parseArray(stringRedisTemplate.opsForValue().get(typeKey), Type.class);
    }

    @Override
    public KidDto getById(Long id) {
        Kid kid = kidMapper.selectById(id);
        School school = schoolMapper.selectById(kid.getSchoolId());
        KidDto kidDto = new KidDto();
        BeanUtils.copyProperties(kid, kidDto);
        kidDto.setSchool(school.getName());
        return kidDto;
    }

    @Override
    public List<KidSimDto> getSimByType(Long typeId) {
        return kidInfoMapper.selectSimByTypeId(typeId);
    }

    @Override
    public List<KidSimDto> getSim() {
        return kidInfoMapper.selectSim();
    }

    @Override
    public KidMisDto getMis(Long kidId) {
        Kid kid = kidMapper.selectById(kidId);
        List<Map<String, Object>> maps = kidInfoMapper.selectMisInfo(kid.getId());
        int subMissionWaiting = 0;
        int subMissionDone = 0;
        int optMissionWaiting = 0;
        int optMissionDone = 0;
        for (Map<String, Object> map : maps) {
            Integer type = (Integer) map.get("type");
            Long replyId = (Long) map.get("reply_id");
            if (type == 0 && replyId == null) optMissionWaiting++;
            if (type == 1 && replyId == null) subMissionWaiting++;
            if (type == 0 && replyId != null) optMissionDone++;
            if (type == 1 && replyId != null) subMissionDone++;
        }
        return new KidMisDto(
                kid.getPhoto(),
                kid.getTotalPoint(),
                subMissionWaiting,
                subMissionDone,
                optMissionWaiting,
                optMissionDone
        );
    }

    @Override
    public KidMalDto getMal(Long kidId) {
        return kidInfoMapper.selectMalInfo(kidId);
    }

    @Override
    public KidMeeDto getMee(Long kidId) {
        Kid kid = kidMapper.selectById(kidId);
        KidMeeDto kidMeeDto = new KidMeeDto();
        BeanUtils.copyProperties(kid, kidMeeDto);
        Integer a = kidDonationMapper.selectTotalAmountByKidId(kid.getId());
        kidMeeDto.setObtainedMoney(a == null ? 0 : a);
        kidMeeDto.setRank(kidInfoMapper.selectRankById(kid.getId()));
        kidMeeDto.setWeekrank(kidInfoMapper.selectWeekrankById(kid.getId()));
        return kidMeeDto;
    }

    @Override
    public KidRecDto getRec(Long kidId) {
        return toKidRecDto(kidMapper.selectById(kidId));
    }

    @Transactional
//    @Scheduled(cron = "* * * * * *")
    public void updateCache() {
        // 有关当前用户的所有信息是否应该存入BaseContext中，一个人的信息不会很多
        updateCacheType();
    }

    private void updateCacheType() {
        List<Type> types = typeMapper.selectAll();
        stringRedisTemplate.opsForValue().set(typeKey, JSON.toJSONString(types));
    }

}
