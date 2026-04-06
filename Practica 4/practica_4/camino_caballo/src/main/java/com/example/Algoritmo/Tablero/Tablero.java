package com.example.Algoritmo.Tablero;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.example.Algoritmo.Vec2;

/**
 * Tablero de ajedrez
 */
public class Tablero {
    private class TableroIterator implements Iterator<Casilla> {
        private Casilla[][] casillas;
        private final int alto;
        private final int ancho;
        private int y = 0;
        private int x = 0;

        public TableroIterator(Tablero tablero, Casilla[][] casillas) {
            this.alto = tablero.alto;
            this.ancho = tablero.ancho;
            this.casillas = casillas;
        }

        @Override
        public boolean hasNext() {
            return x < ancho && y < alto;
        }

        @Override
        public Casilla next() throws NoSuchElementException {
            Casilla casilla_a_devolver = null;
            if (!this.hasNext()) {
                throw new NoSuchElementException();
            } else {
                casilla_a_devolver = this.casillas[y][x];

                // Si se ha llegado al último elemento de la fila
                if (x + 1 == ancho) {
                    // Se pasa a la siguiente fila
                    y++;
                    x = 0;
                } else {
                    // Se incrementa la x
                    x++;
                }
            }
            return casilla_a_devolver;
        }

    }

    /**
     * Número de casillas de alto
     */
    public final int alto;

    /**
     * Número de casillas de ancho
     */
    public final int ancho;

    /**
     * Número de casillas totales
     */
    public final int casillas_totales;

    /**
     * Casillas del tablero
     */
    private final Casilla[][] casillas;

    public Tablero(int alto, int ancho) {
        this.alto = alto;
        this.ancho = ancho;
        this.casillas_totales = this.alto * this.ancho;

        // Inicializar el tablero
        this.casillas = new Casilla[this.alto][this.ancho];
        for (int y = 0; y < this.alto; y++) {
            for (int x = 0; x < this.ancho; x++) {
                this.casillas[y][x] = new Casilla(x, y);
            }
        }
    }

    public Casilla getCasilla(int x, int y) throws TableroCoordenadaFueraDeRango {
        if (x >= 0 && y >= 0 && x < this.ancho && y < this.alto) {
            return this.casillas[y][x];
        } else {
            throw new TableroCoordenadaFueraDeRango();
        }
    }

    /**
     * 
     * @return Iterator para las casillas del tablero
     */
    public Iterator<Casilla> getIterator() {
        return new TableroIterator(this, this.casillas);
    }

    public Casilla getCasilla(Vec2 coordenada) throws TableroCoordenadaFueraDeRango {
        return this.getCasilla(coordenada.x, coordenada.y);
    }

    /**
     * Configurar el tablero para devolverlo a su estado inicial
     */
    public void reiniciar() {
        // Reiniciar las casillas
        for (Casilla[] fila : casillas) {
            for (Casilla casilla : fila) {
                casilla.reiniciar();
            }
        }
    }

    /**
     * Comprobar si el tablero cumple las condiciones necesarias para que puedan
     * existir caminos cerrados
     * 
     * @return true si es posible encontrar caminos cerrados en el tablero
     */
    public boolean cumpleRestriccionesCerrado() {
        boolean resultado = false;

        /*
         * Para que haya al menos un camino cerrado, se deben cumplir ciertas
         * restricciones:
         * - El número de filas debe ser menor o igual que el número de columnas
         * (filas <= columnas)
         * - De entre el número de filas y columnas, al menos uno de ellos debe ser par
         * (filas % 2 == 0 || columnas % 2 == 0)
         * - El número de filas no puede ser igual a 1, 2 o 4
         * (filas != 1 && filas != 2 && filas != 4)
         * - Cuando el número de filas es igual a 3, el número de columnas debe ser
         * mayor que 8 (filas == 3 ? columnas > 8 : true)
         */
        if ((this.alto <= this.ancho) &&
                (this.alto % 2 == 0 || this.ancho % 2 == 0) &&
                (this.alto != 1 && this.alto != 2 && this.alto != 4) &&
                (this.alto == 3 ? this.ancho > 8 : true)) {
            resultado = true;
        }

        return resultado;
    }
}
