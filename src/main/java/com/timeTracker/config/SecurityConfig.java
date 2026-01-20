package com.timeTracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.*;

@Configuration
public class SecurityConfig {

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .csrf(csrf -> csrf.ignoringRequestMatchers("/api/billing/webhook")) // webhook
      .authorizeHttpRequests(auth -> auth
        .requestMatchers("/login", "/auth/**", "/css/**", "/js/**").permitAll()
        .requestMatchers("/api/billing/webhook").permitAll()
        .anyRequest().permitAll() // MVP: реальную защиту сделаем через проверку session userId
      )
      .formLogin(form -> form.disable());

    return http.build();
  }
}
