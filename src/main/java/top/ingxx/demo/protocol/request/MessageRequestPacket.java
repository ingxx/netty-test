package top.ingxx.demo.protocol.request;

import lombok.Data;
import top.ingxx.demo.protocol.Packet;
import top.ingxx.demo.protocol.command.Command;

@Data
public class MessageRequestPacket extends Packet {
    private String message;

    @Override
    public Byte getCommand() {
        return Command.MESSAGE_REQUEST;
    }
}
