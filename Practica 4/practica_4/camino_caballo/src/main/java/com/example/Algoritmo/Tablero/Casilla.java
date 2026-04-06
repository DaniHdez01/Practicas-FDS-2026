package com.example.Algoritmo.Tablero;

import com.example.Algoritmo.Vec2;

/**
 * Casilla del tablero
 */
public class Casilla {
    /**
     * Indica si la casilla en cuestión ha sido recorrida o no. Se utiliza en los
     * algoritmos de búsqueda de caminos para asegurar que no se recorre la misma
     * casilla dos veces.
     */
    public boolean recorrida = false;

    /**
     * Coordenada de la casilla dentro del tablero
     */
    public final Vec2 coordenada;

    /**
     * Construir una nueva casilla
     * 
     * @param coordenada Coordenada de la casilla dentro del tablero
     */
    public Casilla(Vec2 coordenada) {
        this.coordenada = coordenada;
    }

    /**
     * Construir una nueva casilla
     * 
     * @param x Coordenada X de la casilla dentro del tablero
     * @param y Coordenada Y de la casilla dentro del tablero
     */
    public Casilla(int x, int y) {
        this(new Vec2(x, y));
    }

    @Override
    public String toString() {
        return this.coordenada.toString();
    }

    /**
     * Configurar la casilla para devolverla a su estado inicial
     */
    public void reiniciar() {
        this.recorrida = false;
    }
}
