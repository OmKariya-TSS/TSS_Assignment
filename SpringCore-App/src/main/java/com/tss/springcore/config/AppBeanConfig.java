package com.tss.springcore.config;

import com.tss.springcore.model.Computer;
import com.tss.springcore.model.HardDisk;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppBeanConfig {
    @Bean
    HardDisk hardDisk(){
        return new HardDisk(256);
    }
    @Bean
    Computer computer(){
        return new Computer("Apple",new HardDisk(256));
    }
}
