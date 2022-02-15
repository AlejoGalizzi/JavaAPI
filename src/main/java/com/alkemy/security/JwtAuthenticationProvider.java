package com.alkemy.security;

import java.util.List;

import com.alkemy.entity.User;
import com.alkemy.jwt.JwtUserDetails;
import com.alkemy.jwt.JwtAuthenticationToken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider{

    @Autowired
    private JwtValidator jwtValidator;

    @Override
    protected void additionalAuthenticationChecks(UserDetails arg0, UsernamePasswordAuthenticationToken arg1)
            throws AuthenticationException {
        
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {
        JwtAuthenticationToken jwtAutheticationToken = (JwtAuthenticationToken)authentication;
        String token = jwtAutheticationToken.getToken();
        User jwtUser = jwtValidator.validate(token);
        if(jwtUser == null){
            throw new RuntimeException("jwt vacio");
        }
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                                                    .commaSeparatedStringToAuthorityList(jwtUser.getRole()); 
        return new JwtUserDetails(jwtUser.getUsername(), token, jwtUser.getId(), grantedAuthorities);

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthenticationToken.class.isAssignableFrom(authentication);
    }

    

}
