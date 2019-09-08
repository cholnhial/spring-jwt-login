package com.cholnhial.springjwtlogin.security;

import com.cholnhial.springjwtlogin.model.ApplicationUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Key;
import java.util.Collections;
import java.util.Date;

import static com.cholnhial.springjwtlogin.constant.SecurityConstants.EXPIRATION_TIME;
import static com.cholnhial.springjwtlogin.constant.SecurityConstants.KEY;
import static io.jsonwebtoken.security.Keys.hmacShaKeyFor;

@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        try {
            ApplicationUser applicationUser = new ObjectMapper().readValue(request.getInputStream(), ApplicationUser.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            applicationUser.getUsername(),
                            applicationUser.getPassword(), Collections.emptyList()));

        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        Date tokenExpiration = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        SecretKey key = hmacShaKeyFor(KEY.getBytes());
        Claims claims = Jwts.claims().setSubject(((User) authResult.getPrincipal()).getUsername());
        String token = Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, key)
                .setExpiration(tokenExpiration)
                .compact();

        response.setHeader("token", token);
    }
}
