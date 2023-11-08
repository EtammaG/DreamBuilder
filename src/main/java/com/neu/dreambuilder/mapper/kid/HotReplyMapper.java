package com.neu.dreambuilder.mapper.kid;

import com.neu.dreambuilder.dto.CommentDto;
import com.neu.dreambuilder.dto.kid.HotReplyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HotReplyMapper {
    List<HotReplyDto> selectAll(Long id);

    List<CommentDto> selectCommentsByHotId(String hotId);
}
