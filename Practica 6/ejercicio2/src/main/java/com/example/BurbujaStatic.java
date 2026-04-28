package com.example;

public class BurbujaStatic implements Ordenacion{
    private static int [] vector = {10,9,8,7,6,5,4,3,2,1};

    @Override
    public void ordenar() {
        for (int i = 0; i < vector.length - 1; i++) {
            for (int j = vector.length - 1; j > i; j--) {
                if (vector[j - 1] > vector[j]) {
                    int temp = vector[j - 1];
                    vector[j - 1] = vector[j];
                    vector[j] = temp;
                }
            }
        }

       
    }

    @Override
    public void imprimir() {
        for(int i =0; i<vector.length; i++){
            System.out.println(vector[i]);
        }
        
    } 
    



}
