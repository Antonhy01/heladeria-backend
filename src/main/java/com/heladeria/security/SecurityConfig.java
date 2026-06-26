package com.heladeria.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http

                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        // LOGIN
                        .requestMatchers("/api/login").permitAll()

                        // CRUD USUARIOS
                        .requestMatchers("/api/usuarios/**").permitAll()

                        // CRUD CATEGORIAS
                        .requestMatchers("/api/categorias/**").permitAll()

                        // CRUD PRODUCTOS
                        .requestMatchers("/api/productos/**").permitAll()

                        // CRUD CLIENTES
                        .requestMatchers("/api/clientes/**").permitAll()

                        // CRUD VENTAS
                        .requestMatchers("/api/ventas/**").permitAll()

                        // CRUD DETALLES
                        .requestMatchers("/api/detalles/**").permitAll()

                        .anyRequest().authenticated())

                .httpBasic(Customizer.withDefaults());

        return http.build();

    }

    @Bean
    PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();

    }

}