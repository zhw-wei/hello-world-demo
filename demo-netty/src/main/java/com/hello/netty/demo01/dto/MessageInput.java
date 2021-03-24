package com.hello.netty.demo01.dto;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageInput {
    private String type;
    private String requestId;
    private String payload;

    public <T> T getPayload(Class<T> clazz) {
        if (Objects.isNull(clazz)) return null;
        return JSON.parseObject(this.payload, clazz);
    }
}
