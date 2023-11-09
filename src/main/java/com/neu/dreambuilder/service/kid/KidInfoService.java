package com.neu.dreambuilder.service.kid;

import com.neu.dreambuilder.dto.kid.*;
import com.neu.dreambuilder.entity.kid.Type;

import java.util.List;

public interface KidInfoService {

    List<KidRecDto> getRandomRecs();

    List<Type> getAllType();

    KidDto getById(Long id);

    List<KidSimDto> getSimByType(Long typeId);

    List<KidSimDto> getSim();

    KidMisDto getMis(Long kidId);

    KidMalDto getMal(Long kidId);

    KidMeeDto getMee(Long kidId);

    KidRecDto getRec(Long kidId);

}
