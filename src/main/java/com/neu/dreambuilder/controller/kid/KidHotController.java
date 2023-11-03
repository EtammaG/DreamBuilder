package com.neu.dreambuilder.controller.kid;

import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.dto.kid.KidRecent;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kid/hot")
@PreAuthorize("hasAuthority('KID')")
public class KidHotController {

    @GetMapping("/recent")
    public Result<KidRecent> get() {
        return null;
    }

}
