package com.example;

public class SinCombustibleState extends VehiculoState{
    public SinCombustibleState(Vehiculo vehiculo){
        this.vehiculo = vehiculo;
    }

    @Override
    public void repostar() {
        if(this.vehiculo.getCombustible() <= 0) {
            super.repostar();
            this.vehiculo.setState(this.vehiculo.getState_apagado());
        } else {
            System.out.println("El depósito debe estar vacío para repostar.");
        }
    }

    @Override
    public void acelerar(){}

    @Override
    public void frenar(){
        System.out.println("No se puede frenar, no hay combustible.");
    }

    @Override
    public void contacto(){}
}
