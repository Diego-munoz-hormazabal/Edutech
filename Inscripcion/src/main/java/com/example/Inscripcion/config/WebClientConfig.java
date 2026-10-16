package com.example.Inscripcion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient usuarioWebClient() {
        return WebClient.builder().baseUrl("http://gestionusuario1.us-east-1.elasticbeanstalk.com").build();
    }

    @Bean
    public WebClient plataformaWebClient() {
        return WebClient.builder().baseUrl("http://gestionplataforma.us-east-1.elasticbeanstalk.com").build();
    }
}
