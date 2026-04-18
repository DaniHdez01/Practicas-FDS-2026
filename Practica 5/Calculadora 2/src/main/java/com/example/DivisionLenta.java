package com.example;

public class DivisionLenta implements Operador {
    public int operar(int num1, int num2) {
        // Cociente resultante
        int cociente = 0;

        // Hasta que el dividendo sea menor que el divisor, restarle al dividendo el
        // valor del divisor e incrementar el cociente en 1
        for (int dividendo = num1; dividendo >= num2; dividendo -= num2) {
            cociente++;
        }

        return cociente;
    }
}
