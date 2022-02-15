package com.alkemy.service;

import java.util.List;


import com.alkemy.entity.User;



public interface IUserService{

    public List<User> findAll();

    public User findByUsername(String username);

    public User findByEmail(String email);

    public boolean existsByUsername(String username);

    public boolean existsByEmail(String email);

    public void save(User user);

}
