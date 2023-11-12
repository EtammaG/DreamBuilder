package com.neu.dreambuilder.service.donor;

import com.neu.dreambuilder.dto.donor.DonationStaDto;

import java.util.List;

public interface DonorDonationRankService {
    List<DonationStaDto> all();

    List<DonationStaDto> month();

    List<DonationStaDto> week();

    void update(Long donorId);
}
