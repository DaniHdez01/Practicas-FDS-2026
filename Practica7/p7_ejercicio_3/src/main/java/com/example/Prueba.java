package com.example;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
public class Prueba {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Pedimos al usuario que introduzca el número de elementos
        System.out.print("Introduce el número de elementos: ");
        int n = sc.nextInt();

        int[] vector = new int[n];

        System.out.println("Generando un array de longitud" + n + " con números aleatorios: "); 
        Random random = new Random(); 
        for (int i = 0; i < n; i++) {
            vector[i] = random.nextInt(100) +1; 
        }
        System.out.println("Array generado: " + Arrays.toString(vector)); 

        // Creamos una instancia del adaptador que implementa Estatico
        IOrdena adaptador = new Adaptador();

        // Usamos el adaptador para ordenar el array
        adaptador.ordena(vector);

        // Mostramos el array ordenado
        System.out.println("Array ordenado:");
        for (int num : vector) {
            System.out.print(num + " ");
        }
        System.out.println();

        sc.close();
    }
}
