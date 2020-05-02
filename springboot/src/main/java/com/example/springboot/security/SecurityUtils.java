package com.example.springboot.security;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
public class SecurityUtils {

    public String getTokenFromRequest(HttpServletRequest request) {
        String token = null;
        Cookie cookieToken = WebUtils.getCookie(request, "token");
        if (cookieToken != null) {
            token = cookieToken.getValue();
        } else {
            String bearerToken = request.getHeader("Authorization");
            System.out.println("bearerToken 1 "+ bearerToken);
            if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
                token = bearerToken.substring(7, bearerToken.length());
            }
        }
        return token;
    }
}
