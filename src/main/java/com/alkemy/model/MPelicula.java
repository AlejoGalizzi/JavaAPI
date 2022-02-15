package com.alkemy.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.alkemy.entity.Pelicula;

public class MPelicula{
    private String titulo;
    private String imagen;
    private String fecha_creacion;


    public MPelicula(String titulo, String imagen, Date fecha_creacion) {
        this.titulo = titulo;
        this.imagen = imagen;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.fecha_creacion = formatter.format(fecha_creacion);  
         
        
    }


    public MPelicula(Pelicula pelicula) {
        this.titulo = pelicula.getTitulo();
        this.imagen = pelicula.getImagen();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.fecha_creacion = formatter.format(pelicula.getFecha_creacion()); 
    }


    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagen() {
        return this.imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getFecha_creacion() {
        return this.fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.fecha_creacion = formatter.format(fecha_creacion);
    }

}
