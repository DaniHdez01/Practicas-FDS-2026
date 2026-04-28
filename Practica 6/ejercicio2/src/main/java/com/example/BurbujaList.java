package com.example;
import java.util.ArrayList;
public class BurbujaList implements Ordenacion {

    private ArrayList<Integer> vector = new ArrayList<>();

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
