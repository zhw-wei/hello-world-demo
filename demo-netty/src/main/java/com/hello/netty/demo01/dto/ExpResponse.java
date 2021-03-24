package com.hello.netty.demo01.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpResponse {
    private long value;
    private long costInNanos;
}
