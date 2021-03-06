package top.ingxx.demo.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.string.StringEncoder;
import top.ingxx.demo.client.handler.LoginResponseHandler;
import top.ingxx.demo.client.handler.MessageResponseHandler;
import top.ingxx.demo.protocol.PacketCodeC;
import top.ingxx.demo.protocol.codec.PacketDecoder;
import top.ingxx.demo.protocol.codec.PacketEncoder;
import top.ingxx.demo.protocol.codec.Spliter;
import top.ingxx.demo.protocol.request.MessageRequestPacket;
import top.ingxx.demo.util.LoginUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap(); //启动类
        NioEventLoopGroup group = new NioEventLoopGroup();

        bootstrap.group(group) //指定线程模型
                .channel(NioSocketChannel.class) //指定IO为NIO
                .handler(new ChannelInitializer<Channel>() { //IO处理逻辑
                    @Override
                    protected void initChannel(Channel ch) {
                        ch.pipeline().addLast(new Spliter());
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new LoginResponseHandler());
                        ch.pipeline().addLast(new MessageResponseHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                });
        //建立连接
        Channel channel = bootstrap.connect("127.0.0.1", 8080).channel();
        starConsoleThread(channel);
    }

    private static void starConsoleThread(Channel channel) {
        new Thread(() -> {
            while (!Thread.interrupted()) {
                if (LoginUtil.hasLogin(channel)) {
                    System.out.println("输入消息发送至服务端: ");
                    Scanner sc = new Scanner(System.in);
                    String line = sc.nextLine();
                    MessageRequestPacket packet = new MessageRequestPacket();
                    packet.setMessage(line);
                    channel.writeAndFlush(packet);
                }
            }
        }).start();
    }
}
