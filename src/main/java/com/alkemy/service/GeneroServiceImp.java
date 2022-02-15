package com.alkemy.service;

import java.util.List;

import com.alkemy.dao.IGeneroDao;
import com.alkemy.entity.Genero;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GeneroServiceImp implements IGeneroService{

    @Autowired
    private IGeneroDao generoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Genero> findAll() {
        return (List<Genero>)generoDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Genero findById(long id) {
        return generoDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Genero findByNombre(String nombre) {
        return generoDao.findByNombre(nombre);
    }

    @Override
    @Transactional
    public void saveGenero(Genero genero) {
        generoDao.save(genero);
        
    }

    @Override
    @Transactional
    public void deleteGenero(Genero genero) {
        generoDao.delete(genero);
    }

    
}