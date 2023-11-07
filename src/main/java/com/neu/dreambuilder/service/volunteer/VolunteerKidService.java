package com.neu.dreambuilder.service.volunteer;

import com.neu.dreambuilder.dto.kid.KidDto;
import com.neu.dreambuilder.dto.kid.KidRecDto;
import com.neu.dreambuilder.entity.kid.Kid;

import java.util.List;

public interface VolunteerKidService {
    KidRecDto getKidRandomRec();

    List<Kid> getAllKidInfo();
}
