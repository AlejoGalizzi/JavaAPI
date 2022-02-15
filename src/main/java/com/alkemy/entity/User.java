package com.alkemy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "user",
        uniqueConstraints={
            @UniqueConstraint(columnNames = "username"),
            @UniqueConstraint(columnNames = "password")
        })
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "El nombre del usuario no puede estar en blanco")
    @Size(max = 20)
    private String username;

    @NotEmpty(message = "El mail del usuario no puede estar en blanco")
    @Size(max = 50)
    @Column(unique = true)
    @Email(message = "El mail no tiene el formato adecuado")
    private String email;

    @NotEmpty(message = "La contraseña del usuario no puede estar en blanco")
    private String password;

    private String role;
    
    public User() {
    }


    public User(String username, String email, String password,String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    
}
