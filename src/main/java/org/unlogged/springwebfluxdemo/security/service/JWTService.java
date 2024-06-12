package org.unlogged.springwebfluxdemo.security.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class JWTService {

    private final SecretKey secretKey;
    private final JwtParser jwtParser;

    public JWTService() {
        this.secretKey = Keys.hmacShaKeyFor("123456xfgfgjfndsfndgnfdbsfnfnsf7890".getBytes());
        this.jwtParser = Jwts.parser().setSigningKey(this.secretKey).build(); //depracated
    }

    public String generate(String userName) {
        JwtBuilder builder = Jwts.builder()
                .setSubject(userName) //depracated
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(15, ChronoUnit.MINUTES)))
                .signWith(secretKey);
        return builder.compact();
    }

    public String getUserName(String token) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody(); //depracted
        return claims.getSubject();
    }

    public boolean validate(UserDetails user, String token) {
        Claims claims = jwtParser.parseClaimsJws(token).getBody(); //depracted
        boolean unexpired = claims.getExpiration().after(Date.from(Instant.now()));

        return unexpired && user.getUsername() == claims.getSubject();
    }
}

