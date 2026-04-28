package com.example;

public abstract class FactorySort {
    protected abstract Ordenacion crearInstancia(String type); 

    public Ordenacion ordenar(String type){
        Ordenacion nuevaInstancia = crearInstancia(type);

        if (nuevaInstancia != null){
            nuevaInstancia.ordenar();
        }
        return nuevaInstancia; 
    }

        public Ordenacion imprimir(String type){
        Ordenacion nuevaInstancia = crearInstancia(type);

        if (nuevaInstancia != null){
            nuevaInstancia.imprimir();
        }
        return nuevaInstancia; 
    }
}
