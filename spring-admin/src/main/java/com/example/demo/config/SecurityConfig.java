package com.example.demo.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(@NotNull  HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .formLogin().defaultSuccessUrl("/wallboard")
                .and()
                .logout().logoutSuccessUrl("/login")
                .and()
                .authorizeHttpRequests().anyRequest().authenticated()
                .and()
                .httpBasic();

        return http.build();

    }
}
