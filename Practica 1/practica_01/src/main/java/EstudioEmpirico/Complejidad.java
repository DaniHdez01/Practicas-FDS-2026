package EstudioEmpirico;

public class Complejidad {
    private int nIteracciones; 
    private volatile vectorOrdenable vectorOrdenado; 
    private volatile vectorOrdenable vectorInverso; 
    private volatile vectorOrdenable vectorAleatorio; 
    private MatrizDeTiempos matrizTiempos; 

    public Complejidad(int n){
        this.nIteracciones = n;
        this.matrizTiempos = new MatrizDeTiempos(3, 3); 
    }
    public void ejecutarAlgoritmos(){

        //BURBUJA
        //CASO MEJOR
        vectorOrdenado = new vectorOrdenable(nIteracciones); 
        vectorOrdenado.rellenarArray();
        matrizTiempos.asignaValor(0, 0, vectorOrdenado.ordenarPorBurbuja()); 
        
        
        //CASO PEOR
        vectorInverso = new vectorOrdenable(nIteracciones);  
        vectorInverso.rellenarInverso();
        matrizTiempos.asignaValor(1, 0, vectorInverso.ordenarPorBurbuja());

        //CASO MEDIO
        vectorAleatorio = new vectorOrdenable(nIteracciones); 
        vectorAleatorio.rellenarAleatorio(nIteracciones);
        matrizTiempos.asignaValor(2, 0, vectorAleatorio.ordenarPorBurbuja());


        //MEZCLA 
        //CASO MEJOR: 
        vectorOrdenado = new vectorOrdenable(nIteracciones); 
        vectorOrdenado.rellenarArray();
        matrizTiempos.asignaValor(0, 1,vectorOrdenado.ordenarPorMezcla());

        //CASO PEOR:
        vectorInverso = new vectorOrdenable(nIteracciones);
        vectorInverso.rellenarInverso();
        matrizTiempos.asignaValor(1, 1, vectorInverso.ordenarPorMezcla());
        
        //CASO MEDIO: 
        vectorAleatorio = new vectorOrdenable(nIteracciones);
        vectorAleatorio.rellenarAleatorio(nIteracciones);
        matrizTiempos.asignaValor(2, 1, vectorAleatorio.ordenarPorMezcla());
        
        //SLECTION SORT
        //CASO MEJOR 
        vectorOrdenado = new vectorOrdenable(nIteracciones); 
        vectorOrdenado.rellenarArray();
        matrizTiempos.asignaValor(0, 2, vectorOrdenado.ordenarPorSeleccion());
        //CASO PEOR
        vectorInverso = new vectorOrdenable(nIteracciones);
        vectorInverso.rellenarInverso();
        matrizTiempos.asignaValor(1, 2,vectorInverso.ordenarPorSeleccion());
        //CASO MEDIO
        vectorAleatorio = new vectorOrdenable(nIteracciones);
        vectorAleatorio.rellenarAleatorio(nIteracciones);
        matrizTiempos.asignaValor(2, 2, vectorAleatorio.ordenarPorSeleccion());
    

    }

    public void imprimeTiempos(){
        this.matrizTiempos.imprimeTiempos();
    }
    public void guardarDatos(String nombreArchivo){
        this.matrizTiempos.guardarTiempos(nombreArchivo);
    }
}
