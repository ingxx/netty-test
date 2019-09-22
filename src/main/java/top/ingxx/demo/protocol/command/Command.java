package top.ingxx.demo.protocol.command;

public interface Command {
    Byte LOGIN_REQUEST = 1; //登录request
    Byte LOGIN_RESPONSE = 2; //登陆response
    Byte MESSAGE_REQUEST = 3;
    Byte MESSAGE_RESPONSE = 4;
}
