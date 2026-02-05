
package EstudioEmpirico;
import Algoritmos.*;  

public class vectorOrdenable {
    int numeroDeElementos;
    int[]vector; 
    

    //Funciones de inicialización de arrays
    public void rellenarArray(){
        for (int i = 0; i<numeroDeElementos; i++){
            vector[i] = i; 
        }
    }
    public void rellenarInverso(){
        for (int i = numeroDeElementos-1; i >= 0; i--){
            vector[i] = i; 
        }
    }
    public void rellenarAleatorio(int max){
        for (int i = 0; i<numeroDeElementos; i++){
            vector[i] = (int) (Math.random() * max); 
        }
    }

    //Funciones para ejecutar los algoritmos
}
