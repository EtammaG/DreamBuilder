package com.neu.dreambuilder.service.kid;

import com.neu.dreambuilder.dto.kid.AwardDto;
import com.neu.dreambuilder.dto.kid.AwardExchangeDto;
import com.neu.dreambuilder.entity.kid.Award;
import com.neu.dreambuilder.entity.kid.AwardExample;
import com.neu.dreambuilder.entity.kid.AwardType;

import java.util.List;

public interface AwardService {
    List<AwardType> getAllType();

    List<AwardDto> search(Long id, AwardExample example);

    void like(Long id, long awardId);

    List<Award> getLike(Long id);

    void exchange(Long id, long awardId);

    List<AwardExchangeDto> getExchange(Long id);

}
