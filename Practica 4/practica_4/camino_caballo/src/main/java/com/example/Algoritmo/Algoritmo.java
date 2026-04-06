package com.example.Algoritmo;

import java.util.Iterator;
import java.util.List;

import com.example.Algoritmo.Tablero.Casilla;
import com.example.Algoritmo.Tablero.Tablero;
import com.example.Algoritmo.Tablero.TableroCoordenadaFueraDeRango;

/**
 * Algoritmo para buscar camino(s) que le permite(n) a una ficha de caballo
 * recorrer todo un tablero de ajedrez pasando una sola vez por cada casilla.
 */
public abstract class Algoritmo {
    /**
     * Conjunto de desplazamientos a aplicar a la casilla en la que se sitúa el
     * caballo para obtener las ocho siguientes casillas a las que es posible mover
     * el caballo. Constituye el conjunto de candidatos a evaluar en cada paso de la
     * técnica de backtracking.
     */
    protected static final Vec2[] candidatos = {
            new Vec2(-1, 2),
            new Vec2(-2, 1),
            new Vec2(-2, -1),
            new Vec2(-1, -2),
            new Vec2(1, 2),
            new Vec2(2, 1),
            new Vec2(2, -1),
            new Vec2(1, -2)
    };

    /**
     * Tablero a utilizar con el algoritmo
     */
    protected final Tablero tablero;

    /**
     * Configurar una nueva instancia del algoritmo en base a un tablero
     * 
     * @param tablero
     */
    protected Algoritmo(Tablero tablero) {
        this.tablero = tablero;
    }

    /**
     * Comprobar si un camino es cerrado
     * 
     * Un camino cerrado es aquel que permite ir desde la casilla final del camino a
     * la casilla inicial en un solo movimiento.
     * 
     * @param camino  Camino a evaluar
     * @param tablero Tablero asociado al camino
     * @return true si el camino es cerrado
     */
    protected static boolean caminoCerrado(Camino camino, Tablero tablero) {
        boolean resultado = false;

        // Por cada candidato posible y mientras no se haya encontrado la casilla
        // inicial de entre las siguientes casillas posibles
        for (int candidato = 0; candidato < candidatos.length && !resultado; candidato++) {
            try {
                // Obtener siguiente casilla posible
                Casilla siguiente_casilla = tablero.getCasilla(
                        Vec2.add(camino.getLast().coordenada, candidatos[candidato]));

                // Si la siguiente casilla es la casilla inicial
                if (siguiente_casilla == camino.getFirst()) {
                    // Concluir que el camino sí es cerrado
                    resultado = true;
                }
            } catch (TableroCoordenadaFueraDeRango e) {
                // No hacer nada
            }
        }

        return resultado;
    }

    /**
     * Comprobar si existe al menos un camino que le permita a un caballo recorrer
     * todas las casillas una sola vez
     * 
     * Un camino cerrado es aquel que permite ir desde la casilla final del camino a
     * la casilla inicial en un solo movimiento.
     * 
     * Nota: Debido al comportamiento del algoritmo que busca todas las soluciones,
     * ejecutar esta función en base a este algoritmo resulta contraproducente. Por
     * ello, se recomienda su uso sólo con el algoritmo de una única solución.
     * 
     * @param tablero          Tablero a recorrer
     * @param debe_ser_cerrado Indicar si el camino a buscar debe ser cerrado
     * @return true si existe al menos un camino
     */
    public boolean hayCamino(boolean debe_ser_cerrado) {
        return calcularSolucion(debe_ser_cerrado).size() > 0;
    }

    /**
     * Obtener la solución del algoritmo
     * 
     * Un camino cerrado es aquel que permite ir desde la casilla final del camino a
     * la casilla inicial en un solo movimiento.
     * 
     * @param tablero          Tablero a recorrer
     * @param debe_ser_cerrado Indicar si el camino a buscar debe ser cerrado
     * @return Lista de caminos encontrados
     */
    public List<Camino> calcularSolucion(boolean debe_ser_cerrado) {
        // Crear la lista de caminos
        List<Camino> lista = this.crearLista();

        // Crear un camino temporal tan grande como casillas haya en el tablero para que
        // el algoritmo trabaje con él
        Camino camino_temporal = new Camino(this.tablero.casillas_totales, this.tablero);

        // Obtener un iterador para las casillas iniciales
        Iterator<Casilla> casillas_iniciales = this.tablero.getIterator();

        // Comprobar si es posible que existan caminos del tipo especificado
        boolean es_posible = false;
        if (debe_ser_cerrado) {
            // Si el tablero cumple las restricciones necesarias
            if (this.tablero.cumpleRestriccionesCerrado()) {
                // Es posible que existan caminos cerrados
                es_posible = true;
            }
        } else {
            // Siempre es posible que existan caminos abiertos
            es_posible = true;
        }

        // Buscar los caminos
        try {
            if (es_posible) {
                // Mientras aún queden casillas iniciales por comprobar y la solución no esté
                // completa
                while (casillas_iniciales.hasNext() && !this.solucionCompleta(lista, casillas_iniciales.hasNext())) {
                    // Insertar la casilla inicial en el camino
                    Casilla casilla_inicial = casillas_iniciales.next();
                    casilla_inicial.recorrida = true;
                    camino_temporal.add(casilla_inicial);

                    // Buscar caminos que empiecen por la casilla obtenida
                    this.buscar(lista, this.tablero, camino_temporal, debe_ser_cerrado);

                    // Retirar la casilla inicial del camino
                    casilla_inicial.recorrida = false;
                    camino_temporal.removeFirst();
                }
            }
        } catch (InterruptedException e) {
            // Cazar la excepción de interrupción para devolver la lista que se ha obtenido
            // hasta ahora
        }

        // Limpiar el estado del tablero
        this.tablero.reiniciar();

        // Devolver la referencia a la lista de soluciones encontradas
        return lista;
    };

    /**
     * Obtener el objeto List a utilizar para almacenar las soluciones
     * 
     * Este método permite que cada concreción de la clase utilice la implementación
     * de List que más convenga según el caso.
     * 
     * @return Lista a utilizar para almacenar las soluciones
     */
    protected abstract List<Camino> crearLista();

    /**
     * Determinar si la lista de caminos construida hasta el momento cuenta como una
     * solución completa para la variante del algoritmo utilizada
     * 
     * @param quedan_casillas_iniciales Si quedan casillas iniciales por comprobar
     * @return true si la lista de caminos cuenta como solución completa
     */
    protected abstract boolean solucionCompleta(List<Camino> lista, boolean quedan_casillas_iniciales);

    /**
     * Buscar el primer camino de la ficha de caballo en el que recorre todas las
     * casillas del tablero de ajedrez una sola vez
     * 
     * @param[out] lista Lista de caminos encontrados
     * @param[in] tablero Tablero a recorrer
     * @param[in] casillas_iniciales Iterator para obtener las posibles casillas
     *            iniciales de los caminos a evaluar
     * @param[in] camino_temporal Camino temporal
     * @param[in] debe_ser_cerrado Indicar si el camino a buscar debe ser cerrado
     * @return true si se ha encontrado al menos un camino que cumpla los requisitos
     */
    protected abstract boolean buscar(List<Camino> lista, Tablero tablero, Camino camino_temporal,
            boolean debe_ser_cerrado) throws InterruptedException;
}
