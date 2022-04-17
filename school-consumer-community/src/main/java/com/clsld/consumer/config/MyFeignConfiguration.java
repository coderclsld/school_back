package com.clsld.consumer.config;

import feign.Contract;
import feign.Logger;
import feign.codec.Decoder;
import feign.form.FormEncoder;
import feign.form.spring.converter.SpringManyMultipartFilesReader;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.cloud.openfeign.support.SpringMvcContract;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MyFeignConfiguration {

    @Bean
    public Contract feignContract(){
        return new SpringMvcContract();
    }

    @Bean
    public Logger.Level logLevel(){
        return Logger.Level.FULL;
    }
}