package com.example;

public class Cliente {
    private Disponibilidad disponibilidad;
    private Cartelera cartelera;
    private Pago pago;

    public Cliente() {
        this.disponibilidad = new Disponibilidad();
        this.cartelera = new Cartelera();
        this.pago = new Pago();
    }

    public void consultarPeliculas() {
        System.out.println(this.cartelera.verPeliculas());
    }

    public boolean consultarDisponibilidad(String pelicula) {
        if (this.cartelera.estaEnCartelera(pelicula) == true) {
            if (this.disponibilidad.estaDisponible(pelicula) == true) {
                System.out.println("Hay asientos disponibles para la pelicula ");
                return true;
            } else {
                System.out.println("No hay asientos disponibles");
                return false;
            }
        } else {
            System.out.println("La pelicula no está en cartelera");
            return false;
        }
    }

    public void pagar(String pelicula) {
        System.out.println(pelicula);
        if (consultarDisponibilidad(pelicula) == true) {
            if (this.pago.pagoAceptado(pelicula) == true) {
                System.out.println("Pago aceptado");
            } else {
                System.out.println("Pago rechazado");
            }
        } else {
            System.out.println("La pelicula no está disponible");
        }
    }

}
