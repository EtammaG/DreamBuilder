package com.neu.dreambuilder.mapper.common;

import com.neu.dreambuilder.entity.Chat;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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

}
