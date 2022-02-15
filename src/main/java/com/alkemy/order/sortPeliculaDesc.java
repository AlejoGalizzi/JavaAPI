package com.alkemy.order;

import java.util.Comparator;

import com.alkemy.model.MPelicula;

public class sortPeliculaDesc implements Comparator<MPelicula>{

    @Override
    public int compare(MPelicula p1, MPelicula p2) {
        return p2.getFecha_creacion().compareTo(p1.getFecha_creacion());
    }

}