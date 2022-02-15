package com.alkemy.service;

import java.util.List;

import com.alkemy.entity.Genero;

public interface IGeneroService{
    public List<Genero> findAll();

    public Genero findById(long id);

    public Genero findByNombre(String nombre);

    public void saveGenero(Genero genero);

    public void deleteGenero(Genero genero);
}