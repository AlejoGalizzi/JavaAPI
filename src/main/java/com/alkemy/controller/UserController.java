package com.alkemy.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import com.alkemy.entity.User;
import com.alkemy.security.JwtGenerator;
import com.alkemy.service.IUserService;
import com.alkemy.service.MailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class UserController {

    @Bean
    PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder(10,new SecureRandom());
    }
    
    @Autowired 
    private IUserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private MailService mailService;
    
    private JwtGenerator jwtGenerator;

    public UserController(JwtGenerator jwtGenerator){
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping(value="/register")
    public ResponseEntity<?> register(@Valid @RequestBody User user) throws MessagingException {
        if(user.getPassword().length()<4 || user.getPassword().length()>16){
            throw new MessagingException("El password debe tener como minimo 4 caracteres y como maximo 16");
        }
        if(!userService.existsByUsername(user.getUsername()) && !userService.existsByEmail(user.getEmail())){
            User userDB = new User(user.getUsername(), user.getEmail(), passwordEncoder.encode(user.getPassword()) ,"admin");
            userService.save(userDB);
            mailService.sendTextEmail(userDB.getEmail(),"Bienvenido "+userDB.getUsername()+" a mi API.", "Bienvenido a mi API!!");
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.FOUND);
    }

    @PostMapping("/login")
    public ResponseEntity<?> generate(@RequestBody final User login) throws MessagingException {
        if(login.getPassword().length()<4 || login.getPassword().length()>16){
            throw new MessagingException("El password debe tener como minimo 4 caracteres y como maximo 16");
        }
        User jwtUser = new User();
        jwtUser = existUser(login);
        if(jwtUser != null){
            List<String> lista = new ArrayList<>();
            lista.add(jwtGenerator.generar(jwtUser));
            return new ResponseEntity<List<String>>(lista,HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    private User existUser(User login) {
        
        if(userService.existsByEmail(login.getEmail())){
            User userDB = userService.findByEmail(login.getEmail());
            // System.out.println(passwordEncoder.encode(login.getPassword()));
            if(passwordEncoder.matches(login.getPassword(),userDB.getPassword())){
                userDB.setRole("Admin");
                return userDB;
            }
        }
        return null;
    }
    

}
