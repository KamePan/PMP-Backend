package cn.edu.ecnu.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.util.HashMap;
import java.util.Map;

public class MyWebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    //private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    // 实现点对点发送信息

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("与客户端建立连接，通道开启！");
        //channelGroup.add(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx,
                                Object message) throws Exception {
        // 由于 netty 采用协议升级进行 websocket 请求的连接，为了获取参数 id ，
        // 在第一次发送 http 请求时进行解析得到请求 URL，通过字符串解析获得参数
        // 客户端虽然只初始化一次 WebSocket,但连接后每次发送仍然会通过相同的 URL 进行发送
        // 但不会携带初始化时的参数
        if (message instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) message;
            String uri = request.uri();

            Map paramMap = getUrlParams(uri);
            String uid = (String) paramMap.get("uid");
            ChannelPool.channelGroup.put(uid, ctx.channel());

            if(uri.contains("?")){
                String newUri=uri.substring(0,uri.indexOf("?"));
                request.setUri(newUri);
            }

        } else if (message instanceof TextWebSocketFrame) {
            TextWebSocketFrame frame = (TextWebSocketFrame) message;
            System.out.println("服务器收到数据：" +frame.text());
        }
        super.channelRead(ctx, message);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame) throws Exception {

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("与客户端断开连接，通道关闭！");
        ChannelPool.channelGroup.values().remove(ctx.channel());
    }

    private Map getUrlParams(String url){
        Map<String,String> map = new HashMap<>();
        url = url.replace("?",";");
        if (!url.contains(";")){
            return map;
        }
        if (url.split(";").length > 0){
            String[] arr = url.split(";")[1].split("&");
            for (String s : arr){
                String key = s.split("=")[0];
                String value = s.split("=")[1];
                map.put(key,value);
            }
            return  map;

        }else{
            return map;
        }
    }
}
