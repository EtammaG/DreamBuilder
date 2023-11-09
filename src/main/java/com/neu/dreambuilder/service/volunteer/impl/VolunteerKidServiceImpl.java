package com.neu.dreambuilder.service.volunteer.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.neu.dreambuilder.common.utils.BaseContext;
import com.neu.dreambuilder.dto.kid.KidDto;
import com.neu.dreambuilder.dto.kid.KidRecDto;
import com.neu.dreambuilder.entity.kid.Kid;
import com.neu.dreambuilder.entity.volunteer.VolunToKid;
import com.neu.dreambuilder.mapper.kid.KidMapper;
import com.neu.dreambuilder.mapper.volunteer.VolunToKidMapper;
import com.neu.dreambuilder.mapper.volunteer.VolunteerStatisticMapper;
import com.neu.dreambuilder.service.volunteer.VolunteerKidService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class VolunteerKidServiceImpl implements VolunteerKidService {
    @Resource
    private VolunToKidMapper volunToKidMapper;

    @Resource
    private VolunteerStatisticMapper volunteerStatisticMapper;

    @Resource
    private KidMapper kidMapper;
    @Override
    public KidRecDto getKidRandomRec() {

//        LambdaQueryWrapper<VolunToKid> volunQuery = new LambdaQueryWrapper<>();
//        volunQuery.eq(VolunToKid::getVolunId,volunId);
//        List<VolunToKid> volunToKids = volunToKidMapper.selectList(volunQuery);

//        volunToKids.stream().map(volunToKid -> {
//            KidRecDto kidRecDto = new KidRecDto();
//
//            Long kidId = volunToKid.getKidId();
//            LambdaQueryWrapper<Kid> kidQuery = new LambdaQueryWrapper<>();
//            kidQuery.eq(Kid::getId,kidId);
//            Kid kid = kidMapper.selectOne(kidQuery);
//            kidRecDto.setName(kid.getName());
//            kidRecDto.setPhoto(kid.getPhoto());
//
//
//
//
//        }).collect(co)
        KidRecDto kidRecDto = new KidRecDto();
        Long volunId = BaseContext.getCurrentIUserDetails().getId();
        List<Kid> kids = volunteerStatisticMapper.volunKid(volunId);
        List<Long> kidIds = kids.stream().map(kid -> {
            return kid.getId();
        }).collect(Collectors.toList());
        Map<String, Object> newDonation = volunteerStatisticMapper.newDonation(kidIds);
        Map<String, Object> newThingDonation = volunteerStatisticMapper.newThingDonation(kidIds);
        LocalDateTime timeThing = (LocalDateTime)newThingDonation.get("time");
        LocalDateTime timeMoney = (LocalDateTime)newDonation.get("time");
        if (timeMoney.isAfter(timeThing)){
            Long kidId = (Long)newDonation.get("kid_id");
             LambdaQueryWrapper<Kid> kidQuery = new LambdaQueryWrapper<>();
             kidQuery.eq(Kid::getId,kidId);
            Kid kid = kidMapper.selectOne(kidQuery);
            kidRecDto.setName(kid.getName());
            kidRecDto.setPhoto(kid.getPhoto());
            kidRecDto.setRecent(kid.getName()+"在通过了自己的努力后，于"+timeMoney
                    +"获得了"
                    +newDonation.get("nickname")
                    +"的现金资助："
                    +newDonation.get("amount")+"元");
        }else {
            Long kidId = (Long)newThingDonation.get("kid_id");
            LambdaQueryWrapper<Kid> kidQuery = new LambdaQueryWrapper<>();
            kidQuery.eq(Kid::getId,kidId);
            Kid kid = kidMapper.selectOne(kidQuery);
            kidRecDto.setName(kid.getName());
            kidRecDto.setPhoto(kid.getPhoto());
            kidRecDto.setRecent(kid.getName()
                    +"在通过了自己的努力后，于"
                    +timeThing
                    +"获得了"
                    +newThingDonation.get("nickname")
                    +"的物品资助:"
                    +newThingDonation.get("thing_name"));
        }


        return kidRecDto;
    }

    @Override
    public List<Kid> getAllKidInfo() {
//        Long volunId = BaseContext.getCurrentIUserDetails().getId();
//        LambdaQueryWrapper<VolunToKid> volunQuery = new LambdaQueryWrapper<>();
//        volunQuery.eq(VolunToKid::getVolunId,volunId);
//        List<VolunToKid> volunToKids = volunToKidMapper.selectList(volunQuery);
//
//        List<Kid> kids = volunToKids.stream().map(volunToKid -> {
//
//            Long kidId = volunToKid.getKidId();
//            LambdaQueryWrapper<Kid> kidQuery = new LambdaQueryWrapper<>();
//            kidQuery.eq(Kid::getId, kidId);
//            Kid kid = kidMapper.selectOne(kidQuery);
//            return kid;
//
//        }).collect(Collectors.toList());

        Long volunId = BaseContext.getCurrentIUserDetails().getId();
        return volunteerStatisticMapper.volunKid(volunId);
    }
}
