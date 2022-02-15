package com.alkemy.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "genero")
public class Genero implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "Nombre del genero no puede estar en blanco")
    @Column(unique = true)
    private String nombre;

    @Column(length = 2000)
    private String imagen;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "genero_id",referencedColumnName = "id")
    private List<Pelicula> cursos = new ArrayList<>();

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

    public String getImagen() {
        return this.imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public List<Pelicula> getCursos() {
        return this.cursos;
    }

    public void setCursos(List<Pelicula> cursos) {
        this.cursos = cursos;
    }



    private static final long serialVersionUID = 1L;
}