package com.example.Algoritmo.Tablero;

/**
 * La coordenada de la casilla pedida está fuera de rango.
 */
public class TableroCoordenadaFueraDeRango extends Exception {
    public TableroCoordenadaFueraDeRango() {
        super("La coordenada de la casilla pedida está fuera de rango.");
    }
}
