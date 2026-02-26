package es.main;
import Tools.*; 
public class Main {
    public static void main(String[] args) {

        
        int[] tamaños = {100, 50, 10, 20, 50};

        System.out.println("Tamaño\tBruta(ns)\tDivideVenceras(ns)");

        for (int n : tamaños) {
            int[] vector = Generador.generarVector(n);

            //fuerza bruta
            long start = System.nanoTime();
            Bruta.inversiones(vector);
            long end = System.nanoTime();
            long tiempoBruta = (end - start) ;

            //divide y venceras
            start = System.nanoTime();
            DivideYVenceras.contarInversiones(vector);
            end = System.nanoTime();
            long tiempoDivide = (end - start) ;

            System.out.println(n + "\t" + tiempoBruta + "\t\t" + tiempoDivide);
        }
    }

}