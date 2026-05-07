package com.example;

public class ApagadoState extends VehiculoState {
    ApagadoState(Vehiculo vehiculo){
        this.vehiculo = vehiculo;
    }

    @Override
    public void contacto() {
        if (this.vehiculo.getCombustible() > 0) {
            System.out.println("Coche arrancado.");
            this.vehiculo.setState(this.vehiculo.getState_parado());
        } else {
            this.vehiculo.setState(this.vehiculo.getState_sincombustible());
        }
    }

    @Override
    public void acelerar(){
        System.out.println("Coche apagado. No se puede acelerar.");
    }

    @Override
    public void frenar(){}

    @Override
    public void repostar(){}
}
