package com.sensed.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults()) // Configuración estándar de CORS
            .csrf(csrf -> csrf.disable())    // Deshabilitar CSRF para pruebas
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()    // Permitir todo el tráfico
            );
        return http.build();
    }
}