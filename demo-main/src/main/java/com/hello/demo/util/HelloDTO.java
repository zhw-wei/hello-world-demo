package com.hello.demo.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HelloDTO {

    private String merchantNo = "11";      //商户号
    private Long timestamp = System.currentTimeMillis();         //请求时间戳
    @JsonProperty("out_trade_no")
    private String outTradeNo = "22";      //商户订单号
    @JsonProperty("mobile_no")
    private String mobileNo = "33";        //银行卡预留手机号
    @JsonProperty("auth_code")
    private String authCode = "44";        //授权码
}
