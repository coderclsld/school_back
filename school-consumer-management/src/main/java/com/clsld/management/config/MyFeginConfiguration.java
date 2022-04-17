package com.clsld.management.config;

import feign.Contract;
import feign.Logger;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyFeginConfiguration {
    @Bean
    public Contract feignContract(){
        return new SpringMvcContract();
    }

    @Bean
    public Logger.Level logLevel(){
        return Logger.Level.FULL;
    }
}
