package com.example.blog.users.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecuyityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
          
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
        .csrf(csrf ->csrf.disable())
        .authorizeHttpRequests(auth -> auth.requestMatchers("/api/users/register")
        .permitAll().requestMatchers("/api/posts", "/api/posts/recent", "/api/posts/search", "/api/posts/author/**")
        .permitAll().
        anyRequest().authenticated());

        return http.build();
      

    }

}
