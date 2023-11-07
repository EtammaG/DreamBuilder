package com.neu.dreambuilder.service.volunteer;

import com.neu.dreambuilder.dto.mission.MissionDto;
import com.neu.dreambuilder.dto.mission.MissionVolViewDto;

public interface VolunteerMissionService {
    MissionVolViewDto getRandomMission();
}
