package com.example;

public class Calculadora2 {
    public int calcular(int num1, int num2, Operador operador) {
        return operador.operar(num1, num2);
    }
}
