package com.carterprojects.movienightmanager.controller.security;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    // TODO :: Revisit this when doing security
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**")  
        .authorizeRequests()  
        .antMatchers("/**").permitAll();

        http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());

        http.csrf().disable();
    }
}