package com.neu.dreambuilder.mapper.donor;

import com.neu.dreambuilder.dto.donor.DonationStaDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DonationStatisticMapperTest {

    @Autowired
    private DonationStatisticMapper donationStatisticMapper;

    @Test
    void selectStatistic() {
        DonationStaDto donationStaDto = donationStatisticMapper.selectStaByDonorId(1L);
        DonationStaDto donationStaDto1 = donationStatisticMapper.selectStaByDonorId(2L);
        System.out.println();
    }
}