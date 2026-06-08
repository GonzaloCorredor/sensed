package com.sensed.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors().and() // <--- ¡Aquí está la línea que permite que tu CorsConfig funcione!
            .csrf().disable() // Desactivamos CSRF para que tu frontend pueda conectar
            .authorizeHttpRequests((auth) -> auth
                .anyRequest().permitAll() // Permitimos todas las peticiones
            );
        return http.build();
    }
}