package top.ingxx.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

public class NettyServer {
    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap(); //启动类

        NioEventLoopGroup boss = new NioEventLoopGroup(); //bossGroup表示监听端口，accept 新连接的线程组
        NioEventLoopGroup worker = new NioEventLoopGroup(); //workerGroup表示处理每一条连接的数据读写的线程组

        serverBootstrap
                .group(boss,worker) //引导类配置两大线程组
                .channel(NioServerSocketChannel.class) //指定io模型
               .childHandler(new ChannelInitializer<NioSocketChannel>(){ //定义后续每条连接的数据读写，业务处理逻辑
                    protected void initChannel(NioSocketChannel nioSocketChannel) throws Exception {
                        nioSocketChannel.pipeline().addLast(new StringDecoder());
                        nioSocketChannel.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                                System.out.println(msg);
                            }
                        });
                    }
                })
                .bind(8080);//监听端口
    }
}
