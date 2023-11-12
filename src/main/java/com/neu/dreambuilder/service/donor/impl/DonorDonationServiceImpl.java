package com.neu.dreambuilder.service.donor.impl;

import com.neu.dreambuilder.dto.donor.DonationStaDto;
import com.neu.dreambuilder.dto.donor.KidDonationDto;
import com.neu.dreambuilder.dto.donor.KidThingDto;
import com.neu.dreambuilder.dto.donor.ProjectDonationDto;
import com.neu.dreambuilder.entity.donor.KidDonation;
import com.neu.dreambuilder.entity.donor.KidThing;
import com.neu.dreambuilder.entity.donor.ProjectDonation;
import com.neu.dreambuilder.mapper.donor.*;
import com.neu.dreambuilder.mapper.kid.KidMapper;
import com.neu.dreambuilder.service.donor.DonorDonationRankService;
import com.neu.dreambuilder.service.donor.DonorDonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class DonorDonationServiceImpl implements DonorDonationService {

    private final KidDonationMapper kidDonationMapper;
    private final KidThingMapper kidThingMapper;
    private final ProjectDonationMapper projectDonationMapper;

    private final DonationStatisticMapper donationStatisticMapper;
    private final DonationMapper donationMapper;

    private final KidMapper kidMapper;
    private final ProjectMapper projectMapper;

    private final DonorDonationRankService donorDonationRankService;

    @Autowired
    public DonorDonationServiceImpl(KidDonationMapper kidDonationMapper, KidThingMapper kidThingMapper, ProjectDonationMapper projectDonationMapper, DonationStatisticMapper donationStatisticMapper, DonationMapper donationMapper, KidMapper kidMapper, ProjectMapper projectMapper, DonorDonationRankService donorDonationRankService) {
        this.kidDonationMapper = kidDonationMapper;
        this.kidThingMapper = kidThingMapper;
        this.projectDonationMapper = projectDonationMapper;
        this.donationStatisticMapper = donationStatisticMapper;
        this.donationMapper = donationMapper;
        this.kidMapper = kidMapper;
        this.projectMapper = projectMapper;
        this.donorDonationRankService = donorDonationRankService;
    }

    @Override
    @Transactional
    public void addMoney(Long donorId, Long kidId, int amount) {
        kidDonationMapper.insert(new KidDonation(
                donorId,
                kidId,
                amount,
                LocalDateTime.now()));
        donorDonationRankService.update(donorId);
    }

    @Override
    @Transactional
    public void addThing(Long donorId, Long kidId, String name) {
        kidThingMapper.insert(new KidThing(
                donorId,
                kidId,
                name,
                LocalDateTime.now()));
    }

    @Override
    @Transactional
    public void addProject(Long donorId, Long projectId, int amount) {
        projectDonationMapper.insert(new ProjectDonation(
                projectId,
                donorId,
                amount,
                LocalDateTime.now()
        ));
        donorDonationRankService.update(donorId);
    }

    @Override
    public DonationStaDto getStatistic(Long donorId) {
        return donationStatisticMapper.selectSta(donorId);
    }

    @Override
    public List<KidDonationDto> getMoney(Long donorId) {
        return donationMapper.selectKidDonation(donorId);
    }

    @Override
    public List<KidThingDto> getThing(Long donorId) {
        return donationMapper.selectKidThing(donorId);
    }

    @Override
    public List<ProjectDonationDto> getProject(Long donorId) {
        return donationMapper.selectProjectDonation(donorId);
    }
}
