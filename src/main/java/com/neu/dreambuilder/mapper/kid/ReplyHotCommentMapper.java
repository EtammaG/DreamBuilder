package com.neu.dreambuilder.mapper.kid;

import com.neu.dreambuilder.entity.kid.ReplyHotComment;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 热门提交成果的评论 Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2023-11-03
 */
@Mapper
public interface ReplyHotCommentMapper extends BaseMapper<ReplyHotComment> {

    List<ReplyHotComment> selectAll();
}
