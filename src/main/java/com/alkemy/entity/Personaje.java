package com.alkemy.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="personaje")
public class Personaje implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String imagen;

    @NotEmpty(message = "El nombre del personaje no puede estar en blanco")
    @Column(unique = true,length = 255)
    private String nombre;

    @NotNull(message = "La edad no puede ser nulo")
    @Min(value = 1,message = "La edad minima del personaje es 1")
    private int edad;

    @NotNull(message = "El peso no puede ser nulo")
    @Min(value = 1,message = "El peso tiene que ser al menos 1")
    private int peso;

    @Column(length = 4000)
    private String historia;

    @ManyToMany
    @JoinTable(name = "personaje_pelicula",
                joinColumns = @JoinColumn(name = "pelicula_id",referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name= "personaje_id",referencedColumnName = "id"))
    private Set<Pelicula> setPeliculas = new HashSet<>();

    public Set<Pelicula> getSetPeliculas() {
        return this.setPeliculas;
    }

    public void setSetPeliculas(Set<Pelicula> setPeliculas) {
        this.setPeliculas = setPeliculas;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImagen() {
        return this.imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return this.edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getPeso() {
        return this.peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public String getHistoria() {
        return this.historia;
    }

    public void setHistoria(String historia) {
        this.historia = historia;
    }


    private static final long serialVersionUID = 1L;
}