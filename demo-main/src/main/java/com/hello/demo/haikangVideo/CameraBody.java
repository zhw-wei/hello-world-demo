package com.hello.demo.haikangVideo;

import lombok.Data;

/**
 * @author: zhaohw
 * @date: 2021/4/7 11:10
 * @description:
 */
@Data
public class CameraBody {

    private String cameraIndexCode = "";
    private Integer streamType = 0;
    private String protocol = "rtsp";
    private Integer transmode = 1;
    private String expand = "streamform=ps";
}
