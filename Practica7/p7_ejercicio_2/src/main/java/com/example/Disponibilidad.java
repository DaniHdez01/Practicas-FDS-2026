package com.example;

import java.util.List;

public class Disponibilidad {

    public boolean estaDisponible(String pelicula) {
        int rnd = (int) (Math.random() * 10) + 1;
        if (rnd == 1) {
            return false;
        } else {
            return true;
        }
    }

}
