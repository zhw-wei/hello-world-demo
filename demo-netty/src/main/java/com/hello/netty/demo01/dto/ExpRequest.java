package com.hello.netty.demo01.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 输入类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExpRequest {
    private int base;
    private int exp;
}
