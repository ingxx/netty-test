package top.ingxx.demo.protocol.request;

import lombok.Data;
import top.ingxx.demo.protocol.Packet;
import top.ingxx.demo.protocol.command.Command;

@Data
public class LoginRequestPacket extends Packet {

    private String userId;

    private String username;

    private String password;

    @Override
    public Byte getCommand() {
        return Command.LOGIN_REQUEST;
    }
}
