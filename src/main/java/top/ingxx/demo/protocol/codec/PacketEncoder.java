package top.ingxx.demo.protocol.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import top.ingxx.demo.protocol.Packet;
import top.ingxx.demo.protocol.PacketCodeC;

public class PacketEncoder extends MessageToByteEncoder<Packet> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf byteBuf) throws Exception {
        //编码
        PacketCodeC.INSTANCE.encode(byteBuf,packet);
    }
}
