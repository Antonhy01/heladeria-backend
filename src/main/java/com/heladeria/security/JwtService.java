package com.heladeria.security;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
@Service
public class JwtService {

    private static final String SECRET =
            "Heladeria2026ProyectoSpringBootJWTSeguridad123456789";

    private final SecretKey key =
            Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String usuario){

        return Jwts.builder()
                .subject(usuario)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+86400000))
                .signWith(key)
                .compact();

    }

    public String extractUsername(String token){

        return getClaims(token).getSubject();

    }

    public boolean isTokenValid(String token){

        return !getClaims(token)
                .getExpiration()
                .before(new Date());

    }

    private Claims getClaims(String token){

        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

}