package com.alkemy.model;

import com.alkemy.entity.Pelicula;
import com.alkemy.entity.Personaje;

public class MPersonajePelicula{
    private Personaje personaje;
    private Pelicula pelicula;


    public Personaje getPersonaje() {
        return this.personaje;
    }

    public void setPersonaje(Personaje personaje) {
        this.personaje = personaje;
    }

    public Pelicula getPelicula() {
        return this.pelicula;
    }

    public void setPelicula(Pelicula pelicula) {
        this.pelicula = pelicula;
    }

}