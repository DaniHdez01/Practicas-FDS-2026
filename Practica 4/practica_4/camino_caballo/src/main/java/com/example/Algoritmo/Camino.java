package com.example.Algoritmo;

import java.util.ArrayList;

import com.example.Algoritmo.Tablero.Casilla;
import com.example.Algoritmo.Tablero.Tablero;

/**
 * Lista ordenada de casillas
 */
public class Camino extends ArrayList<Casilla> {

    /**
     * Tablero asociado al camino
     */
    private final Tablero tablero;

    /**
     * Crear un camino de casillas
     * 
     * @param longitud_inicial Longitud inicial del camino
     * @param tablero          Tablero asociado al camino
     */
    public Camino(int longitud_inicial, Tablero tablero) {
        super(longitud_inicial);
        this.tablero = tablero;
    }

    /**
     * Obtener una representación visual del camino sobre el tablero
     */
    @Override
    public String toString() {
        // Matriz de correspondencia entre casillas del tablero y el índice de visita
        int[][] indices = new int[this.tablero.alto][this.tablero.ancho];

        // Por cada casilla en el camino
        for (int i = 0; i < this.size(); i++) {
            // Casilla del camino
            Casilla casilla = this.get(i);

            // Asociar el índice de visita a la coordenada correspondiente en el tablero
            indices[casilla.coordenada.y][casilla.coordenada.x] = i;
        }

        // Constructor de strings para el texto
        StringBuilder sb = new StringBuilder();

        /*
         * Las filas a imprimir para representar el camino en el tablero consisten en:
         * - 2 caracteres de barra para estilizar los bordes del tablero
         * 
         * - Para cada casilla a imprimir:
         * + 1 espacio de separación para estilizar
         * + 10 caracteres numéricos justificados a la derecha para poder representar el
         * numero máximo soportado por el tipo int usado para el índice de casilla en el
         * camino
         * + 1 espacio de separación para estilizar
         * + 1 barra para separar una casilla de la siguiente
         * 
         * - Un carácter de barra final para dejarlo igual que al principio de la línea
         */

        // Calcular longitud de las líneas a imprimir
        final int longitud_linea = 2 + ((1 + 10 + 1 + 1) * this.tablero.ancho) + 1;

        // Generar delimitador de líneas en función del número de caracteres que van a
        // tener las filas
        StringBuilder delim = new StringBuilder();
        for (int i = 0; i < longitud_linea; i++) {
            delim.append("=");
        }
        delim.append(System.lineSeparator());

        // Cabecera
        sb.append(delim);

        // Por cada fila del tablero
        for (int fila = 0; fila < this.tablero.alto; fila++) {
            sb.append("||");

            // Por cada casilla en la fila
            for (int columna = 0; columna < this.tablero.ancho; columna++) {
                // Espacio para estilizar
                sb.append(" ");

                // Imprimir el índice como un número entero justificado 10 caracteres a la
                // izquierda
                sb.append(String.format("%-10d", indices[fila][columna]));

                // Espacio para estilizar
                sb.append(" ");

                // Final de la casilla
                sb.append("|");
            }

            // Final de la fila
            sb.append("|");
            sb.append(System.lineSeparator());

            // Separar fila de la siguiente
            sb.append(delim);
        }

        return sb.toString();
    }
}
