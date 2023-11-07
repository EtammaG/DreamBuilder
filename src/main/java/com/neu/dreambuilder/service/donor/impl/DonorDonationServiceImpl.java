package com.neu.dreambuilder.service.donor.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.neu.dreambuilder.common.utils.BaseContext;
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
    public void addMoney(String kidId, int amount) {
        kidDonationMapper.insert(new KidDonation(
                BaseContext.getCurrentIUserDetails().getId(),
                Long.parseLong(kidId),
                amount,
                LocalDateTime.now()));
    }

    @Override
    public void addThing(String kidId, String name) {
        kidThingMapper.insert(new KidThing(
                BaseContext.getCurrentIUserDetails().getId(),
                Long.parseLong(kidId),
                name,
                LocalDateTime.now()));
    }

    @Override
    public void addProject(String projectId, int amount) {
        projectDonationMapper.insert(new ProjectDonation(
                BaseContext.getCurrentIUserDetails().getId(),
                Long.parseLong(projectId),
                amount,
                LocalDateTime.now()
        ));
    }

    @Override
    public DonationStaDto getStatistic() {
        return donationStatisticMapper.selectStaByDonorId(BaseContext.getCurrentIUserDetails().getId());
    }

    @Override
    public List<KidDonationDto> getMoney() {
        LambdaQueryWrapper<KidDonation> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .eq(KidDonation::getDonorId, BaseContext.getCurrentIUserDetails().getId());
        List<KidDonation> kidDonations = kidDonationMapper.selectList(wrapper);
        Map<Long, Kid> kids = kidMapper.selectByIds(kidDonations.stream().map(KidDonation::getKidId).toList());
        List<KidDonationDto> res = new ArrayList<>(kidDonations.size());
        for(KidDonation kidDonation : kidDonations) {
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
    public List<KidThingDto> getThing() {
        LambdaQueryWrapper<KidThing> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .eq(KidThing::getDonorId, BaseContext.getCurrentIUserDetails().getId());
        List<KidThing> kidThings = kidThingMapper.selectList(wrapper);
        Map<Long, Kid> kids = kidMapper.selectByIds(kidThings.stream().map(KidThing::getKidId).toList());
        List<KidThingDto> res = new ArrayList<>(kidThings.size());
        for(KidThing kidThing : kidThings) {
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
    public List<ProjectDonationDto> getProject() {
        LambdaQueryWrapper<ProjectDonation> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .eq(ProjectDonation::getDonorId, BaseContext.getCurrentIUserDetails().getId());
        List<ProjectDonation> projectDonations = projectDonationMapper.selectList(wrapper);
        Map<Long, Project> projects = projectMapper.selectByIds(projectDonations.stream().map(ProjectDonation::getProjectId).toList());
        List<ProjectDonationDto> res = new ArrayList<>(projectDonations.size());
        for(ProjectDonation projectDonation : projectDonations) {
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
