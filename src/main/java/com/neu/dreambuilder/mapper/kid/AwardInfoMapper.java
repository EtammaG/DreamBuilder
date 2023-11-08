package com.neu.dreambuilder.mapper.kid;

import com.neu.dreambuilder.dto.kid.AwardDto;
import com.neu.dreambuilder.dto.kid.AwardExchangeDto;
import com.neu.dreambuilder.entity.kid.Award;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AwardInfoMapper {
    List<AwardDto> search(@Param("id") Long id, @Param("name") String name, @Param("typeId") Long typeId);

    List<Award> selectLike(Long id);

    List<AwardExchangeDto> selectExchanges(Long id);

}
