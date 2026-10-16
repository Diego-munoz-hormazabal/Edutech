package com.example.Curso.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient contenidoWebClienConfig() {
        return WebClient.builder()
                .baseUrl("http://gestioncontenidoeducativo.us-east-1.elasticbeanstalk.com/") // puerto del microservicio contenido
                .build();
    }
}
