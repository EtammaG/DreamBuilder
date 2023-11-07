package com.neu.dreambuilder.mapper.kid;

import com.neu.dreambuilder.entity.kid.Kid;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 被捐助的孩子 Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2023-11-03
 */
@Mapper
public interface KidMapper extends BaseMapper<Kid> {

    @MapKey("id")
    Map<Long, Kid> selectByIds(List<Long> ids);

}
