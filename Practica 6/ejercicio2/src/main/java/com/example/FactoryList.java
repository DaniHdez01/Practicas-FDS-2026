package com.example;

public class FactoryList extends FactorySort{

    @Override
    protected Ordenacion crearInstancia(String type) {
        if (type == null) {
            return null;
        }
        
        switch (type.toUpperCase()) {
            case "BURBUJA":
                return new BurbujaList();
            case "QUICKSORT":
                return new SelectionList();
            default:
                System.out.println("Error: Tipo de ordenación '" + type + "' no soportado para ArrayList.");
                return null;
        }
    }
}


