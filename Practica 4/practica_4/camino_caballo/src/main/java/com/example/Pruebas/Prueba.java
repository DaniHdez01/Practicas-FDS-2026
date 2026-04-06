package com.example.Pruebas;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.csv.CSVPrinter;

import com.example.Algoritmo.Algoritmo;
import com.example.Algoritmo.AlgoritmoTodas;
import com.example.Algoritmo.AlgoritmoUna;
import com.example.Algoritmo.Camino;
import com.example.Algoritmo.Tablero.Tablero;

/**
 * Prueba del algoritmo de una sola solución a ejecutar
 */
public class Prueba {
    /**
     * Identificadores para indicar qué caminos se quiere buscar en la prueba
     */
    public enum CaminoABuscar {
        /**
         * Sólo buscar un camino abierto
         */
        ABIERTO,

        /**
         * Sólo buscar un camino cerrado
         */
        CERRADO,

        /**
         * Buscar tanto un camino abierto como uno cerrado
         */
        ABIERTO_Y_CERRADO
    }

    /**
     * Tiempo límite en milisegundos para cada ejecución del algoritmo
     */
    private final long tiempoLimite = 120000;

    /**
     * Caminos a buscar en la prueba
     */
    private CaminoABuscar caminoABuscar;

    public CaminoABuscar getCaminoABuscar() {
        return caminoABuscar;
    }

    /**
     * Tablero a usar para la prueba
     */
    private Tablero tablero;

    public Tablero getTablero() {
        return this.tablero;
    }

    /**
     * Algoritmo a utilizar para la prueba
     */
    private final boolean todas_las_soluciones;

    /**
     * 
     * @return true si se ha pedido encontrar todas las soluciones
     */
    public boolean todasSolucionesPedidas() {
        return todas_las_soluciones;
    }

    /**
     * 
     * @return Número total de soluciones encontradas en la prueba
     */
    public int getSolucionesEncontradas() {
        int total = 0;
        if (this.caminoABuscar == CaminoABuscar.ABIERTO) {
            total = this.solucionAbierta.size();
        } else if (this.caminoABuscar == CaminoABuscar.CERRADO) {
            total = this.solucionCerrada.size();
        } else if (this.caminoABuscar == CaminoABuscar.ABIERTO_Y_CERRADO) {
            total = this.solucionAbierta.size() + this.solucionCerrada.size();
        }
        return total;
    }

    /**
     * Solución abierta resultante.
     * 
     * Puede ser null en caso de que no exista ninguna para el tablero asociado.
     */
    private List<Camino> solucionAbierta = null;

    /**
     * Tiempo en milisegundos empleado en buscar la solución abierta
     * 
     * Si se ha superado el tiempo límite, su valor es igual a Long.MAX_VALUE
     */
    private long tiempoEjecucionAbierto;

    /**
     * Solución cerrada resultante.
     * 
     * Puede ser null en caso de que no exista ninguno para el tablero asociado.
     */
    private List<Camino> solucionCerrada = null;

    /**
     * Tiempo en milisegundos empleado en buscar la solución cerrada
     * 
     * Si se ha superado el tiempo límite, su valor es igual a Long.MAX_VALUE
     */
    private long tiempoEjecucionCerrado;

    /**
     * Configurar una nueva prueba del algoritmo
     * 
     * @param tablero Tablero a utilizar
     * @param camino  Tipo de camino a buscar en la prueba
     */
    public Prueba(Tablero tablero, CaminoABuscar camino, boolean todas_las_soluciones) {
        this.tablero = tablero;
        this.caminoABuscar = camino;
        this.todas_las_soluciones = todas_las_soluciones;
    }

