package com.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import com.example.Pruebas.Prueba;
import com.example.Pruebas.PruebasPrefefinidas;

public class Main {
    public static void main(String[] args) throws IOException {
        // Mini menú para hacer las pruebas que deseemos
        System.out.println("Opción 1: Ejecutar una prueba personalizada");
        System.out.println("Opción 2: Ejecutar la batería de pruebas predefinida");
        System.out.println("Opción 3: Ejecutar la batería de pruebas predefinida para buscar todas las soluciones");
        System.out.println("Opción 4: Salir");
        System.err.print("Introduce el número de opción: ");
        Scanner scanner = new Scanner(System.in); // Opción que el usuario meterá en la consola
        int opcion = scanner.nextInt();

        System.out.println();

        switch (opcion) {
            case 1: {
                // Prueba única con tablero personalizado
                // Configurar la prueba de forma interactiva
                Prueba prueba = new Prueba(scanner);
                System.out.println();
                System.out.println("Ejecutando prueba...");
                System.out.println();
                prueba.ejecutar();
                if (prueba.todasSolucionesPedidas()) {
                    // Limpiar el buffer de entrada
                    if (scanner.hasNextLine()) {
                        scanner.nextLine();
                    }

                    String confirmacion;

                    // Preguntar si se quiere imprimir las soluciones por pantalla
                    System.out.println("Se han encontrado " + prueba.getSolucionesEncontradas() + " soluciones.");
                    System.out.print("¿Deseas imprimirlas por pantalla? [s/n] ");

                    confirmacion = scanner.nextLine();
                    if (confirmacion.equals("s")) {
                        System.out.println(prueba);
                    }

                    // Preguntar si se quiere imprimir las soluciones por pantalla
                    System.out.print("¿Deseas exportarlas a un archivo de texto? [s/n] ");
                    // Debido a que se va a extraer una línea, es necesario limpiar el buffer de
                    // entrada
                    confirmacion = scanner.nextLine();
                    if (confirmacion.equals("s")) {
                        String nombreArchivo = "Caminos.txt";
                        FileWriter archivo = new FileWriter(nombreArchivo);
                        archivo.write(prueba.toString());
                        archivo.close();
                    }
                } else {
                    System.out.println("==========================");
                    System.out.println("  RESULTADO DE LA PRUEBA  ");
                    System.out.println("==========================");
                    System.out.println(prueba);
                }
                break;
            }

            case 2: {
                // Pila de pruebas
                PruebasPrefefinidas pruebas = new PruebasPrefefinidas();
                pruebas.ejecutarPruebasPredefinidasBasicas();
                System.out.println();
                pruebas.ejecutarPruebasPredefinidasComplejas();
                pruebas.generarFichero();
                pruebas.generarCSV();
                break;
            }
            case 3: {
                PruebasPrefefinidas pruebas = new PruebasPrefefinidas();
                pruebas.ejecutarPruebasPredefinidasTodasSoluciones();
                pruebas.generarFichero();
                pruebas.generarCSV();
            }

            case 4: // Salir
                break;
        }

        // Cerramos el Scanner al final del programa para no cerrar la entrada estándar
        // de texto en caso de que sea necesaria en otro sitio
        // https://stackoverflow.com/a/13042296
        scanner.close();
    }
}