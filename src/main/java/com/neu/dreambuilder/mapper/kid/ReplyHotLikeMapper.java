package com.neu.dreambuilder.mapper.kid;

import com.neu.dreambuilder.entity.kid.ReplyHotLike;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 热门提交成果的点赞 Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2023-11-03
 */
@Mapper
public interface ReplyHotLikeMapper extends BaseMapper<ReplyHotLike> {

    Integer cat(ReplyHotLike replyHotLike);
}
