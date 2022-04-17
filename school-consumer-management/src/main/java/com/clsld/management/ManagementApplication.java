package com.clsld.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = { "com.clsld.management.service"})
public class ManagementApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManagementApplication.class);
    }
}
