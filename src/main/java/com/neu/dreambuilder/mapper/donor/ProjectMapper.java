package com.neu.dreambuilder.mapper.donor;

import com.neu.dreambuilder.entity.donor.Project;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 爱心项目 Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2023-11-03
 */
@Mapper
public interface ProjectMapper extends BaseMapper<Project> {

    @MapKey("id")
    Map<Long, Project> selectByIds(List<Long> toList);
}
