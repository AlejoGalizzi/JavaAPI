package com.alkemy.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alkemy.constants.Constants;
import com.alkemy.jwt.JwtAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter{

    public JwtAuthenticationTokenFilter() {
        super("/api/**");
        
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException{
        final String header = request.getHeader(Constants.AUTHORIZATION_HEADER);
        
        if(header == null || !header.startsWith(Constants.BEARER_TOKEN)){
            throw new RuntimeException("jwt vacio");
        }

        String authenticationToken = header.split(" ")[1].trim();
        JwtAuthenticationToken token = new JwtAuthenticationToken(authenticationToken);

        return getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(javax.servlet.http.HttpServletRequest request, 
    javax.servlet.http.HttpServletResponse response, javax.servlet.FilterChain chain, 
    org.springframework.security.core.Authentication authResult) throws java.io.IOException, javax.servlet.ServletException {
        super.successfulAuthentication(request,response,chain,authResult);
        chain.doFilter(request, response);
    }

}