package top.ingxx.demo.protocol;


import lombok.Data;

@Data
public abstract class Packet {

    //魔数 版本号 序列化算法 指令 数据长度 数据

    //版本号
    private Byte version = 1;

    //指令
    public abstract Byte getCommand();
}
