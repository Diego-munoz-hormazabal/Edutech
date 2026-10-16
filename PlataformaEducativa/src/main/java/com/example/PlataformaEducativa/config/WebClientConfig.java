package com.example.PlataformaEducativa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient comunaWebClienConfig() {
        return WebClient.builder()
                .baseUrl("http://gestrionempresa.us-east-1.elasticbeanstalk.com") 
                .build();
    }
}
