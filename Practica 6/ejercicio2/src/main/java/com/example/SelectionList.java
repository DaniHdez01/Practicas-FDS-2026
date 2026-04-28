package com.example;

import java.util.ArrayList;

public class SelectionList implements Ordenacion{

    ArrayList <Integer> vector = new ArrayList<>();

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
