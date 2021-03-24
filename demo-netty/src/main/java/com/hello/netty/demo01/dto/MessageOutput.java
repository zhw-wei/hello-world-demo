package com.hello.netty.demo01.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageOutput {
    private String type;
    private String requestId;
    private Object payload;
}
