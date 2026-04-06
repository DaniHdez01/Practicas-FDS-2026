package com.example.Pruebas;

import java.util.List;

import com.example.Algoritmo.Algoritmo;
import com.example.Algoritmo.Camino;

/**
 * Ejecutor del algoritmo de una sola solución para poder ejecutarlo en un hilo
 * aparte
 */
public class Ejecutor implements Runnable {
    private final boolean debe_ser_cerrado;
    private final Algoritmo algoritmo;

    private List<Camino> camino = null;

    /**
     * Configurar el ejecutor
     * 
     * @param tablero Tablero a utilizar
     * @param camino  Tipo de camino a buscar
     */
    public Ejecutor(Algoritmo algoritmo, boolean debe_ser_cerrado) {
        this.algoritmo = algoritmo;
        this.debe_ser_cerrado = debe_ser_cerrado;
    }

    @Override
    public void run() {
        this.camino = algoritmo.calcularSolucion(debe_ser_cerrado);
    }

    /**
     * Obtener la solución resultante de haber ejecutado el algoritmo
     */
    public List<Camino> getSolucion() {
        return this.camino;
    }
}
