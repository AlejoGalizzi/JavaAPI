package com.alkemy.service;

import java.util.List;

import com.alkemy.dao.IUserDao;
import com.alkemy.entity.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements IUserService{

    @Autowired
    private IUserDao userDao;

    @Override
    public List<User> findAll() {
        return (List<User>)userDao.findAll();
    }


    @Override
    public boolean existsByUsername(String username) {
        return userDao.existsByEmail(username);
    }

    @Override
    public User findByEmail(String email) {
        return userDao.findByEmail(email).orElse(null);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userDao.existsByEmail(email);
    }


    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username).orElse(null);
    }


    @Override
    public void save(User user) {
        userDao.save(user);
        
    }
    
}
