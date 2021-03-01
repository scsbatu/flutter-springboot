package com.example.springbootwebflux.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.web.reactive.config.EnableWebFlux;

@Configuration
@EnableWebFluxSecurity
@EnableWebFlux
public class SecurityConfig {


    @Bean
    SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http, TokenProvider tokenProvider) {
      return http.cors().and()
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
                .securityContextRepository(NoOpServerSecurityContextRepository.getInstance())
                .authorizeExchange(it -> it
                        .pathMatchers("/api/**").authenticated()
                        .anyExchange().permitAll()
                )
                .addFilterAt(new TokenAuthenticationFilter(tokenProvider), SecurityWebFiltersOrder.AUTHENTICATION)
                .build();


    }
}