package top.ingxx.demo.protocol.response;

import lombok.Data;
import top.ingxx.demo.protocol.Packet;
import top.ingxx.demo.protocol.command.Command;

@Data
public class MessageResponsePacket extends Packet {
    private String message;
    @Override
    public Byte getCommand() {
        return Command.MESSAGE_RESPONSE;
    }
}
