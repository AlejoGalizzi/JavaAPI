package com.alkemy.security;

import com.alkemy.constants.Constants;
import com.alkemy.entity.User;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator{
    public User validate(String token){
        User jwtUser = null;

        try{
            Claims body = (Claims) Jwts.parser()
                            .setSigningKey(Constants.YOUR_SECRET)
                            .parseClaimsJws(token)
                            .getBody();
            jwtUser = new User();
            jwtUser.setUsername(body.getSubject());
            jwtUser.setId(Long.parseLong((String)body.get(Constants.USER_ID)));
            jwtUser.setRole((String)body.get(Constants.ROLE));
        }catch(Exception e){
            System.out.println(e);
        }

        return jwtUser;
    }
}