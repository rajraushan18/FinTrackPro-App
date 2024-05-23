package com.expense.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class UserConfig {

	
	@Bean
    public ObjectMapper objectMapperInit(){
        return new ObjectMapper();
    }
	
}
