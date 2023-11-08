package com.neu.dreambuilder.controller.kid;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.neu.dreambuilder.common.utils.BaseContext;
import com.neu.dreambuilder.dto.PageExample;
import com.neu.dreambuilder.dto.Result;
import com.neu.dreambuilder.dto.kid.AwardDto;
import com.neu.dreambuilder.dto.kid.AwardExchangeDto;
import com.neu.dreambuilder.entity.kid.Award;
import com.neu.dreambuilder.entity.kid.AwardExample;
import com.neu.dreambuilder.entity.kid.AwardType;
import com.neu.dreambuilder.service.kid.AwardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kid/award")
@PreAuthorize("hasAuthority('KID')")
@Api(tags = "孩子端孩子奖品相关接口")
public class KidAwardController {

    private final AwardService awardService;

    @Autowired
    public KidAwardController(AwardService awardService) {
        this.awardService = awardService;
    }

    @GetMapping("/type")
    @ApiOperation("获取奖品类别列表")
    public Result<List<AwardType>> type() {
        return Result.success(awardService.getAllType());
    }

    @PostMapping("/search")
    @ApiOperation("获取指定奖品列表")
    public Result<PageInfo<AwardDto>> search(@RequestBody PageExample<AwardExample> pageExample) {
        PageHelper.startPage(pageExample.getPageNum(), pageExample.getPageSize());
        return Result.success(new PageInfo<>(awardService.search(
                BaseContext.getCurrentIUserDetails().getId(),
                pageExample.getExample())));
    }

    @PostMapping("/like")
    @ApiOperation("收藏奖品")
    public Result<Object> addLike(@ApiParam(value = "奖品ID") String awardId) {
        awardService.like(BaseContext.getCurrentIUserDetails().getId(), Long.parseLong(awardId));
        return Result.success();
    }

    @GetMapping("/like")
    @ApiOperation("获得收藏的奖品")
    public Result<PageInfo<Award>> getLike(PageExample<Object> pageExample) {
        PageHelper.startPage(pageExample.getPageNum(), pageExample.getPageSize());
        return Result.success(new PageInfo<>(awardService.getLike(BaseContext.getCurrentIUserDetails().getId())));
    }

    @PostMapping("/exchange")
    @ApiOperation("兑换奖品")
    public Result<Object> addExchange(@ApiParam(value = "奖品ID") String awardId) {
        awardService.exchange(BaseContext.getCurrentIUserDetails().getId(), Long.parseLong(awardId));
        return Result.success();
    }

    @GetMapping("/exchange")
    @ApiOperation("获得兑换奖品的记录")
    public Result<PageInfo<AwardExchangeDto>> getExchange(PageExample<Object> pageExample) {
        PageHelper.startPage(pageExample.getPageNum(), pageExample.getPageSize());
        return Result.success(new PageInfo<>(awardService.getExchange(BaseContext.getCurrentIUserDetails().getId())));
    }


}
