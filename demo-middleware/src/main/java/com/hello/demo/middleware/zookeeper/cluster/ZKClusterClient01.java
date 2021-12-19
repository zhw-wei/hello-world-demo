package com.hello.demo.middleware.zookeeper.cluster;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.nio.charset.StandardCharsets;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 首先启动客户端
 * @author zhw
 * @date 2021/12/19 6:48 下午
 */
public class ZKClusterClient01 {

    private static ZooKeeper zkCli_client;

    public static void main(String[] args) throws Exception{
        Thread.sleep(1000);
        System.out.println(System.currentTimeMillis() + "---client start---");
        zkCli_client = ZKClusterConfig.CREATE_ZK_CLI.apply(event -> {
            System.out.println("----event start----, event: " + event.getType());
            try {
                listener();
            }catch (Exception ex){
                System.out.println(ex);
            }
            System.out.println("----event stop----");
        });

        //注册临时节点
        zkCli_client.create(ZKClusterConfig.CLIENT_NODE.apply("clientNode"), "客户端节点".getBytes(StandardCharsets.UTF_8),
                ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

        Thread.sleep(1000 * 120);
        System.out.println(System.currentTimeMillis() + "---client stop---");
    }

    private static void listener() throws Exception{
        //获取服务器地址
        //测试条件：多个main线程同时执行
        //测试结果：日志打印可以看到某个服务器节点上线或下线
        try {
            List<String> children = zkCli_client.getChildren(ZKClusterConfig.SERVER_NODE_PARENT, true);
            System.out.println(children);

            Map<String, String> hostPathMap = children.stream().map(child -> {
                        String path = null;
                        try {
                            byte[] data = zkCli_client.getData(ZKClusterConfig.SERVER_NODE.apply(child), false, null);
                            path = new String(data);
                        } catch (Exception ex) {
                            System.out.println(ex);
                        }

                        if (Objects.isNull(path)) return null;
                        return new AbstractMap.SimpleEntry<>(child, path);
                    }).filter(Objects::nonNull)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (var0, var1) -> var0));

            System.out.println(hostPathMap);
        } catch (Exception ex) {

        }
    }
}
