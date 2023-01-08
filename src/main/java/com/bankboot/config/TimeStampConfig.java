package com.bankboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Timestamp;


@Configuration
public class TimeStampConfig {

    @Bean
    public Timestamp timestamp() {
        return new Timestamp(0);
    }


}
