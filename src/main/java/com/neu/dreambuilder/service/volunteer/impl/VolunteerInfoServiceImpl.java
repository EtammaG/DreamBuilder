package com.neu.dreambuilder.service.volunteer.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.neu.dreambuilder.common.utils.BaseContext;
import com.neu.dreambuilder.entity.volunteer.Volunteer;
import com.neu.dreambuilder.mapper.volunteer.VolunteerMapper;
import com.neu.dreambuilder.service.volunteer.VolunteerInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class VolunteerInfoServiceImpl implements VolunteerInfoService {

    @Resource
    private VolunteerMapper volunteerMapper;
    @Override
    public Volunteer getVolunteerInfo() {
        Long id = BaseContext.getCurrentIUserDetails().getId();
        LambdaQueryWrapper<Volunteer> volunQuery = new LambdaQueryWrapper<>();

        volunQuery.eq(Volunteer::getId,id);

        return  volunteerMapper.selectOne(volunQuery);
    }
}
