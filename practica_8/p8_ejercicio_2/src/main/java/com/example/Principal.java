package com.example;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class Principal 
{
    public static void main( String[] args )
    {
        // Inicializar array desordenado
        int[] arrayA = {2, 1, 3};
        int[] arrayB = Arrays.copyOf(arrayA, arrayA.length);

        // Imprimir array
        System.out.print("Array desordenado: ");
        for(int i = 0; i < arrayA.length; i++){
            System.out.print(Integer.toString(arrayA[i]) + " ");
        }
        System.out.println();

        // Ordenar el array con el algoritmo de burbuja
        Ordenador ordenador = new Ordenador(new OrdenarBurbuja());
        ordenador.ordenar(arrayA);

        // Imprimir array
        System.out.print("Array ordenado con algoritmo de la burbuja (creciente): ");
        for(int i = 0; i < arrayA.length; i++){
            System.out.print(Integer.toString(arrayA[i]) + " ");
        }
        System.out.println();

        // Ordenar el array con el algoritmo de selección
        ordenador.cambiarBehavior(new OrdenarSeleccion());
        ordenador.ordenar(arrayB);

        // Imprimir array
        System.out.print("Array ordenado con algoritmo de selección directa (decreciente): ");
        for(int i = 0; i < arrayB.length; i++){
            System.out.print(Integer.toString(arrayB[i]) + " ");
        }
        System.out.println();
    }
}
