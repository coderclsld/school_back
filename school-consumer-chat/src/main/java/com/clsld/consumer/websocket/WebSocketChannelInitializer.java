package com.clsld.consumer.websocket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WebSocketChannelInitializer extends ChannelInitializer<SocketChannel> {


    @Value("${url}")
    private String websocketUrl;


    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        socketChannel.pipeline().addLast("logging",new LoggingHandler("DEBUG"))
                .addLast("http-codec",new HttpServerCodec())
                .addLast("aggregator",new HttpObjectAggregator(8192))
                .addLast("http-chunked",new ChunkedWriteHandler())
                .addLast("handler",new WebSocketHandler())
                .addLast(new WebSocketServerProtocolHandler("/chat",null,true,65535*10));
    }
}
