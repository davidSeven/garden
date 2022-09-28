package com.example;

import cn.hutool.core.io.resource.ClassPathResource;
import com.alibaba.fastjson.JSON;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.ZkClient;

import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ZookeeperApplication {

    public static void main(String[] args) throws InterruptedException {

        ClassPathResource classPathResource = new ClassPathResource("zk.properties");
        Properties properties = new Properties();
        try {
            properties.load(classPathResource.getStream());
            System.out.println("读取配置文件完成");
            System.out.println(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ZkClient zkClient = new ZkClient(properties.getProperty("zk.addr"), Integer.parseInt(properties.getProperty("zk.connect.timeout")));
        String zkRoot = properties.getProperty("zk.root");
        boolean exists = zkClient.exists(zkRoot);
        if (!exists) {
            zkClient.createPersistent(zkRoot);
        }

        zkClient.subscribeChildChanges(zkRoot, new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChildren) throws Exception {
                System.out.println("parentPath [" + parentPath + "], currentChildren [" + JSON.toJSONString(currentChildren) + "]");
            }
        });

        if (!zkClient.exists(zkRoot + "/test1")) {
            zkClient.createEphemeral(zkRoot + "/test1");
        }
        TimeUnit.SECONDS.sleep(1);
        if (!zkClient.exists(zkRoot + "/test2")) {
            zkClient.createEphemeral(zkRoot + "/test2");
        }
        TimeUnit.SECONDS.sleep(1);
        if (!zkClient.exists(zkRoot + "/test3")) {
            zkClient.createEphemeral(zkRoot + "/test3");
        }
        TimeUnit.SECONDS.sleep(1);
        zkClient.delete(zkRoot + "/test3");

        TimeUnit.SECONDS.sleep(5);

        zkClient.close();
    }
}
