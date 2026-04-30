package com.example;

public abstract class FactorySort {
    //Clase principal de Factory donde definimos las funciones que van a tener la fábrica de ordenación estática y dinámica 
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
