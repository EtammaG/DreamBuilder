package com.neu.dreambuilder.mapper.donor;

import com.neu.dreambuilder.dto.donor.ProjectSimDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProjectInfoMapper {
    List<ProjectSimDto> selectSimByType(Long typeId);

    List<ProjectSimDto> selectSim();
}
