package com.neu.dreambuilder.service.kid.impl;

import com.neu.dreambuilder.dto.kid.AwardDto;
import com.neu.dreambuilder.dto.kid.AwardExchangeDto;
import com.neu.dreambuilder.entity.kid.*;
import com.neu.dreambuilder.exception.bean.CustomException;
import com.neu.dreambuilder.mapper.kid.*;
import com.neu.dreambuilder.service.kid.AwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AwardServiceImpl implements AwardService {

    private final AwardTypeMapper awardTypeMapper;
    private final AwardLikeMapper awardLikeMapper;
    private final AwardExchangeMapper awardExchangeMapper;
    private final AwardInfoMapper awardInfoMapper;

    private final KidMapper kidMapper;

    @Autowired
    public AwardServiceImpl(AwardTypeMapper awardTypeMapper, AwardLikeMapper awardLikeMapper, AwardExchangeMapper awardExchangeMapper, AwardInfoMapper awardInfoMapper, KidMapper kidMapper) {
        this.awardTypeMapper = awardTypeMapper;
        this.awardLikeMapper = awardLikeMapper;
        this.awardExchangeMapper = awardExchangeMapper;
        this.awardInfoMapper = awardInfoMapper;
        this.kidMapper = kidMapper;
    }

    @Override
    public List<AwardType> getAllType() {
        return awardTypeMapper.selectAll();
    }

    @Override
    public List<AwardDto> search(Long id, AwardExample example) {
        String name = example.getName();
        name = name == null ? "%%" : "%" + name + "%";
        if(example.getTypeId() == null) throw new CustomException("奖品类别不能为空");
        return awardInfoMapper.search(id, name, example.getTypeId());
    }

    @Override
    public void like(Long id, long awardId) {
        AwardLike awardLike = new AwardLike(awardId, id);
        if(awardLikeMapper.cat(awardLike) != null) throw new CustomException("不能重复收藏");
        awardLikeMapper.insert(awardLike);
    }

    @Override
    public List<Award> getLike(Long id) {
        return awardInfoMapper.selectLike(id);
    }

    @Override
    public void exchange(Long id, long awardId) {
        Kid kid = kidMapper.selectById(id);
        awardExchangeMapper.insert(
                new AwardExchange(awardId, kid.getId(), kid.getAddress(), LocalDateTime.now()));
    }

    @Override
    public List<AwardExchangeDto> getExchange(Long id) {
        return awardInfoMapper.selectExchanges(id);
    }
}
