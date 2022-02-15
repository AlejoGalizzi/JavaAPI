package com.alkemy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Null;

import com.alkemy.entity.Genero;
import com.alkemy.entity.Pelicula;
import com.alkemy.model.MGenero;
import com.alkemy.model.MPelicula;
import com.alkemy.order.sortPeliculaAsc;
import com.alkemy.order.sortPeliculaDesc;
import com.alkemy.service.IGeneroService;
import com.alkemy.service.IPeliculaService;

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
@RequestMapping("/api/movies")
public class PeliculaController{

    @Autowired
    private IPeliculaService peliculaService;

    @Autowired
    private IGeneroService generoService;

    @GetMapping
    public ResponseEntity<?> getPeliculas(@RequestParam(value = "order",required = false)String order){
        if(order == null){
            if(peliculaService.findAll() != null){
                List<MPelicula> lPeliculas = castearPersonajes(peliculaService.findAll());
                return new ResponseEntity<>(lPeliculas,HttpStatus.OK);
            }
        }else{
            if(order == "ASC"){
                List<MPelicula> lPeliculas = castearPersonajes(peliculaService.findAll());
                lPeliculas.sort(new sortPeliculaAsc());
                return new ResponseEntity<>(lPeliculas,HttpStatus.OK);
            }else{
                List<MPelicula> lPeliculas = castearPersonajes(peliculaService.findAll());
                lPeliculas.sort(new sortPeliculaDesc());
                return new ResponseEntity<>(lPeliculas,HttpStatus.OK);
            }
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
                                           
    }
    
    @GetMapping(params = {"name"})
    public ResponseEntity<?> busquedaPelicula(@RequestParam(value = "name")String nombre){
        
        if(peliculaService.lFindByTitulo(nombre) == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }else{
            List<Pelicula> peliculasRes = peliculaService.lFindByTitulo(nombre);
            return new ResponseEntity<>(peliculasRes,HttpStatus.OK);
        }
    }

    @GetMapping(params = {"genre"})
    public ResponseEntity<?> filtrarGenero(@RequestParam(value = "genre")Long id,@RequestParam(value = "order",required = false)String order){
        if(peliculaService.findPeliculasGenero(id) == null || generoService.findById(id) == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }else{
            List<MPelicula> lPeliculas = castearPersonajes(peliculaService.findPeliculasGenero(id)); 
            if(order != null){
                if(order == "ASC"){
                    lPeliculas.sort(new sortPeliculaAsc());
                }else{
                    lPeliculas.sort(new sortPeliculaDesc());
                }
            }
            return new ResponseEntity<>(lPeliculas,HttpStatus.OK);
        }                            
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPelicula(@PathVariable(value="id") Long id){
        if(peliculaService.findById(id) != null){
            return new ResponseEntity<>((Pelicula)peliculaService.findById(id),HttpStatus.OK);
        }else{
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/genres")
    public ResponseEntity<?> getGeneros(){
        if(generoService.findAll() != null){
            List<MGenero> lGeneros = castearGeneros(generoService.findAll());
            return new ResponseEntity<>(lGeneros,HttpStatus.OK);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<?> crearPelicula(@Valid @RequestBody Pelicula pelicula){
        if(peliculaService.findByTituloAndFecha_creacion(pelicula.getTitulo(),pelicula.getFecha_creacion()) == null){
            peliculaService.savePelicula(pelicula);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
    }
    @PostMapping("/genres/create")
    public ResponseEntity<Void> crearGenero(@Valid @RequestBody Genero genero){
        if(generoService.findByNombre(genero.getNombre()) == null){
            generoService.saveGenero(genero);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<?> deletePelicula(@PathVariable(value = "id") Long id){
        if(peliculaService.findById(id) == null){
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }else{
            peliculaService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Void> updatePelicula(@PathVariable(value = "id")Long id,@Null @RequestBody Pelicula pelicula){
        if(peliculaService.findById(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }else{
            Pelicula peliculaBD = (Pelicula)peliculaService.findById(id);
            if(pelicula.getFecha_creacion()!=null){
                peliculaBD.setFecha_creacion(pelicula.getFecha_creacion());
            }
            if(pelicula.getTitulo()!=null){
                peliculaBD.setTitulo(pelicula.getTitulo());
            }
            if(pelicula.getImagen() != null){
                peliculaBD.setImagen(pelicula.getImagen());
            }
            if(pelicula.getCalificacion() != 0){
                peliculaBD.setCalificacion(pelicula.getCalificacion());
            }
            if(pelicula.getGeneroId() != 0){
                peliculaBD.setGeneroId(pelicula.getGeneroId());
            }
            peliculaService.updatePelicula(peliculaBD);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }


    private List<MPelicula> castearPersonajes(List<Pelicula> lPeliculas) {
        List<MPelicula> lMPeliculas = new ArrayList<>();
        for (Pelicula pelicula : lPeliculas) {
            MPelicula aux = new MPelicula(pelicula);
            lMPeliculas.add(aux);
        }
        return lMPeliculas;
    }

    private List<MGenero> castearGeneros(List<Genero> lGeneros) {
        List<MGenero> lMGeneros = new ArrayList<>();
        for (Genero genero : lGeneros) {
            MGenero aux = new MGenero(genero);
            lMGeneros.add(aux);
        }
        return lMGeneros;
    }
}