package com.example;

public class FactoryStatic extends FactorySort{

    //Clase Factory que se va a encargar de instanciar las clases que ordenen arrays estáticos 
    @Override 
    protected Ordenacion crearInstancia(String type){
        if (type == null){
            return null; 
        } 
         switch (type.toUpperCase()) { 
            case "BURBUJA": //Si la instancia creada va a ordenar por el método burbuja  
                return new BurbujaStatic();
            case "SELECCION":
                return new SelectionStatic();//Si la instancia creada va a ordenar por el método de selección
            default:
                System.out.println("Error: Tipo de ordenación '" + type + "' no soportado para ArrayList.");
                return null;
        }
    }; 
}