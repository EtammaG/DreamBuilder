package com.neu.dreambuilder.mapper.donor;

import com.neu.dreambuilder.dto.donor.KidDonationDto;
import com.neu.dreambuilder.dto.donor.KidThingDto;
import com.neu.dreambuilder.dto.donor.ProjectDonationDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DonationMapper {
    List<KidDonationDto> selectKidDonation(Long donorId);

    List<KidThingDto> selectKidThing(Long donorId);

    List<ProjectDonationDto> selectProjectDonation(Long donorId);
}
