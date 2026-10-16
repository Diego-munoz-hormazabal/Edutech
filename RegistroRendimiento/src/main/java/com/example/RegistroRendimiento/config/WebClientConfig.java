package com.example.RegistroRendimiento.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    @Bean
    public WebClient PlataformaEducativaWebClienConfig() {
        return WebClient.builder()
                .baseUrl("http://gestionplataforma.us-east-1.elasticbeanstalk.com") 
                .build();
    }
}
