package com.example.nutri_well.config.auth;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Appconfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
