package com.neu.dreambuilder.service.donor;

import com.neu.dreambuilder.dto.donor.ProjectSimDto;
import com.neu.dreambuilder.entity.donor.Project;
import com.neu.dreambuilder.entity.donor.ProjectType;

import java.util.List;

public interface DonorProjectService {
    List<ProjectType> getAllType();

    List<ProjectSimDto> getSimByType(Long typeId);

    List<ProjectSimDto> getSim();

    Project getById(String id);

}
