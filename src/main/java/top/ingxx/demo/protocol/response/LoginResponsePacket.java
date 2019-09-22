package top.ingxx.demo.protocol.response;

import lombok.Data;
import top.ingxx.demo.protocol.Packet;
import top.ingxx.demo.protocol.command.Command;


@Data
public class LoginResponsePacket extends Packet {
    private boolean success;

    private String reason;


    @Override
    public Byte getCommand() {
        return Command.LOGIN_RESPONSE;
    }
}
