package com.neu.dreambuilder.service.kid;

import com.neu.dreambuilder.dto.CommentDto;
import com.neu.dreambuilder.dto.kid.HotReplyDto;

import java.util.List;

public interface HotReplyService {
    List<HotReplyDto> getAll(Long id);

    void like(Long id, long hotId);

    List<CommentDto> getComment(String hotId);

    void addComment(Long id, long hotId, String comment);
}
