package com.alkemy.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.alkemy.entity.Pelicula;
import com.alkemy.entity.Personaje;
import com.alkemy.model.MPersonaje;
import com.alkemy.model.MPersonajePelicula;
import com.alkemy.service.IPeliculaService;
import com.alkemy.service.IPersonajeService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api")
public class PeliculasPersonajesController{

    @Autowired
    private IPersonajeService personajeService;

    @Autowired
    private IPeliculaService peliculaService;

    @GetMapping(value = "/characters",params = {"movies"})
    public ResponseEntity<?> getPeliculasPersonaje(@RequestParam(value = "movies") long id){
        if(peliculaService.findById(id) != null){
            Pelicula peliculaDB = (Pelicula)peliculaService.findById(id);
            Collection<Personaje> lPersonajes = peliculaDB.getSetPersonajes();
            Collection<MPersonaje> cPersonajes = castearPersonajes(lPersonajes);
            if(cPersonajes !=null){
                return new ResponseEntity<>(cPersonajes,HttpStatus.OK);
            }
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/characters/asignar_pelicula")
    public ResponseEntity<?> getPeliculasPersonaje(@RequestBody MPersonajePelicula mPersonajePelicula){
        Pelicula peliculaDB = (Pelicula)peliculaService.findById(mPersonajePelicula.getPelicula().getId());
        if(peliculaDB != null){
            Personaje personajeDB = (Personaje)personajeService.findById(mPersonajePelicula.getPersonaje().getId());
            
            if(!peliculaDB.getSetPersonajes().contains(personajeDB)){
                peliculaDB.getSetPersonajes().add(personajeDB);
                peliculaService.savePelicula(peliculaDB);
                return new ResponseEntity<Void>(HttpStatus.CREATED);
            }else{
                return new ResponseEntity<Void>(HttpStatus.FOUND);
            }
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    private List<MPersonaje> castearPersonajes(Collection<Personaje> lPersonajes){
        List<MPersonaje> lMPersonajes = new ArrayList<>();
        for (Personaje personaje : lPersonajes) {
            MPersonaje aux = new MPersonaje(personaje);
            lMPersonajes.add(aux);
        }
        return lMPersonajes;
    }
    
}