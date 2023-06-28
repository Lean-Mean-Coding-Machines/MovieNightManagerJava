package com.carterprojects.movienightmanager.controller.security;

import lombok.AllArgsConstructor;
import org.reflections.Reflections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {

    JwtAuthenticationFilter jwtAuthenticationFilter;

    AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        var unauthorizedRoutes = getUnauthorizedRoutes();
        http
                .cors(Customizer.withDefaults())
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(unauthorizedRoutes.toArray(new String[0]))
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    @Order(1)
    CorsConfigurationSource corsConfigurationSource() {
        var config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:3000", "http://localhost:8080"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("authorization", "content-type"));
        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    private List<String> getUnauthorizedRoutes() {
        List<String> unauthorizedRoutes = new ArrayList<>();

        Reflections reflections = new Reflections("com.carterprojects.movienightmanager.controller");
        Set<Class<?>> controllerClasses = reflections.getTypesAnnotatedWith(RestController.class);
        controllerClasses.forEach(
                controller -> {
                    String baseUrl = controller.getAnnotation(RequestMapping.class).value()[0];
                    for (Method endpoint : controller.getDeclaredMethods()) {
                        if (!endpoint.isAnnotationPresent(Authorize.class)) {
                            var path = "";
                            if (endpoint.isAnnotationPresent(PostMapping.class)) {
                                path = getPathFromOptions(
                                        endpoint.getAnnotation(PostMapping.class).path(),
                                        endpoint.getAnnotation(PostMapping.class).value()
                                );
                                unauthorizedRoutes.add(buildUrl(baseUrl, path));
                            } else if (endpoint.isAnnotationPresent(PutMapping.class)) {
                                path = getPathFromOptions(
                                        endpoint.getAnnotation(PutMapping.class).path(),
                                        endpoint.getAnnotation(PutMapping.class).value()
                                );
                                unauthorizedRoutes.add(buildUrl(baseUrl, path));
                            } else if (endpoint.isAnnotationPresent(GetMapping.class)) {
                                path = getPathFromOptions(
                                        endpoint.getAnnotation(GetMapping.class).path(),
                                        endpoint.getAnnotation(GetMapping.class).value()
                                );
                                unauthorizedRoutes.add(buildUrl(baseUrl, path));
                            } else if (endpoint.isAnnotationPresent(DeleteMapping.class)) {
                                path = getPathFromOptions(
                                        endpoint.getAnnotation(DeleteMapping.class).path(),
                                        endpoint.getAnnotation(DeleteMapping.class).value()
                                );
                                unauthorizedRoutes.add(buildUrl(baseUrl, path));
                            }
                        }
                    }
                }
        );

        return unauthorizedRoutes;
    }

    private String getPathFromOptions(String[] path1, String[] path2) {
        return path1.length > 0 ? path1[0] : path2.length > 0 ? path2[0] : "";
    }

    private String buildUrl(String baseUrl, String targetUrl) {
        if (targetUrl == null || targetUrl.isBlank()) {
            return baseUrl;
        }
        return baseUrl + "/" + (targetUrl.charAt(0) == '/' ? targetUrl.replaceFirst("/", "") : targetUrl);
    }
}