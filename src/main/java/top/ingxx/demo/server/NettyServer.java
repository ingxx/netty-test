package top.ingxx.demo.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import top.ingxx.demo.protocol.codec.PacketDecoder;
import top.ingxx.demo.protocol.codec.PacketEncoder;
import top.ingxx.demo.server.handler.LoginRequestHandler;
import top.ingxx.demo.server.handler.MessageRequestHandler;

public class NettyServer {
    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap(); //启动类

        NioEventLoopGroup boss = new NioEventLoopGroup(); //bossGroup表示监听端口，accept 新连接的线程组
        NioEventLoopGroup worker = new NioEventLoopGroup(); //workerGroup表示处理每一条连接的数据读写的线程组

        serverBootstrap
                .group(boss, worker) //引导类配置两大线程组
                .channel(NioServerSocketChannel.class) //指定io模型
                .childHandler(new ChannelInitializer<NioSocketChannel>() { //定义后续每条连接的数据读写，业务处理逻辑
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new PacketDecoder());
                        nioSocketChannel.pipeline().addLast(new LoginRequestHandler());
                        nioSocketChannel.pipeline().addLast(new MessageRequestHandler());
                        nioSocketChannel.pipeline().addLast(new PacketEncoder());
                    }
                });
        bind(serverBootstrap,8080);
    }
    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(future -> {
            if (future.isSuccess()) {
                System.out.println("端口[" + port + "]绑定成功!");
            } else {
                System.err.println("端口[" + port + "]绑定失败!");
            }
        });
    }
}
