package com.example.assignment03.configure;

import com.fasterxml.jackson.databind.json.JsonMapper;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ComponentConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public JsonMapper jsonMapper(){
        return new JsonMapper();
    }


}
