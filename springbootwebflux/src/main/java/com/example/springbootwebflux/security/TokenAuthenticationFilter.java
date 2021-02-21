package com.example.springbootwebflux.security;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;


@RequiredArgsConstructor
public class TokenAuthenticationFilter implements WebFilter {

    private final TokenProvider tokenProvider;

    public static final String HEADER_PREFIX = "Bearer ";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String token = resolveToken(exchange.getRequest());
        if (StringUtils.hasText(token) && this.tokenProvider.validateToken(token)) {
            Authentication authentication = this.tokenProvider.getAuthentication(token);
            return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication));
        }
        return chain.filter(exchange);
    }
    private String resolveToken(ServerHttpRequest request) {
        String bearerToken = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(HEADER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
//https://github.com/PacktPublishing/Hands-On-Spring-Security-5-for-Reactive-Applications/blob/master/Chapter06/spring-boot-security-webflux-jwt/src/main/java/com/packtpub/book/ch06/springsecurity/auth/JWTAuthWebFilter.java
//https://medium.com/zero-equals-false/protect-rest-apis-with-spring-security-reactive-and-jwt-7b209a0510f1