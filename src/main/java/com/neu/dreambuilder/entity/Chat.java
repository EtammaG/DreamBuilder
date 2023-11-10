package com.neu.dreambuilder.entity;

import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 聊天记录暂存
 * </p>
 *
 * @author 作者
 * @since 2023-11-03
 */
@Getter
@Setter
@AllArgsConstructor
@ApiModel(value = "Chat对象", description = "聊天记录暂存")
public class Chat {

    @ApiModelProperty("消息内容")
    private String content;

    @ApiModelProperty("发送方ID")
    private Long fromId;

    @ApiModelProperty("接收方ID")
    private Long toId;

    @ApiModelProperty("0表示volunteer->kid，1表示kid->volunteer")
    private Integer type;

    @ApiModelProperty("发送时间")
    private LocalDateTime time;


}
