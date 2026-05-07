package com.example.usuario;

public abstract class Usuario {
    protected String nombre;

    /**
     * Obtener nombre del usuario
     */
    public String toString(){
        return this.nombre;
    }
}
