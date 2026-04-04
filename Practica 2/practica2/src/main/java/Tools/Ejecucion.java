package Tools;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Ejecucion {
    private long [] tiemposDYV; 
    private long [] tiemposBruta; 
    private static String titulos[] = {"Tamaño", "Bruta(ns)", "DivideVenceras(ns)"}; 

    public Ejecucion() {
        this.tiemposDYV = new long[5];
        this.tiemposBruta = new long[5];
    }
    
    public void ejecucionMasiva(int [] sizes){
        int[] tamaños = sizes; 
        int i = 0; 
        System.out.println("Tamaño\tBruta(ns)\tDivideVenceras(ns)");
        for (int n : tamaños) {
            int[] vector = Generador.generarVector(n);

            //fuerza bruta
            long tiempoBruta = ejecucionBrutaUnitaria(vector);  
            //divide y venceras
            long tiempoDivide= ejecucionDivideYVencerasUnitaria(vector); 
            System.out.println(n + "\t" + tiempoBruta + "\t\t" + tiempoDivide); 
            tiemposDYV[i] = tiempoDivide; 
            tiemposBruta[i] = tiempoBruta; 
            i++; 
        }
        generarCSV(tamaños, "tiempos"); 
    }
    public long ejecucionBrutaUnitaria(int [] vector){
            long start = System.nanoTime();
            Bruta.inversiones(vector);
            long end = System.nanoTime();
            long tiempoBruta = (end - start); 
            return tiempoBruta; 
    }

    public long ejecucionDivideYVencerasUnitaria(int[] vector){
            //divide y venceras
            long start = System.nanoTime();
            DivideYVenceras.contarInversiones(vector);
            long end = System.nanoTime();
            long tiempoDivide = (end - start) ;
            return tiempoDivide; 
    }
    //Generar CSV
    private void generarCSV(int [] tamaños, String nombreArchivo){
        nombreArchivo = nombreArchivo + ".csv"; 
        
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo));


            for(int i = 0; i< titulos.length; i++){
                bw.write(titulos[i] + ";");
            }
            bw.newLine();
            for(int i = 0; i< tamaños.length; i++){
                bw.write(tamaños[i] + ";" + this.tiemposBruta[i] + ";" + this.tiemposDYV[i] + ";") ;
                bw.newLine(); 
            }
            System.out.println("Archivo .csv cargado correctamente"); 
            bw.close(); 
        } catch (IOException e){
            System.err.println("Error al cargar el archivo");
        }

    }

    public long[] getTiemposDYV() {
        return tiemposDYV;
    }

    public long[] getTiemposBruta() {
        return tiemposBruta;
    }

    
}
