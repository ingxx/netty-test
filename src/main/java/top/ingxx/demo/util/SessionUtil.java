package top.ingxx.demo.util;

import io.netty.util.AttributeKey;
import top.ingxx.demo.attribute.Attributes;
import top.ingxx.demo.session.Session;

import io.netty.channel.Channel;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SessionUtil {
    public static final Map<String, Channel> userIdChannelMap = new ConcurrentHashMap<>();

    public static void bindSession(Session session, Channel channel) {
        userIdChannelMap.put(session.getUserId(), channel);
        channel.attr(Attributes.SESSION).set(session);
    }

    public static void unBindSession(Channel channel) {
        if (hasSession(channel)) {
            userIdChannelMap.remove(getSession(channel).getUserId());
            channel.attr(Attributes.SESSION).set(null);
        }
    }

    public static Session getSession(Channel channel) {
        return channel.attr(Attributes.SESSION).get();
    }

    public static boolean hasSession(Channel channel) {
        return channel.hasAttr(Attributes.SESSION);
    }

    public static Channel getChannel(String userId) {
        return userIdChannelMap.get(userId);
    }
}
