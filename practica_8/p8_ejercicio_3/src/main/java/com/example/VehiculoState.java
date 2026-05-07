package com.example;

public abstract class VehiculoState {
    protected Vehiculo vehiculo;

    public void contacto(){};
    public void acelerar(){
        System.out.println("Acelerando coche.");
        this.vehiculo.setCombustible(this.vehiculo.getCombustible() - 20);
        this.vehiculo.setVelocidad(this.vehiculo.getVelocidad() + 20);
    };
    public void frenar(){
        System.out.println("Frenando coche.");
        this.vehiculo.setVelocidad(this.vehiculo.getVelocidad() - 20);
    };
    public void repostar(){
        System.out.println("Repostando.");
        this.vehiculo.setCombustible(this.vehiculo.getCombustible() + 120);
    };
}
