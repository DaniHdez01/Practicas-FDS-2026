package com.example;
import java.util.ArrayList;
public class Test {

    /* PARA LA CLASE TEST SE NOS PIDE: 
        1. Crear las 4 instancias 
        2. Imprimir la lista desordenada 
        3. Ordenar según su método 
        4. Imprimir la lista ordenada 

        Para cada función debemos pasar como parámetro que algoritmo vamos a utilizar 
        utilizando los métodos correspondientes de las clases Factory
     */
    public static void main(String [] args){

        //CREACIÓN DE NUEVAS INSTANCIAS MEDIANTE FACTORY 
        FactorySort arrayListBurbuja = new FactoryList(); 
        FactorySort arrayListSelection = new FactoryList(); 
        FactorySort staticBurbuja = new FactoryStatic(); 
        FactorySort staticSelection = new FactoryStatic(); 

        //IMPRIMIR LOS ARREGLOS DESORDENADOS CON LOS MÉTODOS ESTABLECIDOS EN ORDENACIÓN
        System.out.println("ArrayList Burbuja sin ordenar: ");
        arrayListBurbuja.imprimir("BURBUJA"); 
        System.out.println("ArrayList Seleccion sin ordenar: ");
        arrayListSelection.imprimir("SELECCION");
        System.out.println("Array estatico Burbuja sin ordenar: ");
        staticBurbuja.imprimir("BURBUJA"); 
        System.out.println("Array estatico Seleccion sin ordenar: ");
        staticSelection.imprimir("SELECCION"); 

        //APLICAR EL ALGORITMO CORRESPONDIENTE A CADA ARREGLO 
        arrayListBurbuja.ordenar("BURBUJA"); 
        arrayListSelection.ordenar("SELECCION"); 
        staticBurbuja.ordenar("BURBUJA"); 
        staticSelection.ordenar("SELECCION"); 


        //IMPRIMIR LAS LISTAS YA ORDENADAS
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
