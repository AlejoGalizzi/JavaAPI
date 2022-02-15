package com.alkemy.model;

import com.alkemy.entity.Genero;

public class MGenero {
    private long id;
    private String nombre;


    public MGenero(Genero genero) {
        this.id = genero.getId();
        this.nombre = genero.getNombre();
    }


    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }


}
