package com.neu.dreambuilder.service.donor;

import com.neu.dreambuilder.dto.donor.DonationStaDto;
import com.neu.dreambuilder.dto.donor.KidDonationDto;
import com.neu.dreambuilder.dto.donor.KidThingDto;
import com.neu.dreambuilder.dto.donor.ProjectDonationDto;

import java.util.List;

public interface DonorDonationService {
    void addMoney(String kidId, int amount);

    void addThing(String kidId, String name);

    void addProject(String projectId, int amount);

    DonationStaDto getStatistic();

    List<KidDonationDto> getMoney();

    List<KidThingDto> getThing();

    List<ProjectDonationDto> getProject();
}
