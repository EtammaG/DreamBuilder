package com.neu.dreambuilder.mapper.kid;

import com.neu.dreambuilder.entity.kid.AwardType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 奖品的类型 Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2023-11-03
 */
@Mapper
public interface AwardTypeMapper extends BaseMapper<AwardType> {

    List<AwardType> selectAll();
}
