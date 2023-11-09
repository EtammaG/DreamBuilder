package com.neu.dreambuilder.service.donor.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.neu.dreambuilder.dto.donor.DonationStaDto;
import com.neu.dreambuilder.dto.donor.KidDonationDto;
import com.neu.dreambuilder.dto.donor.KidThingDto;
import com.neu.dreambuilder.dto.donor.ProjectDonationDto;
import com.neu.dreambuilder.entity.donor.KidDonation;
import com.neu.dreambuilder.entity.donor.KidThing;
import com.neu.dreambuilder.entity.donor.Project;
import com.neu.dreambuilder.entity.donor.ProjectDonation;
import com.neu.dreambuilder.entity.kid.Kid;
import com.neu.dreambuilder.mapper.donor.*;
import com.neu.dreambuilder.mapper.kid.KidMapper;
import com.neu.dreambuilder.service.donor.DonorDonationService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DonorDonationServiceImpl implements DonorDonationService {

    private final KidDonationMapper kidDonationMapper;
    private final KidThingMapper kidThingMapper;
    private final ProjectDonationMapper projectDonationMapper;

    private final DonationStatisticMapper donationStatisticMapper;

    private final KidMapper kidMapper;
    private final ProjectMapper projectMapper;

    @Autowired
    public DonorDonationServiceImpl(KidDonationMapper kidDonationMapper, KidThingMapper kidThingMapper, ProjectDonationMapper projectDonationMapper, DonationStatisticMapper donationStatisticMapper, KidMapper kidMapper, ProjectMapper projectMapper) {
        this.kidDonationMapper = kidDonationMapper;
        this.kidThingMapper = kidThingMapper;
        this.projectDonationMapper = projectDonationMapper;
        this.donationStatisticMapper = donationStatisticMapper;
        this.kidMapper = kidMapper;
        this.projectMapper = projectMapper;
    }

    @Override
    public void addMoney(Long donorId, Long kidId, int amount) {
        kidDonationMapper.insert(new KidDonation(
                donorId,
                kidId,
                amount,
                LocalDateTime.now()));
    }

    @Override
    public void addThing(Long donorId, Long kidId, String name) {
        kidThingMapper.insert(new KidThing(
                donorId,
                kidId,
                name,
                LocalDateTime.now()));
    }

    @Override
    public void addProject(Long donorId, Long projectId, int amount) {
        projectDonationMapper.insert(new ProjectDonation(
                donorId,
                projectId,
                amount,
                LocalDateTime.now()
        ));
    }

    @Override
    public DonationStaDto getStatistic(Long donorId) {
        return donationStatisticMapper.selectStaByDonorId(donorId);
    }

    @Override
    public List<KidDonationDto> getMoney(Long donorId) {
        LambdaQueryWrapper<KidDonation> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .eq(KidDonation::getDonorId, donorId);
        List<KidDonation> kidDonations = kidDonationMapper.selectList(wrapper);
        Map<Long, Kid> kids = kidMapper.selectByIds(kidDonations.stream().map(KidDonation::getKidId).toList());
        List<KidDonationDto> res = new ArrayList<>(kidDonations.size());
        for (KidDonation kidDonation : kidDonations) {
            Kid kid = kids.get(kidDonation.getKidId());
            KidDonationDto kidDonationDto = new KidDonationDto();
            BeanUtils.copyProperties(kidDonation, kidDonationDto);
            kidDonationDto.setKidName(kid.getName());
            kidDonationDto.setKidPhoto(kid.getPhoto());
            res.add(kidDonationDto);
        }
        return res;
    }

    @Override
    public List<KidThingDto> getThing(Long donorId) {
        LambdaQueryWrapper<KidThing> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .eq(KidThing::getDonorId, donorId);
        List<KidThing> kidThings = kidThingMapper.selectList(wrapper);
        Map<Long, Kid> kids = kidMapper.selectByIds(kidThings.stream().map(KidThing::getKidId).toList());
        List<KidThingDto> res = new ArrayList<>(kidThings.size());
        for (KidThing kidThing : kidThings) {
            Kid kid = kids.get(kidThing.getKidId());
            KidThingDto kidThingDto = new KidThingDto();
            BeanUtils.copyProperties(kidThing, kidThingDto);
            kidThingDto.setKidName(kid.getName());
            kidThingDto.setKidPhoto(kid.getPhoto());
            res.add(kidThingDto);
        }
        return res;
    }

    @Override
    public List<ProjectDonationDto> getProject(Long donorId) {
        LambdaQueryWrapper<ProjectDonation> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .eq(ProjectDonation::getDonorId, donorId);
        List<ProjectDonation> projectDonations = projectDonationMapper.selectList(wrapper);
        Map<Long, Project> projects = projectMapper.selectByIds(projectDonations.stream().map(ProjectDonation::getProjectId).toList());
        List<ProjectDonationDto> res = new ArrayList<>(projectDonations.size());
        for (ProjectDonation projectDonation : projectDonations) {
            Project project = projects.get(projectDonation.getProjectId());
            ProjectDonationDto projectDonationDto = new ProjectDonationDto();
            BeanUtils.copyProperties(projectDonation, projectDonationDto);
            projectDonationDto.setProjectName(project.getName());
            projectDonationDto.setProjectPic(project.getPic());
            res.add(projectDonationDto);
        }
        return res;
    }
}
