package com.example;

import java.util.Scanner;

public class Prueba {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Pedimos al usuario que introduzca el número de elementos
        System.out.print("Introduce el número de elementos: ");
        int n = sc.nextInt();

        int[] vector = new int[n];

        // Pedimos al usuario que introduzca los valores del array
        System.out.println("Introduce los valores del array:");
        for (int i = 0; i < n; i++) {
            vector[i] = sc.nextInt();
        }

        // Creamos una instancia del adaptador que implementa Estatico
        Estatico adaptador = new Adaptador();

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
