package com.neu.dreambuilder.service.volunteer;

import com.neu.dreambuilder.dto.kid.KidVieDto;
import com.neu.dreambuilder.dto.mission.MissionDto;
import com.neu.dreambuilder.dto.mission.MissionVolViewDto;

import java.util.List;
import java.util.Map;

public interface VolunteerMissionService {
    MissionVolViewDto getRandomMission();

    List<MissionVolViewDto> getAllMission();

    List<KidVieDto> getKidInfo(Long missionId);

    MissionDto getMissionDetail(long missionId);

    void putScore(Long missionId, Long kidId, int score);

    Map<String,Long> getMissionTatal();
}
