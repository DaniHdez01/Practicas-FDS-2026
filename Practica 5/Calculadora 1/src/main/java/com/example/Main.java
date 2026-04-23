package com.example;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("--CALCULADORA--");
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Introduce el primer número: ");
            int num1 = scanner.nextInt();

            System.out.print("Introduce el segundo número: ");
            int num2 = scanner.nextInt();

            System.out.print("Introduce la operación (S para Suma, R para Resta, M para Multiplicación, D para División): ");
            char operadorChar = scanner.next().charAt(0);

            Calculadora calculadora = new Calculadora();
            int resultado = calculadora.calcular(num1, num2, operadorChar);

            System.out.println("El resultado de la operación es: " + resultado);
        } catch (InputMismatchException e) {
            System.err.println("Error: Entrada inválida. Por favor, introduce números enteros para los operandos.");
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}