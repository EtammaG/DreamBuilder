package com.neu.dreambuilder.controller.kid;

import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.dto.kid.KidRecDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/kid/hot")
@PreAuthorize("hasAuthority('KID')")
public class KidHotController {

    @GetMapping("/recent")
    @ApiOperation("获取某些孩子近况列表")
    public Result<List<KidRecDto>> get() {
        return null;
    }

}
