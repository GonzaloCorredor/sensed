package com.sensed.backend.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults()) // Activa las reglas CORS de abajo
            .csrf(csrf -> csrf.disable())    // Desactiva CSRF para que no bloquee los POST
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()    // Permite todas las peticiones
            );
        return http.build();
    }

    // ¡Esta es la "lista de invitados" para el portero!
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        
        // Autorizamos a tus dominios reales, al de Vercel y al localhost para pruebas
        configuration.setAllowedOrigins(Arrays.asList(
            "https://sensed.es", 
            "https://www.sensed.es", 
            "https://sensed-frontend.vercel.app", 
            "http://localhost:5500", 
            "http://127.0.0.1:5500"
        ));
        
        // Permitimos todos los métodos (POST para registro, GET, etc.)
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        
        // Permitimos cualquier tipo de cabecera de datos
        configuration.setAllowedHeaders(Arrays.asList("*"));
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration); // Aplica esto a todas las rutas (/api/auth/...)
        return source;
    }
}