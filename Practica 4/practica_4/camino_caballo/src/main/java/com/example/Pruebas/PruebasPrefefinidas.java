package com.example.Pruebas;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.example.Algoritmo.Tablero.Tablero;

/**
 * Pila predefinida de soluciones para el algoritmo de una solución
 */
public class PruebasPrefefinidas {
    private ArrayList<Prueba> solucionesBasicas;
    private ArrayList<Prueba> solucionesComplejas;
    private ArrayList<Prueba> solucionesTodasSoluciones;

    /**
     * Formato de CSV a leer para obtener la información de las pruebas
     * 
     * Se trata del formato por defecto definido por Apache Commons CSV con las
     * cabeceras de columna ya definidas.
     */
    private final CSVFormat csvFormato = CSVFormat.DEFAULT.builder()
            .setHeader("TAMAÑO", "ABIERTO", "CERRADO", "TIEMPO ABIERTO (ms)", "TIEMPO CERRADO (ms)").build();

    public PruebasPrefefinidas() {
        this.solucionesBasicas = new ArrayList<Prueba>();
        this.solucionesComplejas = new ArrayList<Prueba>();
        this.solucionesTodasSoluciones = new ArrayList<Prueba>();
    }

    /**
     * Generamos una primera tabla con soluciones básicas, donde las dimensiones del
     * tablero cumplen con las restricciones que da el enunciado.
     */
    public void ejecutarPruebasPredefinidasBasicas() {
        /**
         * Pruebas con parámetros de entrada predefinidos
         */
        Prueba[] pruebas = {
                new Prueba(new Tablero(3, 3), Prueba.CaminoABuscar.ABIERTO_Y_CERRADO, false),
                new Prueba(new Tablero(4, 4), Prueba.CaminoABuscar.ABIERTO_Y_CERRADO, false),
                new Prueba(new Tablero(5, 5), Prueba.CaminoABuscar.ABIERTO_Y_CERRADO, false),
                new Prueba(new Tablero(6, 6), Prueba.CaminoABuscar.ABIERTO_Y_CERRADO, false),
                new Prueba(new Tablero(3, 10), Prueba.CaminoABuscar.ABIERTO_Y_CERRADO, false),
                new Prueba(new Tablero(3, 12), Prueba.CaminoABuscar.ABIERTO_Y_CERRADO, false),
                new Prueba(new Tablero(5, 6), Prueba.CaminoABuscar.ABIERTO_Y_CERRADO, false),
                new Prueba(new Tablero(3, 4), Prueba.CaminoABuscar.ABIERTO_Y_CERRADO, false),
                new Prueba(new Tablero(3, 5), Prueba.CaminoABuscar.ABIERTO_Y_CERRADO, false),
                new Prueba(new Tablero(3, 6), Prueba.CaminoABuscar.ABIERTO_Y_CERRADO, false),
                new Prueba(new Tablero(3, 8), Prueba.CaminoABuscar.ABIERTO_Y_CERRADO, false),
                new Prueba(new Tablero(4, 6), Prueba.CaminoABuscar.ABIERTO_Y_CERRADO, false),
                new Prueba(new Tablero(4, 3), Prueba.CaminoABuscar.ABIERTO_Y_CERRADO, false),
        };

        System.out.println("Ejecutando pruebas básicas...");

        // Ejecutar y registrar las pruebas en la pila
        for (Prueba prueba : pruebas) {
            // Indicar por pantalla la prueba a ejecutar
            System.out.println(
                    "Tablero: " + prueba.getTablero().alto + "x" + prueba.getTablero().ancho + ". Camino a buscar: "
                            + prueba.getCaminoABuscar().toString());

            // Ejecutar la prueba
            prueba.ejecutar();

            // Añadir a la lista de pruebas básicas ejecutadas
            this.solucionesBasicas.add(prueba);
        }
    }

