package com.alkemy.service;

import java.util.List;

import com.alkemy.dao.IPersonajeDao;
import com.alkemy.entity.Personaje;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PersonajeServiceImp implements IPersonajeService{

    @Autowired
    private IPersonajeDao personajeDao;

    @Override
    @Transactional(readOnly = true)
    public List<Personaje> findAll() {
        return (List<Personaje>)personajeDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Personaje findById(long id) {
        return personajeDao.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Personaje findByNombre(String nombre) {
        return personajeDao.findByNombre(nombre);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Personaje> findByEdad(int edad) {
        return (List<Personaje>)personajeDao.findByEdad(edad);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Personaje> findByPeso(int peso) {
        return (List<Personaje>)personajeDao.findByPeso(peso);
    }

    @Override
    @Transactional
    public void savePersonaje(Personaje personaje) {
        personajeDao.save(personaje);
        
    }

    @Override
    @Transactional
    public Personaje updatePersonaje(Personaje personaje) {
        return (Personaje)personajeDao.save(personaje);
    }

    @Override
    @Transactional
    public void deletePersonaje(Personaje personaje) {
        personajeDao.deleteById(personaje.getId());
        
    }

    @Override
    @Transactional
    public void delete(Long id) {
        personajeDao.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAllPersonajes(){
        personajeDao.deleteAll();
    }

    

    
    

}