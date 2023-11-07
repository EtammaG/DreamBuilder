package com.neu.dreambuilder.mapper.donor;

import com.neu.dreambuilder.dto.donor.DonationStaDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface DonationStatisticMapper {

    DonationStaDto selectStaByDonorId(@Param("donorId") Long donorId);

    List<DonationStaDto> selectRank(@Param("num") int num);

    List<DonationStaDto> selectRankByDate(@Param("num") int num,
                                          @Param("startDate") LocalDate startDate,
                                          @Param("endDate") LocalDate endDate);

    Integer selectNumOfKid();
}
