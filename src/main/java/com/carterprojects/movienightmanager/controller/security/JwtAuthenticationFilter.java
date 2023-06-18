package com.carterprojects.movienightmanager.controller.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    JwtService jwtServiceImpl;
    UserDetailsService userServiceImpl;

    @Override
    protected void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        final var authHeader = servletRequest.getHeader("authorization");

        if ("OPTIONS".equals(servletRequest.getMethod())) {
            servletResponse.setStatus(HttpServletResponse.SC_OK);
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        final var token = authHeader.substring(7);
        final var username = jwtServiceImpl.extractUsername(token);
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            var userDetails = userServiceImpl.loadUserByUsername(username);
            if (jwtServiceImpl.isTokenValid(token, userDetails)) {
                var authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(servletRequest)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }
}