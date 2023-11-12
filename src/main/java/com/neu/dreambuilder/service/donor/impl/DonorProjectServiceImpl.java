package com.neu.dreambuilder.service.donor.impl;

import com.alibaba.fastjson.JSON;
import com.neu.dreambuilder.dto.donor.ProjectSimDto;
import com.neu.dreambuilder.entity.donor.Project;
import com.neu.dreambuilder.entity.donor.ProjectType;
import com.neu.dreambuilder.mapper.donor.ProjectInfoMapper;
import com.neu.dreambuilder.mapper.donor.ProjectMapper;
import com.neu.dreambuilder.mapper.donor.ProjectTypeMapper;
import com.neu.dreambuilder.service.donor.DonorProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DonorProjectServiceImpl implements DonorProjectService {

    private final String redisPrefix;
    private final String typeKey;

    private final ProjectTypeMapper projectTypeMapper;
    private final ProjectInfoMapper projectInfoMapper;
    private final ProjectMapper projectMapper;

    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public DonorProjectServiceImpl(
            @Value("${dream-builder.redis.prefix.donor.project}") String redisPrefix,
            ProjectTypeMapper projectTypeMapper, ProjectInfoMapper projectInfoMapper, ProjectMapper projectMapper, StringRedisTemplate stringRedisTemplate) {
        this.redisPrefix = redisPrefix;
        this.projectTypeMapper = projectTypeMapper;
        this.projectInfoMapper = projectInfoMapper;
        this.projectMapper = projectMapper;
        this.stringRedisTemplate = stringRedisTemplate;
        this.typeKey = redisPrefix + "type";
    }

    @Override
    public List<ProjectType> getAllType() {
        return JSON.parseArray(stringRedisTemplate.opsForValue().get(typeKey), ProjectType.class);
    }

    @Override
    public List<ProjectSimDto> getSimByType(Long typeId) {
        return projectInfoMapper.selectSimByType(typeId);
    }

    @Override
    public List<ProjectSimDto> getSim() {
        return projectInfoMapper.selectSim();
    }

    @Override
    public Project getById(String id) {
        return projectMapper.selectById(id);
    }

    @Transactional
//    @Scheduled(cron = "* * * * * *")
    public void updateCache() {
        // todo 使用缓存就不能在controller使用分页了
        updateCacheType();
    }

    private void updateCacheType() {
        List<ProjectType> types = projectTypeMapper.selectAll();
        stringRedisTemplate.opsForValue().set(typeKey, JSON.toJSONString(types));
    }

}
