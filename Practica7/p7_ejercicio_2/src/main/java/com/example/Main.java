package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        Scanner scanner = new Scanner(System.in);
        boolean salir = false;

        while (!salir) {
            System.out.println("Bienvenido al gestor de peliculas: ");
            System.out.println("Opcion 1: Ver cartelera: ");
            System.out.println("Opcion 2: Comprar entradas: ");
            System.out.println("Opcion 3 consultar disponibilidad: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    System.out.println("Mostrando cartelera...");
                    cliente.consultarPeliculas();
                    break;
                case 2:
                    System.out.println("Introduce la pelicula que deseas ver");
                    String peliculaParaComprar = scanner.nextLine();
                    cliente.pagar(peliculaParaComprar);
                    break;
                case 3:
                    System.out.println("Consultar disponibilidad...");
                    String peliculaAConsultar = scanner.nextLine();
                    cliente.consultarDisponibilidad(peliculaAConsultar);
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    salir = true;
                    break;
                default:
                    System.out.println("Opcion no valida. Intente de nuevo.");
            }

        }
        scanner.close();
    }
}