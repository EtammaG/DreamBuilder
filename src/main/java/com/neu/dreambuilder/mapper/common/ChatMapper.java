package com.neu.dreambuilder.mapper.common;

import com.neu.dreambuilder.entity.Chat;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * <p>
 * 聊天记录暂存 Mapper 接口
 * </p>
 *
 * @author 作者
 * @since 2023-11-03
 */
@Mapper
public interface ChatMapper extends BaseMapper<Chat> {

    List<String> selectAllMsgByBoth(
            @PathVariable("toId") Long toId,
            @PathVariable("fromId") Long fromId,
            @PathVariable("type") int type);

}
