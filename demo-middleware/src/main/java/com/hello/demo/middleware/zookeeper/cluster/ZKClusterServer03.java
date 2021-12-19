package com.hello.demo.middleware.zookeeper.cluster;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.nio.charset.StandardCharsets;

/**
 * @author zhw
 * @date 2021/12/19 6:48 下午
 */
public class ZKClusterServer03 {

    public static void main(String[] args) throws Exception {
        ZooKeeper zkCli = ZKClusterConfig.CREATE_ZK_CLI.apply(event -> {
        });

        //60秒全程在线
        System.out.println(System.currentTimeMillis() + "---server03 start---");
        //注册临时节点
        zkCli.create(ZKClusterConfig.SERVER_NODE.apply("serverNode"), "服务器节点地址03".getBytes(StandardCharsets.UTF_8),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        Thread.sleep(1000 * 60);
        System.out.println(System.currentTimeMillis() + "---server03 stop---");
    }
}
