package com.clsld.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = { "com.clsld.consumer.Service" })
public class CommunityApplication {
    public static void main(String[] args) {
        SpringApplication.run(CommunityApplication.class,args);
    }
}
