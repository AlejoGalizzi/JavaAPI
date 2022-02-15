package com.alkemy.service;

import java.util.List;

import com.alkemy.entity.Personaje;

public interface IPersonajeService{

    public List<Personaje> findAll();

    public Object findById(long id);

    public Personaje findByNombre(String nombre);

    public List<Personaje> findByEdad(int edad);

    public List<Personaje> findByPeso(int peso);

    public void savePersonaje(Personaje personaje);

    public Personaje updatePersonaje(Personaje personaje);

    public void deletePersonaje(Personaje personaje);

    public void delete(Long id);

    public void deleteAllPersonajes();

    

}