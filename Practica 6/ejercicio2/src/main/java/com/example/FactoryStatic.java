package com.example;

public class FactoryStatic extends FactorySort{
    @Override 
    protected Ordenacion crearInstancia(String type){
        if (type == null){
            return null; 
        }
         switch (type.toUpperCase()) {
            case "BURBUJA":
                return new BurbujaStatic();
            case "QUICKSORT":
                return new SelectionStatic();
            default:
                System.out.println("Error: Tipo de ordenación '" + type + "' no soportado para ArrayList.");
                return null;
        }
    }; 
}