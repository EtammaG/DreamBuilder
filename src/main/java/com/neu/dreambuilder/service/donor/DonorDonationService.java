package com.neu.dreambuilder.service.donor;

import com.neu.dreambuilder.dto.donor.DonationStaDto;
import com.neu.dreambuilder.dto.donor.KidDonationDto;
import com.neu.dreambuilder.dto.donor.KidThingDto;
import com.neu.dreambuilder.dto.donor.ProjectDonationDto;

import java.util.List;

public interface DonorDonationService {

    void addMoney(Long donorId, Long kidId, int amount);

    void addThing(Long donorId, Long kidId, String name);

    void addProject(Long donorId, Long projectId, int amount);

    DonationStaDto getStatistic(Long donorId);

    List<KidDonationDto> getMoney(Long donorId);

    List<KidThingDto> getThing(Long donorId);

    List<ProjectDonationDto> getProject(Long donorId);
}
