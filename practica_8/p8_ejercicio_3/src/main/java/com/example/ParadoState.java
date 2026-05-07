package com.example;

public class ParadoState extends VehiculoState {
    ParadoState(Vehiculo vehiculo){
        this.vehiculo = vehiculo;
    }

    @Override
    public void acelerar() {
        if(this.vehiculo.getCombustible() > 0){
            super.acelerar();
            this.vehiculo.setState(this.vehiculo.getState_enmarcha());
        } else {
            System.out.println("El coche se ha quedado sin gasolina.");
            this.vehiculo.setState(this.vehiculo.getState_sincombustible());
        }
    }

    @Override
    public void frenar() {
        System.out.println("No procede frenar, el coche ya está parado.");
        this.vehiculo.setState(this.vehiculo.getState_parado());
    }

    @Override
    public void repostar(){}

    @Override
    public void contacto(){
        System.out.println("Quitando contacto.");
        this.vehiculo.setState(this.vehiculo.getState_apagado());
    }
}