    /**
     * Se nos pide también, una tabla de soluciones más compleja donde se ignoran
     * por completo las restricciones
     */
    public void ejecutarPruebasPredefinidasComplejas() {
        /**
         * Pruebas con parámetros de entrada predefinidos
         * 
         * Hay algunas pruebas para encontrar un camino cerrado nunca terminan, por lo
         * que no se busca ese camino.
         */
        Prueba[] pruebas = {
                new Prueba(new Tablero(7, 7), Prueba.CaminoABuscar.ABIERTO_Y_CERRADO, false),
                new Prueba(new Tablero(8, 8), Prueba.CaminoABuscar.ABIERTO_Y_CERRADO, false),
                new Prueba(new Tablero(3, 14), Prueba.CaminoABuscar.ABIERTO_Y_CERRADO, false),
                new Prueba(new Tablero(5, 8), Prueba.CaminoABuscar.ABIERTO_Y_CERRADO, false),
                new Prueba(new Tablero(6, 7), Prueba.CaminoABuscar.ABIERTO_Y_CERRADO, false),
                new Prueba(new Tablero(6, 8), Prueba.CaminoABuscar.ABIERTO_Y_CERRADO, false),
                new Prueba(new Tablero(4, 8), Prueba.CaminoABuscar.ABIERTO_Y_CERRADO, false),
                new Prueba(new Tablero(5, 7), Prueba.CaminoABuscar.ABIERTO_Y_CERRADO, false),
                new Prueba(new Tablero(8, 4), Prueba.CaminoABuscar.ABIERTO_Y_CERRADO, false),
                new Prueba(new Tablero(7, 5), Prueba.CaminoABuscar.ABIERTO_Y_CERRADO, false),
                new Prueba(new Tablero(14, 3), Prueba.CaminoABuscar.ABIERTO_Y_CERRADO, false)
        };

        System.out.println("Ejecutando pruebas complejas...");

        // Ejecutar y registrar las pruebas en la pila
        for (Prueba prueba : pruebas) {
            // Indicar por pantalla la prueba a ejecutar
            System.out.println(
                    "Tablero: " + prueba.getTablero().alto + "x" + prueba.getTablero().ancho + ". Camino a buscar: "
                            + prueba.getCaminoABuscar().toString());

            // Ejecutar la prueba
            prueba.ejecutar();

            // Añadir a la lista de pruebas complejas ejecutadas
            this.solucionesComplejas.add(prueba);
        }
    }

    /**
     * Se nos pide también, una tabla de soluciones para el algoritmo de búsqueda de
     * todas las soluciones
     */
    public void ejecutarPruebasPredefinidasTodasSoluciones() {
        /**
         * Pruebas con parámetros de entrada predefinidos
         * 
         * Hay algunas pruebas para encontrar un camino cerrado nunca terminan, por lo
         * que no se busca ese camino.
         */
        Prueba[] pruebas = {
                new Prueba(new Tablero(3, 3), Prueba.CaminoABuscar.ABIERTO_Y_CERRADO, true),
                new Prueba(new Tablero(4, 4), Prueba.CaminoABuscar.ABIERTO_Y_CERRADO, true),
                new Prueba(new Tablero(5, 5), Prueba.CaminoABuscar.ABIERTO_Y_CERRADO, true),
                new Prueba(new Tablero(6, 6), Prueba.CaminoABuscar.ABIERTO_Y_CERRADO, true),
                new Prueba(new Tablero(3, 10), Prueba.CaminoABuscar.ABIERTO_Y_CERRADO, true),
                new Prueba(new Tablero(3, 12), Prueba.CaminoABuscar.ABIERTO_Y_CERRADO, true),
        };

        System.out.println("Ejecutando pruebas de búsqueda de todas las soluciones...");

        // Ejecutar y registrar las pruebas en la pila
        for (Prueba prueba : pruebas) {
            // Indicar por pantalla la prueba a ejecutar
            System.out.println(
                    "Tablero: " + prueba.getTablero().alto + "x" + prueba.getTablero().ancho + ". Camino a buscar: "
                            + prueba.getCaminoABuscar().toString());

            // Ejecutar la prueba
            prueba.ejecutar();

            // Añadir a la lista de pruebas complejas ejecutadas
            this.solucionesTodasSoluciones.add(prueba);
        }
    }

    public void imprimirSoluciones() {
        System.out.println("Tabla 1 - Soluciones básicas:" + System.lineSeparator());
        if (solucionesBasicas.size() > 0) {
            for (Prueba prueba : solucionesBasicas) {
                System.out.println(prueba);
            }
        }

        System.out.println("Tabla 2 - Soluciones complejas:" + System.lineSeparator());
        if (solucionesComplejas.size() > 0) {
            for (Prueba prueba : solucionesComplejas) {
                System.out.println(prueba);
            }
        }

        System.out.println("Tabla 3 - Todas las soluciones:" + System.lineSeparator());
        if (solucionesTodasSoluciones.size() > 0) {
            for (Prueba prueba : solucionesTodasSoluciones) {
                System.out.println(prueba);
            }
        }
    }

