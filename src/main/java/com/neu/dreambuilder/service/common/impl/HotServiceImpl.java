package com.neu.dreambuilder.service.common.impl;

import com.neu.dreambuilder.dto.DesDto;
import com.neu.dreambuilder.mapper.donor.DonationStatisticMapper;
import com.neu.dreambuilder.mapper.donor.KidDonationMapper;
import com.neu.dreambuilder.mapper.donor.ProjectDonationMapper;
import com.neu.dreambuilder.service.common.HotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HotServiceImpl implements HotService {

    private final DonationStatisticMapper donationStatisticMapper;

    private final KidDonationMapper kidDonationMapper;
    private final ProjectDonationMapper projectDonationMapper;

    @Autowired
    public HotServiceImpl(DonationStatisticMapper donationStatisticMapper, KidDonationMapper kidDonationMapper, ProjectDonationMapper projectDonationMapper) {
        this.donationStatisticMapper = donationStatisticMapper;
        this.kidDonationMapper = kidDonationMapper;
        this.projectDonationMapper = projectDonationMapper;
    }

    @Override
    public DesDto getSysDes() {
        return new DesDto(
                kidDonationMapper.selectTotalAmount() + projectDonationMapper.selectTotalAmount(),
                donationStatisticMapper.selectNumOfKid()
        );
    }

}
