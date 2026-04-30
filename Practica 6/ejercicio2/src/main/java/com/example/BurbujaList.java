package com.example;
import java.util.ArrayList;
import java.util.Arrays;
public class BurbujaList implements Ordenacion {
    //Clase que con un arraylist ordenará mediante el método burbuja
        ArrayList<Integer> vector = new ArrayList<>(Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1));

    //Cada clase redefinirá los métodos declarados en la clase Ordenación 
    @Override
    public void ordenar() {
       for (int i = 0; i < vector.size() - 1; i++) {
            for (int j = vector.size() - 1; j > i; j--) {
                if (vector.get(j - 1) > vector.get(j)) {
                    int temp = vector.get(j - 1);
                    vector.set(j-1, vector.get(j));
                    vector.set(j, temp);
                }
            }
        }

    }

    @Override
    public void imprimir(){
        for(int i = 0; i< vector.size(); i++){
            System.out.println(vector.get(i));
        }
    }
}
