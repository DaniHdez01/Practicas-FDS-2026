package com.example;

import java.util.ArrayList;
import java.util.Arrays;

public class SelectionList implements Ordenacion{
    //Clase que ordenará un arraylist mediante el algoritmo de selección
    ArrayList<Integer> vector = new ArrayList<>(Arrays.asList(10, 9, 8, 7, 6, 5, 4, 3, 2, 1));

    //Cada clase redefinirá los métodos declarados en la clase Ordenación 
    @Override
    public void ordenar(){
         int m;
        for (int i = 0; i < vector.size() - 1; i++) {
            m = i;
            for (int j = i + 1; j < vector.size(); j++) {
                if (vector.get(j) < vector.get(m))
                    m = j;
            }
            int aux = vector.get(i);
            vector.set(i, vector.get(m));
            vector.set(m, aux);
        }

    }
    @Override
    public void imprimir(){
        for(int i = 0; i<vector.size(); i++){
            System.out.println(vector.get(i)); 
        }
    }

}
