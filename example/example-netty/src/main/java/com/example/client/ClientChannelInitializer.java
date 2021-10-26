package com.example.client;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

public class ClientChannelInitializer extends ChannelInitializer<Channel> {

    private final ClientHandler clientHandler = new ClientHandler();

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ch.pipeline()
                // 15秒客户端没给服务端主动发送消息就触发写空闲，执行TIMClientHandle的userEventTriggered方法给服务端发送一次心跳
                .addLast(new IdleStateHandler(0, 15, 0))
                .addLast(new DelimiterBasedFrameDecoder(8192, Delimiters.lineDelimiter()))
                .addLast(new StringEncoder())
                .addLast(new StringDecoder())
                .addLast(clientHandler);
    }
}
