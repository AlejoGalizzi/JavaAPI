package com.alkemy.order;

import java.util.Comparator;

import com.alkemy.model.MPelicula;

public class sortPeliculaAsc implements Comparator<MPelicula>{

    @Override
    public int compare(MPelicula p1, MPelicula p2) {
        return p1.getFecha_creacion().compareTo(p2.getFecha_creacion());
    }

}