package com.clsld.consumer.websocket;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.clsld.consumer.utils.RedisUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import jodd.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    private static ConcurrentHashMap<String, ChannelHandlerContext> websocketMap  = new ConcurrentHashMap<>();

    private static int onlineCount = 0;

    private String userid = "";

    private Channel channel = null;

    public static RedisUtil redisUtil;

    public static RedisTemplate<String,Object> redisTemplate;

    /*收到连接请求时的前置操作*/
    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{
        System.out.println("进入channelRead");
        if(null != msg && msg instanceof FullHttpRequest) {
            FullHttpRequest request = (FullHttpRequest) msg;
            String uri = request.uri();
            Map<String,String> paramMap = getUrlParams(uri);
            if (uri.contains("?")) {
                String newUri = uri.substring(0, uri.indexOf("?"));
                System.out.println(newUri);
                request.setUri(newUri);
                this.userid = paramMap.get("userId");
                this.channel = ctx.channel();
                /*if(websocketMap.containsKey(paramMap.get("userId")))
                    websocketMap.remove(paramMap.get("userId"));
                else*/
                websocketMap.put(this.userid,ctx);
                // TODO: 31/3/2022 chat 将list中的数据返回给用户
            }
        }
        super.channelRead(ctx,msg);
        if(msg instanceof FullHttpRequest) {
            try {
                String o = JSON.toJSONString(redisUtil.lGet(userid, 0, -1));
                if(o.length() > 2) {
                    websocketMap.get(userid).channel().writeAndFlush(new TextWebSocketFrame(o));
                    redisTemplate.delete(userid);
                }
            } catch (Exception e) {
                e.printStackTrace();
            };
        }
    }

    /*收到消息时执行的处理*/
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        /*FullHttpRequest request = (FullHttpRequest) msg;
        System.out.println(request.getUri());*/
        if(StringUtil.isNotBlank(msg.text())){
            try{
                JSONObject jsonObject = JSON.parseObject(msg.text());
                String toUserId = jsonObject.getString("toUserId");
                if(StringUtils.isNotBlank(toUserId)&&websocketMap.containsKey(toUserId)){
                    websocketMap.get(toUserId).channel().writeAndFlush(new TextWebSocketFrame(jsonObject.toJSONString()));
                }else{
                    // TODO: 31/3/2022  chat 用户不在服务器上的处理
                    System.out.println("用户不在服务器上");
                    redisUtil.lSet(toUserId,jsonObject);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    /*客户端连接的时候执行的处理*/
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端建立连接");
    }

    /*客户端断开的时候执行的处理*/
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        if(websocketMap.containsKey(userid)) {
            websocketMap.remove(userid);
        }
    }

    /*发生异常的时候执行的处理*/
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("出现异常");
        System.out.println(cause.getMessage());
    }

    private Map<String,String> getUrlParams(String url){
        Map<String,String> map = new HashMap<>();
        url = url.replace("?",";");
        if(!url.contains(";"))
            return map;
        if(url.split(";").length>0){
            String[] arr = url.split(";")[1].split("&");
            for(String s : arr){
                String key = s.split("=")[0];
                String value = s.split("=")[1];
                map.put(key,value);
            }
            return map;
        }else{
            return map;
        }
    }
}
