package com.neu.dreambuilder.mapper.kid;

import com.neu.dreambuilder.entity.kid.Type;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 孩子类别 Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2023-11-03
 */
@Mapper
public interface TypeMapper extends BaseMapper<Type> {

    List<Type> selectAll();
}
