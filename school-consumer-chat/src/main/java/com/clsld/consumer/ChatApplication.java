package com.clsld.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@SpringBootApplication
@ComponentScan(basePackages="com.clsld.consumer.*")
public class ChatApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(ChatApplication.class,args);

    }
}
