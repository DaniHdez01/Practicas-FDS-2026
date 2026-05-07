package com.example;

public class EnMarchaState extends VehiculoState {
    EnMarchaState(Vehiculo vehiculo){
        this.vehiculo = vehiculo;
    }

    @Override
    public void acelerar() {
        if(this.vehiculo.getCombustible() > 0){
            super.acelerar();
            this.vehiculo.setState(this.vehiculo.getState_enmarcha());
        } else {
            System.out.println("Coche se queda sin gasolina.");
            this.vehiculo.setState(this.vehiculo.getState_sincombustible());
        }
    }

    @Override
    public void frenar() {
        if(this.vehiculo.getVelocidad() > 0){
            super.frenar();
            if(this.vehiculo.getVelocidad() == 0){
                System.out.println("Coche se para.");
                this.vehiculo.setState(this.vehiculo.getState_parado());
            } else {
                this.vehiculo.setState(this.vehiculo.getState_enmarcha());
            }
        }
    }

    @Override
    public void repostar(){
        System.out.println("Coche en marcha. Imposible repostar.");
    }

    @Override
    public void contacto(){
        System.out.println("Coche ya en marcha, imposible dar contacto otra vez.");
    }
}
