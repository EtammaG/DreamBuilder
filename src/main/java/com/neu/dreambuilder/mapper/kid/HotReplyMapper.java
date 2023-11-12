package com.neu.dreambuilder.mapper.kid;

import com.neu.dreambuilder.bo.Comment;
import com.neu.dreambuilder.dto.CommentDto;
import com.neu.dreambuilder.dto.kid.HotReplyDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HotReplyMapper {
    List<HotReplyDto> selectAll();

    List<CommentDto> selectCommentsByHotId(String hotId);

    List<Comment> selectAllComments();
}
