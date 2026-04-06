package com.example.Algoritmo;

import java.util.ArrayList;
import java.util.List;

import com.example.Algoritmo.Tablero.Casilla;
import com.example.Algoritmo.Tablero.Tablero;
import com.example.Algoritmo.Tablero.TableroCoordenadaFueraDeRango;

/**
 * Algoritmo que busca un solo camino que le permite a una ficha de caballo
 * recorrer todo un tablero de ajedrez pasando una sola vez por cada casilla.
 */
public class AlgoritmoUna extends Algoritmo {
    public AlgoritmoUna(Tablero tablero) {
        super(tablero);
    }

    @Override
    protected List<Camino> crearLista() {
        return new ArrayList<Camino>(1);
    }

    @Override
    protected boolean solucionCompleta(List<Camino> lista, boolean quedan_casillas_iniciales) {
        /**
         * El algoritmo de una soluciones completa su solución en el momento que se
         * encuentra un camino.
         */

        return lista.size() == 1;
    }

    @Override
    protected boolean buscar(List<Camino> lista, Tablero tablero, Camino camino_temporal, boolean debe_ser_cerrado)
            throws InterruptedException {
        boolean exito = false;

        /*
         * Conjunto de candidatos: El conjunto de casillas obtenidas a partir de los 8
         * desplazamientos predefinidos
         */

        // Por cada candidato posible
        for (int candidato = 0; candidato < AlgoritmoUna.candidatos.length && !exito; candidato++) {
            // Detener el algoritmo si se ha pedido la detención del hilo de ejecución
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }

            // Calcular la siguiente posible casilla a partir de uno de los desplazamientos
            // predefinidos
            try {
                // Probar a obtener la siguiente casilla en base a la coordenada de la última
                // casilla hasta ahora y el siguiente desplazamiento posible
                // Si la casilla no existe, se sale del bloque "try"
                Casilla siguiente_casilla = tablero.getCasilla(
                        Vec2.add(camino_temporal.getLast().coordenada, candidatos[candidato]));

                /*
                 * Función de factibilidad: Comprobar que la casilla exista (excepción
                 * TableroCoordenadaFueraDeRango) y que no haya sido recorrida previamente
                 */

                // Si la casilla no ha sido marcada como recorrida
                if (!siguiente_casilla.recorrida) {
                    /**
                     * Anotar la solución: Marcar la casilla como recorrida y añadirla al camino
                     * recorrido
                     * 
                     * Nota: Podría resultar redundante el tener que marcar la casilla como
                     * recorrida utilizando un atributo cuando su presencia puede ser comprobada
                     * buscando directamente en el camino, pero de cara a la ejecución del algoritmo
                     * es mucho más eficiente poder comprobar si una casilla ha sido recorrida
                     * accediendo a un atributo (eficiencia O(1)) que buscando la casilla en el
                     * array (eficiencia O(n)).
                     */

                    // Marcar la casilla como recorrida
                    siguiente_casilla.recorrida = true;
                    // Añadir la casilla al camino recorrido
                    camino_temporal.add(siguiente_casilla);

                    /**
                     * Función de solución: Comprobar que se han recorrido todas las casillas del
                     * tablero y, en el caso de que sea necesario, que se pueda ir desde la última
                     * casilla del camino a la primera en un solo movimiento
                     */

                    // Si se ha llegado a la ultima casilla
                    if ((camino_temporal.size() == tablero.casillas_totales)
                            && (debe_ser_cerrado ? caminoCerrado(camino_temporal, tablero) : true)) {
                        // Declaramos solución
                        exito = true;

                        // Añadir la solución a la lista
                        lista.add((Camino) camino_temporal.clone());
                    } else {
                        /*
                         * Cuando no se ha llegado a la última casilla, aún no se ha alcanzado una
                         * solución por lo que seguimos buscando.
                         */

                        // Probamos a añadir otra casilla al camino
                        exito = buscar(lista, tablero, camino_temporal, debe_ser_cerrado);
                        if (!exito) {
                            // Desmarcar la casilla como recorrida
                            siguiente_casilla.recorrida = false;
                            // Eliminar la casilla del camino recorrido
                            camino_temporal.removeLast();
                        }
                    }
                }
            } catch (TableroCoordenadaFueraDeRango e) {
                // No hacer nada
            }
        }

        // Cuando se utiliza este algoritmo, la lista no puede tener más de un camino
        if (lista.size() > 1) {
            throw new RuntimeException(
                    "ERROR: La lista de caminos generada tiene más de un elemento a pesar de haber sido generada utilizando el algoritmo de una sola solución.");
        }

        return exito;
    }
}
