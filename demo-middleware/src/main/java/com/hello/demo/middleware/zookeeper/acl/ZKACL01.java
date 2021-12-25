package com.hello.demo.middleware.zookeeper.acl;

import com.hello.demo.middleware.zookeeper.ZKConfig;
import lombok.val;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.auth.DigestAuthenticationProvider;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

/**
 * digest
 *
 * @author zhw
 * @date 2021/12/25 7:29 下午
 */
public class ZKACL01 implements Watcher {

    private String ipPort;
    private int timeout;
    private ZooKeeper zk;

    public ZKACL01(String ipPort, int timeout){
        this.ipPort = ipPort;
        this.timeout = timeout;
    }

    public void start(){
        try {
            System.out.println("zookeeper starting .........");
            this.zk = new ZooKeeper(ipPort, timeout, this);
            System.out.println("zookeeper start success ....");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<ACL> getAcl(String path){
        try {
            return this.zk.getACL(path, null);
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println("event: " + event);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {

        ZKACL01 zkacl01 = new ZKACL01(ZKConfig.ZK_PATH, 2000);
        zkacl01.start();

        System.out.println(zkacl01.getAcl("/ZNode01"));

    }
}
