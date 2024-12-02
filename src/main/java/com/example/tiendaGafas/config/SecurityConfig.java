package com.example.tiendaGafas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.tiendaGafas.jwt.JwtAuthenticationFilter;
import org.springframework.security.authentication.AuthenticationProvider; // Cambiar esta importación

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authProvider; 

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        
        return http
            .csrf(csrf -> csrf.disable())
            .cors().and()
            .authorizeRequests(authRequest ->
                authRequest
                    .requestMatchers("/auth/**").permitAll()
                    .requestMatchers("/css/**", "/img/**", "/js/**").permitAll()
                    .anyRequest().authenticated()
            )
            .sessionManagement(sessionManager ->
                sessionManager
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )
            .authenticationProvider(authProvider)
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .build();
    }
}
