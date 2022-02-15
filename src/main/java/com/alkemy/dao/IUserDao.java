package com.alkemy.dao;

import java.util.Optional;

import com.alkemy.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserDao extends JpaRepository<User,Integer>{

    public Optional<User> findByUsername(String username);

    public Optional<User> findByEmail(String email);

    public boolean existsByEmail(String email);

    public boolean existsByUsername(String username);


}