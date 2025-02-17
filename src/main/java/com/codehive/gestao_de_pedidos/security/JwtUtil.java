package com.codehive.gestao_de_pedidos.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Configuration
public class JwtUtil {

    private static final String SECRET_KEY = "sua_chave_secreta_sua_chave_secreta"; // Deve ter pelo menos 32 bytes
    private static final Key KEY = Keys.hmacShaKeyFor(SECRET_KEY.getBytes()); // Gera uma chave segura
    private static final long EXPIRATION_TIME = 86400000; // 24 horas

    // Método para gerar o Token JWT
    public static String generateToken(String username) {
        return Jwts.builder()
                .subject(username) // Usar .subject() ao invés de .setSubject()
                .issuedAt(new Date()) // Usar .issuedAt()
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // .expiration()
                .signWith(KEY) // Nova forma de assinar com chave segura
                .compact();
    }

    // Método para validar o Token JWT
    public static boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith((SecretKey) KEY).build().parseSignedClaims(token); // Novo método de parsing
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    // Método para extrair o usuário do Token JWT
    public static String extractUsername(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) KEY).build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}
