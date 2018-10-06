package com.gmail.evanloafakahaitao.pcstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@PropertySource({"classpath:config.properties"})
@ComponentScan(basePackages = {
        "com.gmail.evanloafakahaitao.pcstore.dao",
        "com.gmail.evanloafakahaitao.pcstore.service",
        "com.gmail.evanloafakahaitao.pcstore.config",
        "com.gmail.evanloafakahaitao.pcstore.controller"
})
public class AppConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer configurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }
}
