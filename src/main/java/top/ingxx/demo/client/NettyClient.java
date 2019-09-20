package top.ingxx.demo.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Date;

public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap(); //启动类
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group) //指定线程模型
                .channel(NioSocketChannel.class) //指定IO为NIO
                .handler(new ChannelInitializer<Channel>() { //IO处理逻辑
                    @Override
                    protected void initChannel(Channel ch) {
                        ch.pipeline().addLast(new FirstClientHandler());
                    }
                });
        //建立连接
        Channel channel = bootstrap.connect("127.0.0.1", 8080).channel();

        while (true) {
            channel.writeAndFlush(new Date() + ": hello world!");
            Thread.sleep(2000);
        }
    }
}
