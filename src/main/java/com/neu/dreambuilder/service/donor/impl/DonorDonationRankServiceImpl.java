package com.neu.dreambuilder.service.donor.impl;

import com.neu.dreambuilder.dto.donor.DonationStaDto;
import com.neu.dreambuilder.mapper.donor.DonationStatisticMapper;
import com.neu.dreambuilder.service.donor.DonorDonationRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DonorDonationRankServiceImpl implements DonorDonationRankService {

    private final DonationStatisticMapper donationStatisticMapper;

    @Autowired
    public DonorDonationRankServiceImpl(DonationStatisticMapper donationStatisticMapper) {
        this.donationStatisticMapper = donationStatisticMapper;
    }

    @Override
    public List<DonationStaDto> all() {
        return donationStatisticMapper.selectRank(50);
    }

    @Override
    public List<DonationStaDto> month() {
        LocalDate now = LocalDate.now();
        return donationStatisticMapper.selectRankByDate(50, now.minusMonths(1), now);
    }

    @Override
    public List<DonationStaDto> week() {
        LocalDate now = LocalDate.now();
        return donationStatisticMapper.selectRankByDate(50, now.minusWeeks(1), now);
    }
}
