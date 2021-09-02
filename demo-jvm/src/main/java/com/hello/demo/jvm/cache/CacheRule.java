package com.hello.demo.jvm.cache;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author: zhaohw
 * @date: 2021.09.01 下午 4:30
 */
@NoArgsConstructor
public class CacheRule {

    //超时时间，单位秒
    @Getter
    private int expireTime = 30;

    public CacheRule(int expireTime){
        this.expireTime = expireTime;
    }
}
