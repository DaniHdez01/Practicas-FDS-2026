package es.Main;

import EstudioEmpirico.Complejidad;

//COMPILAR: javac -d out -sourcepath src/main/java src/main/java/es/Main/Main.java     
//EJECUTAR: Java -cp out es.Main.Main         

public class Main {
    public static void main(String[] args) {
        Complejidad complejidad = new Complejidad(1000); 
        complejidad.ejecutarAlgoritmos();
        complejidad.imprimeTiempos();
        complejidad.guardarDatos("tiempos");
    }
}