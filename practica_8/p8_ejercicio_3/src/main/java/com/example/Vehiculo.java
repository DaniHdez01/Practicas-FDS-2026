package com.example;

public class Vehiculo {
    private VehiculoState state;
    public VehiculoState getState() {
        return state;
    }
    public void setState(VehiculoState state) {
        this.state = state;
    }

    private int velocidad = 0;
    public int getVelocidad() {
        return velocidad;
    }
    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    private int combustible = 80;
    public int getCombustible() {
        return combustible;
    }
    public void setCombustible(int combustible) {
        this.combustible = combustible;
    }

    private VehiculoState state_apagado = new ApagadoState(this);
    public VehiculoState getState_apagado() {
        return state_apagado;
    }

    private VehiculoState state_parado = new ParadoState(this);
    public VehiculoState getState_parado() {
        return state_parado;
    }

    private VehiculoState state_enmarcha = new EnMarchaState(this);
    public VehiculoState getState_enmarcha() {
        return state_enmarcha;
    }

    private VehiculoState state_sincombustible = new SinCombustibleState(this);
    public VehiculoState getState_sincombustible() {
        return state_sincombustible;
    }

    public Vehiculo(){
        this.state = this.state_apagado;
    }

    public void contacto(){
        this.state.contacto();
    };
    public void acelerar(){
        this.state.acelerar();
    };
    public void frenar(){
        this.state.frenar();
    };
    public void repostar(){
        this.state.repostar();
    };
}
