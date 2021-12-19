package com.hello.demo.middleware.zookeeper;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

/**
 * @author zhw
 * @date 2021/12/19 1:59 下午
 */
public class ZKClient01 {

    private ZooKeeper zkCli;

    //建立连接
    @BeforeEach
    public void before() throws IOException {
        this.zkCli = new ZooKeeper(ZKConfig.ZK_PATH, 2000,
                //zookeeper把数据变化或路径变化，发送给此listener线程
                event -> {
                    System.out.println("------start-----");
                    List<String> children = null;
                    try {
                        //watch=true表示开启监听数据
                        children = this.zkCli.getChildren("/", true);
                    } catch (KeeperException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    for (String child : children) {
                        System.out.println(child);
                    }
                    System.out.println("------end-----");
                });
    }

    @Test
    @DisplayName("创建节点")
    public void createNode() throws InterruptedException, KeeperException {
        //创建节点
        String path = this.zkCli.create("/ZNode01", "ZNodeValue".getBytes(StandardCharsets.UTF_8),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(path);
    }

    @Test
    @DisplayName("获取子节点并监听数据变化")
    public void getChildren() throws InterruptedException, KeeperException {

        //线程暂停，用于命令行修改ZNode节点和观察控制打印
        Thread.sleep(Integer.MAX_VALUE);
    }

    @Test
    @DisplayName("判断节点是否存在")
    public void checkNodeExists() throws InterruptedException, KeeperException {
        Stat exists = this.zkCli.exists("/ZNode01", false);
        System.out.println(exists);
        Assertions.assertTrue(Objects.nonNull(exists));
    }
}
