package com.cic.ejercicio07;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @SuppressWarnings("removal")
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/public").permitAll()
                    .requestMatchers("/coches").authenticated()
                    .requestMatchers("/coches/**").authenticated()
                    .anyRequest().permitAll()
            )
            .oauth2ResourceServer(oauth2ResourceServer ->
            oauth2ResourceServer.jwt()
            );
        return http.build();
    }
}