package com.example;
import java.util.ArrayList;
public class Test {
    public static void main(String [] args){
        FactorySort arrayListBurbuja = new FactoryList(); 
        FactorySort arrayListSelection = new FactoryList(); 
        FactorySort staticBurbuja = new FactoryStatic(); 
        FactorySort staticSelection = new FactoryStatic(); 

        System.out.println("ArrayList Burbuja sin ordenar: ");
        arrayListBurbuja.imprimir("BURBUJA"); 
        System.out.println("ArrayList Seleccion sin ordenar: ");
        arrayListSelection.imprimir("SELECCION");
        System.out.println("Array estatico Burbuja sin ordenar: ");
        staticBurbuja.imprimir("BURBUJA"); 
        System.out.println("Array estatico Seleccion sin ordenar: ");
        staticSelection.imprimir("SELECCION"); 

        arrayListBurbuja.ordenar("BURBUJA"); 
        arrayListSelection.ordenar("SELECCION"); 
        staticBurbuja.ordenar("BURBUJA"); 
        staticSelection.ordenar("SELECCION"); 

        System.out.println("ArrayList Burbuja ordenado: ");
        arrayListBurbuja.imprimir("BURBUJA"); 
        System.out.println("ArrayList Seleccion ordenado: ");
        arrayListSelection.imprimir("SELECCION");
        System.out.println("Array estatico Burbuja ordenado: ");
        staticBurbuja.imprimir("BURBUJA"); 
        System.out.println("Array estatico Seleccion ordenado: ");
        staticSelection.imprimir("SELECCION"); 

    }
}