    /**
     * Generar el fichero con la representación visual de los caminos obtenidos
     * 
     * @throws IOException
     */
    public void generarFichero() throws IOException {
        String nombreArchivo = "Caminos.txt";
        FileWriter archivo = new FileWriter(nombreArchivo);
        try {
            if (solucionesBasicas.size() > 0) {
                archivo.write("#####################################" + System.lineSeparator());
                archivo.write("#### TABLA 1: SOLUCIONES BÁSICAS ####" + System.lineSeparator());
                archivo.write("#####################################" + System.lineSeparator());
                for (int i = 0; i < solucionesBasicas.size(); i++) {
                    archivo.write(solucionesBasicas.get(i).toString());
                    archivo.write(System.lineSeparator());

                    // Separador entre pruebas
                    archivo.write("///////////////////////////////////" + System.lineSeparator());
                }
                archivo.write(System.lineSeparator());
            }

            if (solucionesComplejas.size() > 0) {
                archivo.write("#######################################" + System.lineSeparator());
                archivo.write("#### TABLA 2: SOLUCIONES COMPLEJAS ####" + System.lineSeparator());
                archivo.write("#######################################" + System.lineSeparator());
                for (int i = 0; i < solucionesComplejas.size(); i++) {
                    archivo.write(solucionesComplejas.get(i).toString());
                    archivo.write(System.lineSeparator());

                    // Separador entre pruebas
                    archivo.write("///////////////////////////////////" + System.lineSeparator());
                }
                archivo.write(System.lineSeparator());
            }

            if (solucionesTodasSoluciones.size() > 0) {
                archivo.write("#######################################" + System.lineSeparator());
                archivo.write("#### TABLA 3: TODAS LAS SOLUCIONES ####" + System.lineSeparator());
                archivo.write("#######################################" + System.lineSeparator());
                for (int i = 0; i < solucionesTodasSoluciones.size(); i++) {
                    archivo.write(solucionesTodasSoluciones.get(i).toString());
                    archivo.write(System.lineSeparator());

                    // Separador entre pruebas
                    archivo.write("///////////////////////////////////" + System.lineSeparator());
                }
            }
            System.out.println("Archivo generado.");
        } catch (IOException e) {
            System.out.println("Error al generar el archivo.");
        }
        archivo.close();
    }

    /**
     * Generar los ficheros CSV correspondientes para los resultados de las pruebas
     */
    public void generarCSV() {
        // Archivo para exportar los datos de las soluciones simples
        if (solucionesBasicas.size() > 0) {
            File archivo_sol_basicas = new File("Tabla 1 - Soluciones simples.csv");

            // Crear el archivo CSV
            try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(archivo_sol_basicas), csvFormato)) {
                // Incluir, línea por línea, las distintas entradas del registro en el CSV
                for (Prueba prueba : this.solucionesBasicas) {
                    prueba.exportarResultadosEnCSV(csvPrinter);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        // Archivo para exportar los datos de las soluciones complejas
        if (solucionesComplejas.size() > 0) {
            if (solucionesComplejas.size() > 0) {
                File archivo_sol_complejas = new File("Tabla 2 - Soluciones complejas.csv");

                // Crear el archivo CSV
                try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(archivo_sol_complejas), csvFormato)) {
                    // Incluir, línea por línea, las distintas entradas del registro en el CSV
                    for (Prueba prueba : this.solucionesComplejas) {
                        prueba.exportarResultadosEnCSV(csvPrinter);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }

        // Archivo para exportar los datos de las pruebas para todas las soluciones
        if (solucionesTodasSoluciones.size() > 0) {
            File archivo_sol_todas = new File("Tabla 3 - Todas las soluciones.csv");

            // Crear el archivo CSV
            try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(archivo_sol_todas), csvFormato)) {
                // Incluir, línea por línea, las distintas entradas del registro en el CSV
                for (Prueba prueba : this.solucionesTodasSoluciones) {
                    prueba.exportarResultadosEnCSV(csvPrinter);
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
