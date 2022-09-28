package com.example.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.DefaultExecutorServiceFactory;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

import java.net.InetSocketAddress;
import java.util.concurrent.TimeUnit;

public class AppClient {

    private static EventLoopGroup group = new NioEventLoopGroup(1, new DefaultExecutorServiceFactory("client-work"));
    private static SocketChannel channel;

    private static String serverIp = "127.0.0.1";
    private static int serverPort = 9000;

    /**
     * 重试次数
     */
    private static int errorCount;
    private static int maxErrorCount = 3;

    private static int nettyPort = 9100;

    public static void main(String[] args) throws InterruptedException {

        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .localAddress(new InetSocketAddress(nettyPort))
                .handler(new ClientChannelInitializer());

        ChannelFuture future = null;
        try {
            future = bootstrap.connect(serverIp, serverPort).sync();
        } catch (Exception e) {
            errorCount++;

            if (errorCount >= maxErrorCount) {
                System.out.println("连接失败次数达到上限[" + errorCount + "]次");
            }
            System.out.println("Connect fail!");
            e.printStackTrace();
        }
        if (future.isSuccess()) {
            System.out.println("Start tim client success!");
        }
        channel = (SocketChannel) future.channel();

        channel.writeAndFlush("你好，发送一条测试消息" + "\r\n").addListener(new GenericFutureListener() {
            @Override
            public void operationComplete(Future future) throws Exception {
                System.out.println("消息发送完成");

                channel.close();
            }
        });

        TimeUnit.SECONDS.sleep(3);
    }
}
