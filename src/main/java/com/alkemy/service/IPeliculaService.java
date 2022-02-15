package com.alkemy.service;

import java.util.Date;
import java.util.List;

import com.alkemy.entity.Pelicula;

public interface IPeliculaService{
    
    public List<Pelicula> findAll();

    public Object findById(long id);

    public Pelicula findByTitulo(String titulo);

    public Pelicula findByTituloAndFecha_creacion(String titulo,Date fecha_creacion);

    public List<Pelicula> lFindByTitulo(String titulo);

    public List<Pelicula> findPeliculasGenero(Long id);


    // public Pelicula findByNombreYFecha_Creacion(String nombre,Date fecha_creacion);

    public Pelicula savePelicula(Pelicula pelicula);

    public Pelicula updatePelicula(Pelicula pelicula);

    public void deletePelicula(Pelicula pelicula);

    public void deleteById(Long id);

    
}