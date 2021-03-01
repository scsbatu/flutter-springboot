package com.example.springbootwebflux.security;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TokenProviderTest {

    @InjectMocks
    private TokenProvider tokenProvider;

    @Mock
    FirebaseAuth firebaseAuth;

    @Mock
    FirebaseToken decodedToken;


    @Test
    public void shouldDoSomething() throws FirebaseAuthException {
        when(decodedToken.getUid()).thenReturn("11111111111111");
        when(firebaseAuth.verifyIdToken(any())).thenReturn(decodedToken);
        assertTrue(tokenProvider.validateToken("111111111111111111111111"));
    }
}
