package com.neu.dreambuilder.mapper.donor;

import com.neu.dreambuilder.entity.donor.ProjectType;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 项目类别 Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2023-11-03
 */
@Mapper
public interface ProjectTypeMapper extends BaseMapper<ProjectType> {

    List<ProjectType> selectAll();
}
