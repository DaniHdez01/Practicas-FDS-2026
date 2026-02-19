package EstudioEmpirico;
import java.util.Arrays;
import Algoritmos.*;  
import java.util.Random; 
public class vectorOrdenable {
    private int numeroDeElementos;
    private volatile int[]vector; //Vector donde cambiaremos el volatile 
    
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
        Random random = new Random(); 
        for (int i = 0; i<numeroDeElementos; i++){
            vector[i] = (int) (random.nextInt(max)); 
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
        int[] array_a_ordenar = Arrays.copyOf(this.vector, this.vector.length);
        long inicio = System.nanoTime(); 
        AlgoritmoBurbuja.burbuja(array_a_ordenar); 
        long terminado = System.nanoTime(); 
        long tiempo = terminado - inicio; 
        return tiempo; 
    }

    public long ordenarPorSeleccion(){
        int[] array_a_ordenar = Arrays.copyOf(this.vector, this.vector.length);
        long inicio = System.nanoTime(); 
        AlgoritmoSelectionSort.selectSort(array_a_ordenar);
        long terminado = System.nanoTime(); 
        long tiempo = terminado - inicio; 
        return tiempo; 
    }
    public long ordenarPorMezcla(){
        int[] array_a_ordenar = Arrays.copyOf(this.vector, this.vector.length);
        long inicio = System.nanoTime(); 
        AlgoritmoMerge.mergeSort(array_a_ordenar);
        long terminado = System.nanoTime(); 
        long tiempo = terminado - inicio; 
        return tiempo; 
    }
}




