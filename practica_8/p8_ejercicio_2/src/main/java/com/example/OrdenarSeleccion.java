package com.example;

public class OrdenarSeleccion implements OrdenarBehavior{
    public void ordenar (int [] array){
        int m;
        for (int i=0; i< array.length-1; i++){
            m = i;
            for(int j = i+1; j < array.length; j++){
                if (array[j] > array[m]){
                    m = j;
                }
            }
            int aux = array[i];
            array[i] = array[m];
            array[m] = aux;
        }
    }
}
