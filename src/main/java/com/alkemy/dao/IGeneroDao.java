package com.alkemy.dao;

import java.util.Optional;

import com.alkemy.entity.Genero;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGeneroDao extends CrudRepository<Genero,Long>{

    public Genero findByNombre(String nombre);
    public Optional<Genero> findById(Long id);
}