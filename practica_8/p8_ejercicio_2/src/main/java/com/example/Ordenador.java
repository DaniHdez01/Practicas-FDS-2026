package com.example;

public class Ordenador {
    private OrdenarBehavior ordenarBehavior;

    public Ordenador(OrdenarBehavior behavior){
        this.ordenarBehavior = behavior;
    }

    public void cambiarBehavior(OrdenarBehavior behavior){
        this.ordenarBehavior = behavior;
    }

    public void ordenar(int[] array){
        this.ordenarBehavior.ordenar(array);
    }
}
