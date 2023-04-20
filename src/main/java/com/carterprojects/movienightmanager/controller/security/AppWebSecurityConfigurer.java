package com.carterprojects.movienightmanager.controller.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppWebSecurityConfigurer {

    // TODO :: Revisit this when doing security
    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.antMatcher("/**")  
        .authorizeRequests()  
        .antMatchers("/**").permitAll();

        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());

        http.csrf().disable();
        return http.build();
    }
}