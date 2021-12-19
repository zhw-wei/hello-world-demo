package com.hello.demo.middleware.zookeeper.cluster;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * @author zhw
 * @date 2021/12/19 6:48 下午
 */
public class ZkClusterServer01 {
    public static void main(String[] args) throws Exception {
        ZooKeeper zkCli = ZKClusterConfig.CREATE_ZK_CLI.apply(event -> {
        });

        //20秒之后上线
        Thread.sleep(1000 * 20);
        System.out.println(System.currentTimeMillis() + "---server01 start---");
        //注册临时节点
        zkCli.create(ZKClusterConfig.SERVER_NODE.apply("serverNode"), "服务器节点地址01".getBytes(StandardCharsets.UTF_8),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

        //60秒之后下线
        Thread.sleep(1000 * 60);
        System.out.println(System.currentTimeMillis() + "---server01 stop---");
    }

    @Test
    public void before() throws Exception{
        ZooKeeper zkCli = ZKClusterConfig.CREATE_ZK_CLI.apply(event -> {
        });

        if (Objects.isNull(zkCli.exists(ZKClusterConfig.SERVER_NODE_PARENT, false))) {
            //永久节点
            zkCli.create(ZKClusterConfig.SERVER_NODE_PARENT, "服务器集群根节点".getBytes(StandardCharsets.UTF_8),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }

        if (Objects.isNull(zkCli.exists(ZKClusterConfig.CLIENT_NODE_PARENT, false))) {
            //永久节点
            zkCli.create(ZKClusterConfig.CLIENT_NODE_PARENT, "客户端集群根节点".getBytes(StandardCharsets.UTF_8),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
    }
}
