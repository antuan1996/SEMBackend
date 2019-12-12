package com.khoubyari.example.tests.controllersTest;

import com.khoubyari.example.api.rest.AuthController;
import com.khoubyari.example.api.rest.AuthenticationRequest;
import com.khoubyari.example.api.rest.SignUpRequest;
import com.khoubyari.example.dao.jpa.UserRepository;
import com.khoubyari.example.domain.User;
import com.khoubyari.example.security.JwtTokenProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AuthControllerTest {

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    JwtTokenProvider jwtTokenProvider;

    @Mock
    UserRepository userRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @InjectMocks
    AuthController authController;

    @Test
    public void testSignin(){
        User user = new User();
        String email = "test@gmail.com";
        String password = "password";
        user.setEmail(email);
        when(passwordEncoder.encode(password)).thenReturn(password);
        user.setPassword(password);
        AuthenticationRequest request = new AuthenticationRequest();
        request.setEmail(email);
        request.setPassword(password);
        when(userRepository.findByEmail(email)).thenReturn(java.util.Optional.of(user));
        when(jwtTokenProvider.createToken(anyString(), anyListOf(String.class))).thenReturn("token");
        ResponseEntity result = authController.signin(request);
        assertEquals(result.getStatusCodeValue(), 200);
    }

    @Test
    public void testSignup(){
        SignUpRequest request = new SignUpRequest();
        String password = "password";
        String email = "test@gmail.com";
        request.setEmail(email);
        request.setPassword(password);
        request.setFirstName("first");
        request.setLastName("last");
        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        when(passwordEncoder.encode(password)).thenReturn(password);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(jwtTokenProvider.createToken(anyString(), anyListOf(String.class))).thenReturn("token");
        ResponseEntity result = authController.signup(request);
        assertEquals(result.getStatusCodeValue(), 200);
    }

}
