package top.ingxx.demo.attribute;

import io.netty.util.AttributeKey;
import top.ingxx.demo.session.Session;

public interface Attributes {
    AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");
    AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}
