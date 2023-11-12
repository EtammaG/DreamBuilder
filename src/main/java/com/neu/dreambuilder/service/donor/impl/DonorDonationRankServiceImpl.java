package com.neu.dreambuilder.service.donor.impl;

import com.alibaba.fastjson.JSON;
import com.neu.dreambuilder.common.utils.TranCacheUtil;
import com.neu.dreambuilder.dto.donor.DonationStaDto;
import com.neu.dreambuilder.mapper.donor.DonationStatisticMapper;
import com.neu.dreambuilder.service.donor.DonorDonationRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Service
public class DonorDonationRankServiceImpl implements DonorDonationRankService {

    private final String redisPrefix;

    private final DonationStatisticMapper donationStatisticMapper;

    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public DonorDonationRankServiceImpl(
            @Value("${dream-builder.redis.prefix.donor.donation.rank}") String redisPrefix,
            DonationStatisticMapper donationStatisticMapper, StringRedisTemplate stringRedisTemplate) {
        this.redisPrefix = redisPrefix;
        this.donationStatisticMapper = donationStatisticMapper;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public List<DonationStaDto> all() {
        String key = redisPrefix + "all:";
        return TranCacheUtil.tryCache(
                () -> {
                    Long size = stringRedisTemplate.opsForList().size(key);
                    if (size == null) return null;
                    List<String> list = stringRedisTemplate.opsForList().range(key, 0, size);
                    return list == null || list.isEmpty() ? null : list;
                },
                (jsons) -> stringRedisTemplate.opsForList().rightPushAll(key, jsons),
                () -> donationStatisticMapper.selectRank(50),
                (jsons) -> jsons.stream().map((json) -> JSON.parseObject(json, DonationStaDto.class)).toList(),
                (objs) -> objs.stream().map(JSON::toJSONString).toList()
        );
    }

    @Override
    public List<DonationStaDto> month() {
        String key = redisPrefix + "month:";
        LocalDate now = LocalDate.now();
        return TranCacheUtil.tryCache(
                () -> {
                    Long size = stringRedisTemplate.opsForList().size(key);
                    if (size == null) return null;
                    List<String> list = stringRedisTemplate.opsForList().range(key, 0, size);
                    return list == null || list.isEmpty() ? null : list;
                },
                (jsons) -> stringRedisTemplate.opsForList().rightPushAll(key, jsons),
                () -> donationStatisticMapper.selectRankByDate(50, now.minusMonths(1), now),
                (jsons) -> jsons.stream().map((json) -> JSON.parseObject(json, DonationStaDto.class)).toList(),
                (objs) -> objs.stream().map(JSON::toJSONString).toList()
        );
    }

    @Override
    public List<DonationStaDto> week() {
        String key = redisPrefix + "week:";
        LocalDate now = LocalDate.now();
        return TranCacheUtil.tryCache(
                () -> {
                    Long size = stringRedisTemplate.opsForList().size(key);
                    if (size == null) return null;
                    List<String> list = stringRedisTemplate.opsForList().range(key, 0, size);
                    return list == null || list.isEmpty() ? null : list;
                },
                (jsons) -> stringRedisTemplate.opsForList().rightPushAll(key, jsons),
                () -> donationStatisticMapper.selectRankByDate(50, now.minusWeeks(1), now),
                (jsons) -> jsons.stream().map((json) -> JSON.parseObject(json, DonationStaDto.class)).toList(),
                (objs) -> objs.stream().map(JSON::toJSONString).toList()
        );
    }

    @Override
    public void update(Long donorId) {
        String key;
        Long size;
        LocalDate now = LocalDate.now();

        key = redisPrefix + "all:";
        size = stringRedisTemplate.opsForList().size(key);
        if (size != null) update(key, donationStatisticMapper.selectSta2(donorId));

        key = redisPrefix + "month:";
        size = stringRedisTemplate.opsForList().size(key);
        if (size != null) update(key, donationStatisticMapper.selectSta2ByDate(donorId, now.minusMonths(1), now));

        key = redisPrefix + "week:";
        size = stringRedisTemplate.opsForList().size(key);
        if (size != null) update(key, donationStatisticMapper.selectSta2ByDate(donorId, now.minusWeeks(1), now));
    }

    private void update(String key, DonationStaDto sta) {
        Long size = stringRedisTemplate.opsForList().size(key);
        if (size == null || size == 0) return;
        LinkedList<String> tmp = new LinkedList<>();
        boolean flag = true;
        while (true) {
            String json = stringRedisTemplate.opsForList().rightPop(key);
            if (json == null) {
                tmp.addFirst(JSON.toJSONString(sta));
                break;
            }
            DonationStaDto obj = JSON.parseObject(json, DonationStaDto.class);
            if (obj.getDonorId().equals(sta.getDonorId())) {
                flag = false;
                continue;
            }
            if (obj.getAmount() >= sta.getAmount()) {
                tmp.addFirst(JSON.toJSONString(sta));
                tmp.addFirst(json);
                break;
            }
            tmp.addFirst(json);
        }
        if (flag) tmp.removeLast();
        stringRedisTemplate.opsForList().rightPushAll(key, tmp);
    }
}
