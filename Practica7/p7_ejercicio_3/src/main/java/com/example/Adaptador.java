package com.example;

import java.util.ArrayList;

// Esta clase actúa como un Adaptador para hacer compatible MergeSort con la interfaz Estatico
public class Adaptador implements Estatico {

    // Instancia de la clase MergeSort que vamos a adaptar
    private MergeSort mergeSort;

    // Constructor: inicializa el objeto MergeSort
    public Adaptador() {
        this.mergeSort = new MergeSort();
    }

    // Implementación del método de la interfaz Estatico
    @Override
    public void ordena(int[] v) {
        // Convertimos el array de enteros a un ArrayList de enteros
        ArrayList<Integer> lista = new ArrayList<Integer>();
        for (int i : v) {
            lista.add(i);
        }

        // Utilizamos el método original de MergeSort que ordena un ArrayList
        mergeSort.ordena(lista);

        // Volvemos a copiar los elementos ordenados al array original
        for (int i = 0; i < v.length; i++) {
            v[i] = lista.get(i);
        }
    }
}
