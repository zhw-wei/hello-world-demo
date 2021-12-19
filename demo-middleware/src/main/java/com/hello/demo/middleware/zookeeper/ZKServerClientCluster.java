package com.hello.demo.middleware.zookeeper;

import org.apache.zookeeper.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 模拟服务器集群
 *
 * @author zhw
 * @date 2021/12/19 4:53 下午
 */
public class ZKServerClientCluster {


    @DisplayName("模拟服务器集群-节点02")
    public void serverCluster02() throws InterruptedException, KeeperException {

    }

    @DisplayName("模拟服务器集群-节点02")
    public void serverCluster03() throws InterruptedException, KeeperException {

    }


    private ZooKeeper zkCli_client;

    @DisplayName("模拟客户端")
    public void clientCluster01() throws InterruptedException, KeeperException {

    }


}
