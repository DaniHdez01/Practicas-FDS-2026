
package EstudioEmpirico;
import Algoritmos.*;  

public class vectorOrdenable {
    private int numeroDeElementos;
    private volatile int[]vector; 
    
    public vectorOrdenable(int n){
        this.numeroDeElementos = n; 
        this.vector = new int[numeroDeElementos]; 
    }

    //Funciones de inicialización de arrays
    public void rellenarArray(){
        for (int i = 0; i<numeroDeElementos; i++){
            vector[i] = i; 
        }
    }
    public void rellenarInverso(){
        for (int i = 0; i<numeroDeElementos ; i++){
            vector[i] = numeroDeElementos - 1 - i; 
        }
    }
    public void rellenarAleatorio(int max){
        for (int i = 0; i<numeroDeElementos; i++){
            vector[i] = (int) (Math.random() * max); 
        }
    }

    //Función para imprimir vector 

    public void imprimirVector(){
        for(int i =0; i <numeroDeElementos; i++){
            System.out.println(vector[i]);
        }
    }
    
    //Funciones de ejecución de los algorítmos
    public long ordenarPorBurbuja(){
        long inicio = System.nanoTime(); 
        AlgoritmoBurbuja.burbuja(vector); 
        long terminado = System.nanoTime(); 
        long tiempo = terminado - inicio; 
        return tiempo; 
    }

    public long ordenarPorSeleccion(){
        long inicio = System.nanoTime(); 
        AlgoritmoSelectionSort.selectSort(vector);
        long terminado = System.nanoTime(); 
        long tiempo = terminado - inicio; 
        return tiempo; 
    }
    public long ordenarPorMezcla(){
        long inicio = System.nanoTime(); 
        AlgoritmoMerge.mergeSort(vector);
        long terminado = System.nanoTime(); 
        long tiempo = terminado - inicio; 
        return tiempo; 
    }
}




