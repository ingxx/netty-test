package top.ingxx.demo.serialize;

import top.ingxx.demo.serialize.impl.JSONSerializer;

public interface Serializer {

    //默认采用JSON序列化
    Serializer DEFAULT = new JSONSerializer();
    /**
     * 序列化算法编号
     * @return
     */
    byte getSerializerAlogrithm();

    /**
     * java对象序列化为二进制数组
     * @param object
     * @return
     */
    byte[] serialize(Object object);

    /**
     * 二进制数组反序列化为java对象
     * @param clazz
     * @param bytes
     * @param <T>
     * @return
     */
    <T> T deserialize(Class<T> clazz, byte[] bytes);
}
