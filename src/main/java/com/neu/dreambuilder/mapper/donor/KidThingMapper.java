package com.neu.dreambuilder.mapper.donor;

import com.neu.dreambuilder.entity.donor.KidThing;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 捐赠者捐助孩子物品记录 Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2023-11-03
 */
@Mapper
public interface KidThingMapper extends BaseMapper<KidThing> {

    KidThing selectOneByKidId(Long kidId);

}
