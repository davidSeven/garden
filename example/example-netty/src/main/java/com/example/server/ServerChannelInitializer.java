package com.example.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

public class ServerChannelInitializer extends ChannelInitializer<Channel> {

    private final ServerHandler serverHandler = new ServerHandler();

    @Override
    protected void initChannel(Channel channel) throws Exception {

        channel.pipeline()
                // 20秒没有收到客户端发送消息或心跳就触发读空闲，执行TIMServerHandle的userEventTriggered方法关闭客户端连接
                .addLast(new IdleStateHandler(20, 0, 0))
                .addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()))
                .addLast(new StringEncoder())
                .addLast(new StringDecoder())
                .addLast(serverHandler);
    }
}