    /**
     * Configurar una nueva prueba del algoritmo de forma interactiva
     * 
     * @param scanner Escáner de texto conectado a la entrada estándar de texto del
     *                programa
     */
    public Prueba(Scanner scanner) {
        // Pedir por pantalla el algoritmo a utilizar
        System.out.println("¿Cómo deseas que se comporte el algoritmo?");
        System.out.println("1. Buscar sólo una solución");
        System.out.println("2. Buscar todas las soluciones");
        System.out.print("Introduce el número de opción: ");
        int opcion_algoritmo = scanner.nextInt();
        switch (opcion_algoritmo) {
            case 1: {
                this.todas_las_soluciones = false;
                break;
            }
            case 2: {
                this.todas_las_soluciones = true;
                break;
            }
            default: {
                // Configurar el valor del algoritmo a null para mitigar error del compilador de
                // Java
                this.todas_las_soluciones = false;
                System.err.println("ERROR: No se ha especificado una opción válida");
                System.exit(1);
                break;
            }
        }

        // Pedir por pantalla las dimensiones del tablero
        System.out.println();
        System.out.println("Introduce las dimensiones del tablero: ");
        System.out.print("Altura del tablero: ");
        int altura = scanner.nextInt();
        System.out.print("Anchura del tablero: ");
        int anchura = scanner.nextInt();
        // Crear el tablero
        this.tablero = new Tablero(altura, anchura);

        // Pedir el tipo de camino a buscar
        System.out.println();
        System.out.println("Tipos de camino a buscar:");
        System.out.println("1. Abierto");
        System.out.println("2. Cerrado");
        System.out.println("3. Ambos");
        System.out.print("Introduce el número de opción: ");
        int opcion_camino = scanner.nextInt();
        switch (opcion_camino) {
            case 1: {
                this.caminoABuscar = CaminoABuscar.ABIERTO;
                break;
            }
            case 2: {
                this.caminoABuscar = CaminoABuscar.CERRADO;
                break;
            }
            case 3: {
                this.caminoABuscar = CaminoABuscar.ABIERTO_Y_CERRADO;
                break;
            }
            default: {
                System.err.println("ERROR: No se ha especificado una opción válida");
                System.exit(1);
                break;
            }
        }
    }

    /**
     * Calcular el primer camino abierto para el tablero y guardar el resultado de
     * la prueba
     * 
     * @param debe_ser_cerrado     Si el camino a buscar debe ser cerrado o no
     * @param todas_las_soluciones Si se deben buscar todas las soluciones o no
     */
    private void solucionarProblemaCamino(boolean debe_ser_cerrado) {
        // Configurar el ejecutor del algoritmo
        Ejecutor ejecutor;
        if (this.todas_las_soluciones) {
            Algoritmo algoritmo = new AlgoritmoTodas(this.tablero);
            ejecutor = new Ejecutor(algoritmo, debe_ser_cerrado);
        } else {
            Algoritmo algoritmo = new AlgoritmoUna(this.tablero);
            ejecutor = new Ejecutor(algoritmo, debe_ser_cerrado);
        }

        // Crear un hilo de ejecución asociado al ejecutor
        Thread thread = new Thread(ejecutor);

        // Crear un temporizador y una tarea para poder parar ejecución del algoritmo
        // en caso de que se pase de tiempo
        Timer temporizador = new Timer();
        ParadorDePruebas parador = new ParadorDePruebas(thread, temporizador);

        // Momento en el que empezamos a medir el tiempo
        long inicio = System.nanoTime();

        // Calculamos la solución iniciando el hilo
        thread.start();

        // Comenzar la cuenta atrás del temporizador
        // Si se supera el tiempo estipulado, se detendrá el algoritmo y se procederá a
        // seguir con la ejecución del código
        temporizador.schedule(parador, tiempoLimite);

        try {
            // Esperar a que acabe el algoritmo
            thread.join();
        } catch (InterruptedException e) {
        }

        // Momento en el que terminamos de medir el tiempo
        long fin = System.nanoTime();

        // Parar el temporizador
        temporizador.cancel();

        // Obtenemos la solución del ejecutor
        List<Camino> camino = ejecutor.getSolucion();

        // Calculamos el tiempo de ejecución restando las dos marcas y lo pasamos a
        // milisegundos. Si se ha parado el algoritmo, usamos el máximo valor de tiempo
        // posible
        long tiempo = parador.tiempoSuperado() ? Long.MAX_VALUE : (fin - inicio) / 1000000;

        // Reiniciar el tablero
        this.tablero.reiniciar();

        // Guardar los resultados
        if (debe_ser_cerrado) {
            this.tiempoEjecucionCerrado = tiempo;
            this.solucionCerrada = camino;
        } else {
            this.tiempoEjecucionAbierto = tiempo;
            this.solucionAbierta = camino;
        }
    }

