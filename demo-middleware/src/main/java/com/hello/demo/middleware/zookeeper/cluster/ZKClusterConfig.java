package com.hello.demo.middleware.zookeeper.cluster;

import com.hello.demo.middleware.zookeeper.ZKConfig;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.function.Function;

/**
 * @author zhw
 * @date 2021/12/19 6:50 下午
 */
public interface ZKClusterConfig {
    //服务端集群对象根节点
    String SERVER_NODE_PARENT = "/ZNode_Server";
    Function<String, String> SERVER_NODE = str -> String.format(SERVER_NODE_PARENT + "/%s", str);

    //客户端集群对象根节点
    String CLIENT_NODE_PARENT = "/ZNode_Client";
    Function<String, String> CLIENT_NODE = str -> String.format(CLIENT_NODE_PARENT + "/%s", str);

    Function<Watcher, ZooKeeper> CREATE_ZK_CLI = watcher -> {
        ZooKeeper zkCli = null;
        try {
            zkCli = new ZooKeeper(ZKConfig.ZK_PATH, 2000, watcher);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return zkCli;
    };
}
