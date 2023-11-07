package com.neu.dreambuilder.service.common.impl;

import com.neu.dreambuilder.dto.DesDto;
import com.neu.dreambuilder.dto.kid.KidRecDto;
import com.neu.dreambuilder.mapper.donor.*;
import com.neu.dreambuilder.mapper.kid.KidMapper;
import com.neu.dreambuilder.service.common.HotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotServiceImpl implements HotService {

    private final DonationStatisticMapper donationStatisticMapper;

    private final KidDonationMapper kidDonationMapper;
    private final ProjectDonationMapper projectDonationMapper;

    private final KidMapper kidMapper;

    @Autowired
    public HotServiceImpl(DonationStatisticMapper donationStatisticMapper, KidDonationMapper kidDonationMapper, ProjectDonationMapper projectDonationMapper, KidMapper kidMapper) {
        this.donationStatisticMapper = donationStatisticMapper;
        this.kidDonationMapper = kidDonationMapper;
        this.projectDonationMapper = projectDonationMapper;
        this.kidMapper = kidMapper;
    }

    @Override
    public DesDto getSysDes() {
        return new DesDto(
                kidDonationMapper.selectTotalAmount() + projectDonationMapper.selectTotalAmount(),
                donationStatisticMapper.selectNumOfKid()
        );
    }

    @Override
    public List<KidRecDto> getKidsRec() {
        return null;
    }
}