    /**
     * Ejecutar la prueba según los parámetros especificados
     */
    public void ejecutar() {
        if (this.caminoABuscar == CaminoABuscar.ABIERTO) {
            this.solucionarProblemaCamino(false);
        } else if (this.caminoABuscar == CaminoABuscar.CERRADO) {
            this.solucionarProblemaCamino(true);
        } else if (this.caminoABuscar == CaminoABuscar.ABIERTO_Y_CERRADO) {
            this.solucionarProblemaCamino(false);
            this.solucionarProblemaCamino(true);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        // Dimensiones del tablero
        sb.append("Dimensiones del tablero: ");
        sb.append(this.tablero.alto);
        sb.append("x");
        sb.append(this.tablero.ancho);
        sb.append(System.lineSeparator());

        // Camino abierto
        if (this.caminoABuscar == CaminoABuscar.ABIERTO || this.caminoABuscar == CaminoABuscar.ABIERTO_Y_CERRADO) {
            sb.append(System.lineSeparator());
            sb.append("SOLUCIÓN ABIERTA:");
            sb.append(System.lineSeparator());

            // Tiempo de ejecución
            sb.append("Tiempo empleado: ");
            sb.append(this.tiempoEjecucionAbierto == Long.MAX_VALUE
                    ? "Se ha excedido el tiempo límite (" + tiempoLimite + " milisegundos)"
                    : this.tiempoEjecucionAbierto + " milisegundos");
            sb.append(System.lineSeparator());

            // Si se ha encontrado un camino, mostrarlo gráficamente sobre el tablero
            sb.append(System.lineSeparator());
            if (this.solucionAbierta.size() == 0) {
                sb.append("No se ha encontrado ningún camino abierto." + System.lineSeparator());
            } else {
                // Imprimir los caminos encontrados marcados sobre el tablero
                solucionAbierta.forEach((solucion) -> {
                    sb.append(solucion);
                    sb.append(System.lineSeparator());
                });
            }
        }

        // Camino cerrado
        if (this.caminoABuscar == CaminoABuscar.CERRADO || this.caminoABuscar == CaminoABuscar.ABIERTO_Y_CERRADO) {
            sb.append(System.lineSeparator());
            sb.append("SOLUCIÓN CERRADA:");
            sb.append(System.lineSeparator());

            // Tiempo de ejecución
            sb.append("Tiempo empleado: ");
            sb.append(this.tiempoEjecucionCerrado == Long.MAX_VALUE
                    ? "Se ha excedido el tiempo límite (" + tiempoLimite + " milisegundos)"
                    : this.tiempoEjecucionCerrado + " milisegundos");
            sb.append(System.lineSeparator());

            // Si se ha encontrado un camino, mostrarlo gráficamente sobre el tablero
            sb.append(System.lineSeparator());
            if (this.solucionCerrada.size() == 0) {
                sb.append("No se ha encontrado ningún camino cerrado.");
            } else {
                // Imprimir los caminos encontrados marcados sobre el tablero
                solucionCerrada.forEach((solucion) -> {
                    sb.append(solucion);
                    sb.append(System.lineSeparator());
                });
            }
        }

        return sb.toString();
    }

    /**
     * Añadir los resultados de la prueba como registro en el archivo CSV
     * especificado
     * 
     * @param csv Impresor (creador) de CSV al que añadir el registro
     * @throws IOException
     */
    public void exportarResultadosEnCSV(CSVPrinter csv) throws IOException {
        /*
         * Determinar los valores a exportar dependiendo de las pruebas escogidas
         */

        // Camino abierto
        String abierto_existe = "N/A";
        String abierto_tiempo = "N/A";
        if (this.caminoABuscar == CaminoABuscar.ABIERTO || this.caminoABuscar == CaminoABuscar.ABIERTO_Y_CERRADO) {
            // Si se ha interrumpido la ejecución del algoritmo
            if (this.tiempoEjecucionAbierto == Long.MAX_VALUE) {
                if (todas_las_soluciones) {
                    // Mostrar el número de soluciones encontradas hasta el momento
                    abierto_existe = ">= " + Integer.toString(this.solucionAbierta.size());
                } else {
                    abierto_existe = "¿?";
                }
            }
            // Si se ha completado la ejecución del algoritmo
            else {
                // Si se han buscado todas las soluciones
                if (todas_las_soluciones) {
                    // Mostrar el número total de soluciones encontradas
                    abierto_existe = Integer.toString(this.solucionAbierta.size());
                }
                // Si sólo se ha buscado una solución
                else {
                    // Mostrar si se ha encontrado o no una solución
                    if (this.solucionAbierta.size() == 0) {
                        abierto_existe = "NO";
                    } else {
                        abierto_existe = "SÍ";
                    }
                }
            }

            // Tiempo empleado para encontrar el camino abierto
            if (this.tiempoEjecucionAbierto == Long.MAX_VALUE) {
                abierto_tiempo = "> " + this.tiempoLimite;
            } else {
                abierto_tiempo = Long.toString(this.tiempoEjecucionAbierto);
            }
        }

        // Camino cerrado
        String cerrado_existe = "N/A";
        String cerrado_tiempo = "N/A";
        if (this.caminoABuscar == CaminoABuscar.CERRADO || this.caminoABuscar == CaminoABuscar.ABIERTO_Y_CERRADO) {
            // Si se ha interrumpido la ejecución del algoritmo
            if (this.tiempoEjecucionCerrado == Long.MAX_VALUE) {
                if (todas_las_soluciones) {
                    // Mostrar el número de soluciones encontradas hasta el momento
                    cerrado_existe = ">= " + Integer.toString(this.solucionCerrada.size());
                } else {
                    cerrado_existe = "¿?";
                }
            }
            // Si se ha completado la ejecución del algoritmo
            else {
                // Si se han buscado todas las soluciones
                if (todas_las_soluciones) {
                    // Mostrar el número total de soluciones encontradas
                    cerrado_existe = Integer.toString(this.solucionCerrada.size());
                }
                // Si sólo se ha buscado una solución
                else {
                    // Mostrar si se ha encontrado o no una solución
                    if (this.solucionCerrada.size() == 0) {
                        cerrado_existe = "NO";
                    } else {
                        cerrado_existe = "SÍ";
                    }
                }
            }

            // Tiempo empleado para encontrar el camino cerrado
            if (this.tiempoEjecucionCerrado == Long.MAX_VALUE) {
                cerrado_tiempo = "> " + this.tiempoLimite;
            } else {
                cerrado_tiempo = Long.toString(this.tiempoEjecucionCerrado);
            }
        }

        csv.printRecord(
                // Dimensiones del tablero
                this.tablero.alto + "x" + this.tablero.ancho,
                abierto_existe,
                cerrado_existe,
                abierto_tiempo,
                cerrado_tiempo);
    }
}

/**
 * Tarea temporizada para parar un hilo de ejecución creado por la prueba
 */
class ParadorDePruebas extends TimerTask {
    private Thread hilo;
    private Timer temporizador;
    /**
     * Guarda registro de si ha hecho falta parar el hilo o no
     */
    private boolean tiempo_superado = false;

    /**
     * Averiguar si ha hecho falta parar el hilo o no
     * 
     * @return true si se ha superado el tiempo y se ha parado el hilo
     */
    public boolean tiempoSuperado() {
        return tiempo_superado;
    }

    public ParadorDePruebas(Thread hilo, Timer temporizador) {
        this.hilo = hilo;
        this.temporizador = temporizador;
    }

    @Override
    public void run() {
        // Mandar una señal de parada al hilo
        if (hilo != null && hilo.isAlive()) {
            hilo.interrupt();
            temporizador.cancel();
            this.tiempo_superado = true;
        }
    }
}
