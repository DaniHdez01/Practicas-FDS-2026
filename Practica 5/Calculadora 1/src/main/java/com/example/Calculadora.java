package com.example;
public class Calculadora {
    public int calcular(int num1, int num2, char op) {
        // Declarar objeto operador
        Operador operador = null;

        // Decidir operador a utilizar
        switch (op) {
            case 'S':
                operador = new Sumador();
                break;
            case 'R':
                operador = new Restador();
                break;
            case 'M':
                operador = new Multiplicador();
                break;
            case 'D':
                operador = new Divisor();
                break;
            default:
                throw new IllegalArgumentException("Operación no válida");
        }

        // Realizar operación
        return operador.operar(num1, num2);
    }
}
