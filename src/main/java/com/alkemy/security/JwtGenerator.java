package com.alkemy.security;

import com.alkemy.constants.Constants;
import com.alkemy.entity.User;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtGenerator{
    public String generar(User jwtUser){
        Claims claims = Jwts.claims()
                        .setSubject(jwtUser.getUsername());
        claims.put(Constants.USER_ID, String.valueOf(jwtUser.getId()));
        claims.put(Constants.ROLE, jwtUser.getRole());
        return Jwts.builder()
                    .setClaims(claims)
                    .signWith(SignatureAlgorithm.HS256,Constants.YOUR_SECRET)
                    .compact();
    }
}