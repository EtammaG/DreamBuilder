package com.neu.dreambuilder.mapper.donor;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neu.dreambuilder.entity.donor.KidDonation;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 捐赠者捐助孩子善款记录 Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2023-11-03
 */
@Mapper
public interface KidDonationMapper extends BaseMapper<KidDonation> {

    KidDonation selectOneByKidId(Long kidId);

    Integer selectTotalAmount();

    Integer selectTotalAmountByKidId(Long kidId);
}
