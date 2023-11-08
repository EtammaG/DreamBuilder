package com.neu.dreambuilder.service.mission;

import com.neu.dreambuilder.dto.mission.MissionDto;
import com.neu.dreambuilder.dto.mission.MissionKidViewDto;

import java.time.LocalDate;
import java.util.List;

public interface MissionInfoService {
    List<MissionKidViewDto> getKidViewByKidIdAndDate(Long kidId, LocalDate date);

    MissionDto getById(Long id);
}
