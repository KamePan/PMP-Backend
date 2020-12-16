package cn.edu.ecnu.netty;

import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelPool {

    public static ConcurrentHashMap<String, Channel> channelGroup = new ConcurrentHashMap<>();

    public static void pointSending(String uid, String message){
        // 发送单点信息的函数
        // 服务端发送信息时如果用户在线，即用户 Channel 在 channelGroup 中时，向用户推送信息
        if (channelGroup.containsKey(uid)) {
            ChannelPool.channelGroup.get(uid).writeAndFlush(new TextWebSocketFrame(message));
        }
    }
}
