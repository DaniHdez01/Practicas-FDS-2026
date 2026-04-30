package com.example;

import java.util.Arrays;
import java.util.List;

public class Cartelera {
    private List<String> peliculas = Arrays.asList("Matrix", "Inception", "Wicked", "Interstellar");

    public boolean estaEnCartelera(String peli) {
        return peliculas.contains(peli);
    }

    public String verPeliculas() {
        String info = "";
        for (int i = 0; i < peliculas.size(); i++) {
            info += peliculas.get(i).toString();
            info += "\n";
        }
        return info;
    }
}
