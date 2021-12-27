package com.hello.demo.middleware.zookeeper;

/**
 * @author zhw
 * @date 2021/12/19 1:04 下午
 */
public interface ZKConfig {

    //如果是多个连接使用逗号分割，注意逗号后不要有空格
    String ZK_LINUX_PATH="192.168.3.63:2181";

    String ZK_MAC_PATH="192.168.1.118:2181";

    String ZK_PATH=ZK_LINUX_PATH;
}
