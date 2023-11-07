package com.neu.dreambuilder.service.volunteer.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.neu.dreambuilder.common.utils.BaseContext;
import com.neu.dreambuilder.dto.kid.KidDto;
import com.neu.dreambuilder.dto.kid.KidRecDto;
import com.neu.dreambuilder.entity.kid.Kid;
import com.neu.dreambuilder.entity.volunteer.VolunToKid;
import com.neu.dreambuilder.mapper.kid.KidMapper;
import com.neu.dreambuilder.mapper.volunteer.VolunToKidMapper;
import com.neu.dreambuilder.service.volunteer.VolunteerKidService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VolunteerKidServiceImpl implements VolunteerKidService {
    @Resource
    private VolunToKidMapper volunToKidMapper;

    @Resource
    private KidMapper kidMapper;
    @Override
    public KidRecDto getKidRandomRec() {
        Long volunId = BaseContext.getCurrentIUserDetails().getId();
        LambdaQueryWrapper<VolunToKid> volunQuery = new LambdaQueryWrapper<>();
        volunQuery.eq(VolunToKid::getVolunId,volunId);
        List<VolunToKid> volunToKids = volunToKidMapper.selectList(volunQuery);

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


        return null;
    }

    @Override
    public List<Kid> getAllKidInfo() {
        Long volunId = BaseContext.getCurrentIUserDetails().getId();
        LambdaQueryWrapper<VolunToKid> volunQuery = new LambdaQueryWrapper<>();
        volunQuery.eq(VolunToKid::getVolunId,volunId);
        List<VolunToKid> volunToKids = volunToKidMapper.selectList(volunQuery);

        List<Kid> kids = volunToKids.stream().map(volunToKid -> {

            Long kidId = volunToKid.getKidId();
            LambdaQueryWrapper<Kid> kidQuery = new LambdaQueryWrapper<>();
            kidQuery.eq(Kid::getId, kidId);
            Kid kid = kidMapper.selectOne(kidQuery);
            return kid;

        }).collect(Collectors.toList());

        return kids;
    }
}
