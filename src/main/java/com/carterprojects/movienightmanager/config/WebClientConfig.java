package com.carterprojects.movienightmanager.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

@Configuration
public class WebClientConfig {

    @Value("${tmdb.api.base-url}")
    String tmdbUrl;

    @Value("${tmdb.api.token}")
    String tmdbBearerToken;

    @Bean
    public WebClient tmdbClient() {
        return WebClient.builder()
                .baseUrl(tmdbUrl)
                .defaultHeaders((httpHeaders -> {
                    httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
                    httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + tmdbBearerToken);
                }))
                .build();
    }
}
