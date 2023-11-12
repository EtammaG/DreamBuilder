package com.neu.dreambuilder.mapper.kid;

import com.neu.dreambuilder.entity.kid.ReplyHotLike;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    int delete(ReplyHotLike replyHotLike);

    List<ReplyHotLike> selectAll();

    void deleteAll();

    void insertAll(List<ReplyHotLike> replies);
}
