package com.neu.dreambuilder.service.donor.impl;

import com.neu.dreambuilder.dto.donor.ProjectSimDto;
import com.neu.dreambuilder.entity.donor.Project;
import com.neu.dreambuilder.entity.donor.ProjectType;
import com.neu.dreambuilder.mapper.donor.ProjectInfoMapper;
import com.neu.dreambuilder.mapper.donor.ProjectMapper;
import com.neu.dreambuilder.mapper.donor.ProjectTypeMapper;
import com.neu.dreambuilder.service.donor.DonorProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonorProjectServiceImpl implements DonorProjectService {

    private final ProjectTypeMapper projectTypeMapper;
    private final ProjectInfoMapper projectInfoMapper;
    private final ProjectMapper projectMapper;

    @Autowired
    public DonorProjectServiceImpl(ProjectTypeMapper projectTypeMapper, ProjectInfoMapper projectInfoMapper, ProjectMapper projectMapper) {
        this.projectTypeMapper = projectTypeMapper;
        this.projectInfoMapper = projectInfoMapper;
        this.projectMapper = projectMapper;
    }

    @Override
    public List<ProjectType> getAllType() {
        return projectTypeMapper.selectAll();
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
}
