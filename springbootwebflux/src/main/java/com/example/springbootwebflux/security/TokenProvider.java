package com.example.springbootwebflux.security;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TokenProvider {

    public boolean validateToken(String token) {
        FirebaseToken decodedToken = null;
        try {
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
        } catch (FirebaseAuthException e) {
            log.error("Firebase Exception {}", e.getLocalizedMessage());
        }

        if(decodedToken != null) {
            return true;
        } else

        return false;
    }

    public Authentication getAuthentication(String token) {
        FirebaseToken decodedToken = null;
        try {
            decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
        } catch (FirebaseAuthException e) {
            log.error("Firebase Exception {}", e.getLocalizedMessage());
        }

        CustomPrincipal customPrincipal = new CustomPrincipal();
        customPrincipal.setUid(decodedToken.getUid());
        customPrincipal.setPhoneNumber((String) decodedToken.getClaims().get("phone_number"));
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                customPrincipal, decodedToken, null);
        return authentication;
    }
}
