package com.example;

public class Test 
{
    public static void main( String[] args )
    {
        if (args.length != 2){
            throw new RuntimeException("Hay que especificar dos operandos como argumentos para poder usar la calculadora.");
        }

        // Instanciamos los operadores
        Operador suma = new Suma();
        Operador resta = new Resta();
        Operador multiplicacion = new Multiplicacion();
        Operador division = new Division();
        Operador division_lenta = new DivisionLenta();

        // Instanciamos la calculadora
        Calculadora2 calc = new Calculadora2();

        // Probar la calculadora con todas las operaciones escritas
        int num1 = Integer.parseInt(args[0]);
        int num2 = Integer.parseInt(args[1]);
        System.out.println(num1 + " + " + num2 + " = " + calc.calcular(num1, num2, suma));
        System.out.println(num1 + " - " + num2 + " = " + calc.calcular(num1, num2, resta));
        System.out.println(num1 + " * " + num2 + " = " + calc.calcular(num1, num2, multiplicacion));
        System.out.println(num1 + " / " + num2 + " = " + calc.calcular(num1, num2, division));
        System.out.println(num1 + " / " + num2 + " = " + calc.calcular(num1, num2, division_lenta));
    }
}
