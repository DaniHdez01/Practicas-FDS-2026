package com.example;

public class SelectionStatic implements Ordenacion{

    private static int [] vector = {10,9,8,7,6,5,4,3,2,1};

    @Override
    public void ordenar(){
         int m;
        for (int i = 0; i < vector.length - 1; i++) {
            m = i;
            for (int j = i + 1; j < vector.length; j++) {
                if (vector[j] < vector[m])
                    m = j;
            }
            int aux = vector[i];
            vector[i] = vector[m];
            vector[m] = aux;
        }

    }

    @Override 
    public void imprimir(){
        for(int i = 0; i<vector.length; i++){
            System.out.println(vector[i]);
        }
    }

}
