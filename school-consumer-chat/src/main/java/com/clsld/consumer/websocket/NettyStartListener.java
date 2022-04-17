package com.clsld.consumer.websocket;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class NettyStartListener implements ApplicationRunner {
    @Resource
    private WebSocketServer webSocketServer;
    @Override
    public void run(ApplicationArguments args) throws Exception {
        webSocketServer.init();
    }
}
