package com.example.Algoritmo;

import java.util.List;

import com.example.Algoritmo.Tablero.Casilla;
import com.example.Algoritmo.Tablero.Tablero;
import com.example.Algoritmo.Tablero.TableroCoordenadaFueraDeRango;

import java.util.LinkedList;

/**
 * Algoritmo que busca todos los caminos que le permiten a una ficha de caballo
 * recorrer todo un tablero de ajedrez pasando una sola vez por cada casilla.
 */
public class AlgoritmoTodas extends Algoritmo {
    public AlgoritmoTodas(Tablero tablero) {
        super(tablero);
    }

    protected List<Camino> crearLista() {
        return new LinkedList<Camino>();
    }

    @Override
    protected boolean solucionCompleta(List<Camino> lista, boolean quedan_casillas_iniciales) {
        /**
         * El algoritmo de todas las soluciones completa su solución cuando se han
         * comprobado todos los caminos posibles, lo cual sólo se cumple cuando se han
         * buscado caminos que comiencen con todas y cada una de las casillas del
         * tablero.
         */
        return !quedan_casillas_iniciales;
    }

    @Override
    protected boolean buscar(List<Camino> lista, Tablero tablero, Camino camino_temporal, boolean debe_ser_cerrado)
            throws InterruptedException {
        /*
         * Conjunto de candidatos: El conjunto de casillas obtenidas a partir de los 8
         * desplazamientos predefinidos
         */

        // Por cada candidato posible
        for (int candidato = 0; candidato < candidatos.length; candidato++) {
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
                        // Añadir solución a la lista
                        lista.add((Camino) camino_temporal.clone());
                    } else {
                        /*
                         * Cuando no se ha llegado a la última casilla, aún no se ha alcanzado una
                         * solución por lo que seguimos buscando.
                         */

                        // Probamos a añadir otra casilla al camino
                        buscar(lista, tablero, camino_temporal, debe_ser_cerrado);
                    }

                    // Desmarcar la casilla como recorrida
                    siguiente_casilla.recorrida = false;

                    // Eliminar la casilla del camino recorrido
                    camino_temporal.removeLast();
                }

            } catch (TableroCoordenadaFueraDeRango e) {
                // No hacer nada
            }
        }

        return lista.size() > 0;
    }
}
