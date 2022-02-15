package com.alkemy.config;

import java.util.Collections;

import com.alkemy.security.JwtAuthenticationEntryPoint;
import com.alkemy.security.JwtAuthenticationProvider;
import com.alkemy.security.JwtAuthenticationTokenFilter;
import com.alkemy.security.JwtSuccessHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
@Component
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter{

    @Autowired 
    private JwtAuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    @Bean
    public AuthenticationManager authenticationManager(){
        return new ProviderManager(Collections.singletonList(authenticationProvider));
    }

    @Bean
    public JwtAuthenticationTokenFilter authenticationTokenFilter(){
        JwtAuthenticationTokenFilter tokenFilter = new JwtAuthenticationTokenFilter();
        tokenFilter.setAuthenticationManager(authenticationManager());
        tokenFilter.setAuthenticationSuccessHandler(new JwtSuccessHandler());
        return tokenFilter;
    }

    @Override
    protected void configure(HttpSecurity http)throws Exception{
        http.csrf().disable()   
        .authorizeRequests().antMatchers("**/characters/**").authenticated()
        .and()
        .authorizeRequests().antMatchers("**/movies/**").authenticated()
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);


        http.exceptionHandling()
        .authenticationEntryPoint(authenticationEntryPoint);
        http.addFilterBefore(authenticationTokenFilter(),UsernamePasswordAuthenticationFilter.class);
        http.headers().cacheControl();
    }
}