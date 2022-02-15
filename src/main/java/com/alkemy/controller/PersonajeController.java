package com.alkemy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Null;

import com.alkemy.entity.Personaje;
import com.alkemy.model.MPersonaje;
import com.alkemy.service.IPersonajeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/characters")
public class PersonajeController{

    @Autowired
    private IPersonajeService personajeService;

    @GetMapping
    public ResponseEntity<?> getPersonajes(){
        if(personajeService.findAll() != null){
            
            List<MPersonaje> lPersonajes = castearPersonajes(personajeService.findAll());
            return new ResponseEntity<>(lPersonajes,HttpStatus.OK);
        }else{
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(params = {"nombre"})
    public ResponseEntity<?> getPersonaje(@RequestParam(value = "nombre") String nombre){
        if(personajeService.findByNombre(nombre) != null){
            Personaje personaje = personajeService.findByNombre(nombre);
            return new ResponseEntity<>(personaje,HttpStatus.OK);
        }else{
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(params = {"edad"})
    public ResponseEntity<?> getPersonajesEdad(@RequestParam(value = "edad") int edad){
        if(personajeService.findByEdad(edad) != null){
            List<MPersonaje> lPersonajes = castearPersonajes(personajeService.findByEdad(edad));
            return new ResponseEntity<>(lPersonajes,HttpStatus.OK);
        }else{
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(params = {"peso"})
    public ResponseEntity<?> getPersonajesPeso(@RequestParam(value = "peso") int peso){
        if(personajeService.findByPeso(peso) != null){
            
            List<MPersonaje> lPersonajes = castearPersonajes(personajeService.findByPeso(peso));
            return new ResponseEntity<>(lPersonajes,HttpStatus.OK);
        }else{
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonaje(@PathVariable(value = "id")Long id){
        if(personajeService.findById(id) != null){
            return new ResponseEntity<>((Personaje)personajeService.findById(id),HttpStatus.OK);
        }else{
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Void> createPersonaje(@Valid @RequestBody Personaje personaje){
        if(personajeService.findByNombre(personaje.getNombre()) == null){
            personajeService.savePersonaje(personaje);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deletePersonaje(@PathVariable(value = "id") Long id){
        if(personajeService.findById(id) != null){
            personajeService.delete(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<?> updatePersonaje(@PathVariable(value = "id")Long id,@Null @RequestBody Personaje personaje){
        if(personajeService.findById(id) != null){
            Personaje personajeDB = (Personaje) personajeService.findById(id);
            if(personaje.getNombre() != null){
                personajeDB.setNombre(personaje.getNombre());
            }
            if(personaje.getHistoria() != null){
                personajeDB.setHistoria(personaje.getHistoria());
            }
            if(personaje.getEdad() != (Integer)null){
                personajeDB.setEdad(personaje.getEdad());
            }
            if(personaje.getPeso() != (Integer)null){
                personajeDB.setPeso(personaje.getPeso());
            }
            personajeService.updatePersonaje(personajeDB);
            return new ResponseEntity<>(personajeDB,HttpStatus.OK);
        }else{
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    private List<MPersonaje> castearPersonajes(List<Personaje> lPersonajes){
        List<MPersonaje> lMPersonajes = new ArrayList<>();
        for (Personaje personaje : lPersonajes) {
            MPersonaje aux = new MPersonaje(personaje);
            lMPersonajes.add(aux);
        }
        return lMPersonajes;
    }

}