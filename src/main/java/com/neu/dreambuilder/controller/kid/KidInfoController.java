package com.neu.dreambuilder.controller.kid;

import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.dto.kid.KidDto;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kid/info")
@PreAuthorize("hasAuthority('KID')")
public class KidInfoController {

    @GetMapping("mission")
    @ApiModelProperty("获取任务的信息")
    public Result<KidDto> mission() {
        return null;
    }

}
