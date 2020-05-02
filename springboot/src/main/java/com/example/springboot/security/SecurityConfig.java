package com.example.springboot.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public TokenFilter tokenAuthenticationFilter() {
        return new TokenFilter();
    }

    @Bean
    public AuthenticationEntryPoint restAuthenticationEntryPoint() {
        return new AuthenticationEntryPoint() {
            @Override
            public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                 AuthenticationException e) throws IOException, ServletException {
                Map<String, Object> errorObject = new HashMap<>();
                int errorCode = 403;
                errorObject.put("message", "Access Denied");
                errorObject.put("error", HttpStatus.FORBIDDEN);
                errorObject.put("code", errorCode);
                errorObject.put("timestamp", new Timestamp(new Date().getTime()));
                httpServletResponse.setContentType("application/json;charset=UTF-8");
                httpServletResponse.setStatus(errorCode);
                httpServletResponse.getWriter().write(objectMapper.writeValueAsString(errorObject));
            }
        };
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable()
                .formLogin().disable()
                .httpBasic().disable()
                .exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint())
                .and().authorizeRequests()
//              .antMatchers().permitAll()
                .anyRequest().authenticated();
        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
