package com.example.Evaluacion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient comunaWebClienConfig() {
        return WebClient.builder()
                .baseUrl("http://gestioncurso1.us-east-1.elasticbeanstalk.com") // puerto del microservicio comuna
                .build();
    }
}
