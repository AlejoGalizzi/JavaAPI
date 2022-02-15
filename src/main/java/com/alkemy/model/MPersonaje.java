package com.alkemy.model;

import com.alkemy.entity.Personaje;

public class MPersonaje{
    private String nombre;
    private String imagen;

    public MPersonaje(){}

    public MPersonaje(String nombre, String imagen) {
        this.nombre = nombre;
        this.imagen = imagen;
    }

    public MPersonaje(Personaje personaje){
        this.nombre = personaje.getNombre();
        this.imagen = personaje.getImagen();
    }


    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return this.imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
}