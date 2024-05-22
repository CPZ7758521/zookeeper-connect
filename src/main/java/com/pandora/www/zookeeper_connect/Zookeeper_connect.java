package com.pandora.www.zookeeper_connect;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;

public class Zookeeper_connect {
    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
        String connectString;
        int sessionTimeOut;
        ZooKeeper zkClient;

        connectString = "test-bigdata001.pandora.com:2182,test-bigdata002.pandora.com:2182,test-bigdata003.pandora.com:2182,";

        sessionTimeOut = 1000;

        zkClient = new ZooKeeper(connectString, sessionTimeOut, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });

        String node = "/hbase";

        Stat stat = zkClient.exists(node, false);

        if (stat == null) {
            System.out.println(node + "节点不存在");
            return;
        } else {
            System.out.println(node + "节点存在");
        }

        byte[] data = zkClient.getData(node, false, stat);

        System.out.println(new String(data) + "run");

        List<String> children = zkClient.getChildren("/", false);

        System.out.println(children);

        for (String child : children) {
            System.out.println(child);
        }

        zkClient.close();
    }
}
