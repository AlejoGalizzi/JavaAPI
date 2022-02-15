package com.alkemy.service;

import java.util.Date;
import java.util.List;

import com.alkemy.dao.IPeliculaDao;
import com.alkemy.entity.Pelicula;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class PeliculaServiceImp implements IPeliculaService{

    @Autowired
    private IPeliculaDao peliculaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Pelicula> findAll() {
        return (List<Pelicula>)peliculaDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Pelicula findByTitulo(String titulo) {
        return peliculaDao.findByTitulo(titulo);
    }

    @Override
    @Transactional(readOnly = true)
    public Pelicula findByTituloAndFecha_creacion(String titulo, Date fecha_creacion) {
        
        return peliculaDao.findByTituloAndFecha_creacion(titulo, fecha_creacion);
    }


    @Override
    @Transactional(readOnly = true)
    public List<Pelicula> lFindByTitulo(String titulo) {
        return peliculaDao.lFindByTitulo(titulo);
    }

    @Override
    @Transactional(readOnly = true)
    public Pelicula findById(long id) {
        return peliculaDao.findById(id).orElse(null);
    }
    
    @Override
    @Transactional
    public Pelicula savePelicula(Pelicula pelicula) {
       return peliculaDao.save(pelicula);
        
    }

    @Override
    @Transactional
    public Pelicula updatePelicula(Pelicula pelicula) {
        return (Pelicula)peliculaDao.save(pelicula);
    }

    @Override
    @Transactional
    public void deletePelicula(Pelicula pelicula) {
        peliculaDao.delete(pelicula);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        peliculaDao.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pelicula> findPeliculasGenero(Long id) {
        return (List<Pelicula>)peliculaDao.findByGeneroId(id);
    }

    
    

    
}