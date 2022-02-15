package com.alkemy.dao;

import java.util.List;
import java.util.Optional;

import com.alkemy.entity.Personaje;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonajeDao extends CrudRepository<Personaje,Long>{

    public Personaje findByNombre(String nombre);
    public Optional<Personaje> findById(Long id);

   public List<Personaje> findByEdad(int edad);
   public List<Personaje> findByPeso(int peso);
    
}