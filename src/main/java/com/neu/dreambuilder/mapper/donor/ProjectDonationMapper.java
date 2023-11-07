package com.neu.dreambuilder.mapper.donor;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neu.dreambuilder.entity.donor.ProjectDonation;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 捐赠者捐助项目善款记录 Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2023-11-03
 */
@Mapper
public interface ProjectDonationMapper extends BaseMapper<ProjectDonation> {

    Integer selectTotalAmount();
}
