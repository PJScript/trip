package com.traveloper.tourfinder.auth.jwt;

import com.traveloper.tourfinder.auth.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenUtils {
    private final Key siginingKey;
    private final JwtParser jwtParser;

    public JwtTokenUtils(
            @Value("${jwt.secret}")
            String jwtSecret
    ) {
        this.siginingKey = Keys.hmacShaKeyFor(jwtSecret.getBytes());
        this.jwtParser = Jwts
                .parserBuilder()
                .setSigningKey(this.siginingKey)
                .build();
    }

    public String generateToken(String uuid, Integer expiredSecond){
        Instant now = Instant.now();
        Claims jwtClaims = Jwts.claims()
                .setSubject(uuid)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(expiredSecond)));

        return Jwts.builder()
                .setClaims(jwtClaims)
                .signWith(this.siginingKey)
                .compact();
    }

    public String generateToken(Member member, Integer expiredSecond){
        Instant now = Instant.now();
        Claims jwtClaims = Jwts.claims()
                .setSubject(member.getUuid())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(expiredSecond)));

        return Jwts.builder()
                .setClaims(jwtClaims)
                .signWith(this.siginingKey)
                .compact();
    }

    public String generateToken(Member member) {
        Instant now = Instant.now();
        Claims jwtClaims = Jwts.claims()
                .setSubject(member.getUuid())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusSeconds(60 * 60)));

        return Jwts.builder()
                .setClaims(jwtClaims)
                .signWith(this.siginingKey)
                .compact();
    }

    public boolean validate(String token) {
        try {
            jwtParser.parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.warn("invalid jwt");
        } return false;
    }

    public Claims parseClaims(String token) {
        return jwtParser
                .parseClaimsJws(token)
                .getBody();
    }
}