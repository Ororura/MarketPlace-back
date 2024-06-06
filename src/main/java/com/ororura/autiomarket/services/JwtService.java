package com.ororura.autiomarket.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

@Service
public class JwtService {
    public String generateJwtToken(UserDetails user) {

        return Jwts.builder()
                .subject((user.getUsername()))
                .issuedAt(new Date())
                .expiration(new Date((new Date()).getTime() + 60000000))
                .signWith(getSigningKey())
                .compact();
    }

    public SecretKey getSigningKey() {
        return Jwts.SIG.HS256.key().build();
    }

    public Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }
}
