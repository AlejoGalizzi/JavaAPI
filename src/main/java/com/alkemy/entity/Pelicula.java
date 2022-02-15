package com.alkemy.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;



@Table(name= "pelicula")
@Entity 
public class Pelicula implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String imagen;

    @Column(name = "titulo",nullable = false)

    @NotEmpty(message = "Titulo no puede estar vacio")
    @Size(min = 2, max = 100,message = "El titulo tiene que tener al menos 3 letras")
    private String titulo;
    
    @NotNull(message = "Fecha de creacion no puede ser nulo")
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date fecha_creacion;

    @Min(value = 1,message = "La calificacion debe ser mayor que 1 y menor que 5")
    @Max(value = 5,message = "La calificacion debe ser mayor que 1 y menor que 5")
    @NotNull(message = "Calificacion no puede ser nulo")
    private int calificacion;


    @Min(value = 1,message = "El id del genero existente es mayor o igual que 1 (no puede ser nulo)")
    @NotNull(message = "Genero no puede ser nulo")
    @Column(name="genero_id")
    private long generoId;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonBackReference
    @JoinTable(name = "personaje_pelicula",joinColumns = @JoinColumn(name = "personaje_id",referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "pelicula_id",referencedColumnName = "id"))
    private Set<Personaje> setPersonajes = new HashSet<>();    

    public Set<Personaje> getSetPersonajes() {
        return this.setPersonajes;
    }

    public void setSetPersonajes(Set<Personaje> setPersonajes) {
        this.setPersonajes = setPersonajes;
    }

    public long getGeneroId() {
        return this.generoId;
    }

    public void setGeneroId(long generoId) {
        this.generoId = generoId;
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

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Date getFecha_creacion() {
        return this.fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public int getCalificacion() {
        return this.calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    private static final long serialVersionUID = 1L;
}
