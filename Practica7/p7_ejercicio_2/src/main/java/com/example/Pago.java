package com.example;

public class Pago {

    public boolean pagoAceptado(String pelicula) {
        int rnd = (int) (Math.random() * 10) + 1;
        if (rnd == 1) {
            return false;
        } else {
            return true;
        }
    }
}
