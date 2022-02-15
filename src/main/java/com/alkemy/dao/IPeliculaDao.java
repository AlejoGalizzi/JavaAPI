package com.alkemy.dao;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.alkemy.entity.Pelicula;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPeliculaDao extends JpaRepository<Pelicula,Long>{

    public Optional<Pelicula> findById(long id);
    // public Pelicula findByNombreAndFecha_creacion(String nombre,Date fecha_creacion);
    public Pelicula findByTitulo(String titulo);
    public List<Pelicula> findByGeneroId(Long id);
    
    @Query(value = "SELECT * FROM Pelicula p WHERE p.titulo = ?1 AND p.fecha_creacion = ?2",nativeQuery = true)
    public Pelicula findByTituloAndFecha_creacion(String titulo,Date fecha_creacion);

    @Query(value = "SELECT * FROM Pelicula p WHERE p.titulo =?1",nativeQuery=true)
    public List<Pelicula> lFindByTitulo(String titulo);
    
}