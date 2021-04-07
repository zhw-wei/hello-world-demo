package com.hello.demo.haikangVideo;

import com.alibaba.fastjson.JSONObject;
import com.hikvision.artemis.sdk.ArtemisHttpUtil;
import com.hikvision.artemis.sdk.config.ArtemisConfig;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: zhaohw
 * @date: 2021/4/7 10:51
 * @description:
 */
public class GetCameraPreviewURL {
    public static String GetCameraPreviewURL() {

        /**
         * STEP1：设置平台参数，根据实际情况,设置host appkey appsecret 三个参数.
         */
        ArtemisConfig.host = "192.168.18.10:443"; // artemis网关服务器ip端口
        ArtemisConfig.appKey = "20915020";  // 秘钥appkey
        ArtemisConfig.appSecret = "N6XVgdCJtn0fBRYKBL6u";// 秘钥appSecret

        final String previewURLsApi = "/artemis/api/video/v2/cameras/previewURLs?";
        Map<String, String> path = new HashMap<>(2) {{
            put("https://", previewURLsApi);//根据现场环境部署确认是http还是https
        }};

        String body = JSONObject.toJSONString(new CameraBody());

        String result = ArtemisHttpUtil.doPostStringArtemis(path, body, null, null,
                "application/json", null);
        return result;
    }

    public static void main(String[] args) {
        String result = GetCameraPreviewURL();
        System.out.println("result结果示例: " + result);
    }
}
