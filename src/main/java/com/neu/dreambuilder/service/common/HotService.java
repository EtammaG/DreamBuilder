package com.neu.dreambuilder.service.common;

import com.neu.dreambuilder.dto.DesDto;
import com.neu.dreambuilder.dto.kid.KidRecDto;

import java.util.List;

public interface HotService {
    DesDto getSysDes();

    List<KidRecDto> getKidsRec();
}
