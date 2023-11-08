package com.neu.dreambuilder.mapper.kid;

import com.neu.dreambuilder.dto.kid.KidMalDto;
import com.neu.dreambuilder.dto.kid.KidSimDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface KidInfoMapper {
    List<KidSimDto> selectSimByTypeId(Long typeId);

    List<Map<String, Object>> selectMisInfo(Long id);

    KidMalDto selectMalInfo(Long id);

    Integer selectRankById(Long id);

    Integer selectWeekrankById(Long id);

    List<KidSimDto> selectSim();
}
