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
        long tBurbujaMejor = vectorOrdenado.ordenarPorBurbuja(); 
        matrizTiempos.asignaValor(0, 0, tBurbujaMejor); 
        //CASO PEOR
        vectorInverso = new vectorOrdenable(nIteracciones);  
        vectorInverso.rellenarInverso();
        long tBurbujaPeor = vectorInverso.ordenarPorBurbuja();
        matrizTiempos.asignaValor(0, 1, tBurbujaPeor);
        //CASO MEDIO
        vectorAleatorio = new vectorOrdenable(nIteracciones); 
        vectorAleatorio.rellenarAleatorio(nIteracciones);
        long tBurbujaMedio = vectorAleatorio.ordenarPorBurbuja();
        matrizTiempos.asignaValor(0, 2, tBurbujaMedio);


        //MEZCLA 
        //CASO MEJOR: 
        vectorOrdenado = new vectorOrdenable(nIteracciones); 
        vectorOrdenado.rellenarArray();
        long tMezclaMejor = vectorOrdenado.ordenarPorMezcla();
        matrizTiempos.asignaValor(1, 0,tMezclaMejor);
        //CASO PEOR:
        vectorInverso = new vectorOrdenable(nIteracciones);
        vectorInverso.rellenarInverso();
        long tMezclaPeor = vectorInverso.ordenarPorMezcla();
        matrizTiempos.asignaValor(1, 1, tMezclaPeor);
        //CASO MEDIO: 
        vectorAleatorio = new vectorOrdenable(nIteracciones);
        vectorAleatorio.rellenarAleatorio(nIteracciones);
        long tMezclaMedio = vectorAleatorio.ordenarPorMezcla(); 
        matrizTiempos.asignaValor(1, 2, tMezclaMedio);
        
        //SLECTION SORT
        //CASO MEJOR 
        vectorOrdenado = new vectorOrdenable(nIteracciones); 
        vectorOrdenado.rellenarArray();
        long tSeleccionMejor = vectorOrdenado.ordenarPorSeleccion();
        matrizTiempos.asignaValor(2, 0, tSeleccionMejor);
        //CASO PEOR
        vectorInverso = new vectorOrdenable(nIteracciones);
        vectorInverso.rellenarInverso();
        long tSeleccionPeor = vectorInverso.ordenarPorSeleccion(); 
        matrizTiempos.asignaValor(2, 1, tSeleccionPeor);
        //CASO MEDIO
        vectorAleatorio = new vectorOrdenable(nIteracciones);
        vectorAleatorio.rellenarAleatorio(nIteracciones);
        long tSeleccionMedio = vectorAleatorio.ordenarPorSeleccion();
        matrizTiempos.asignaValor(2, 2, tSeleccionMedio);
    

    }

    public void imprimeTiempos(){
        this.matrizTiempos.imprimeTiempos();
    }
    public void guardarDatos(String nombreArchivo){
        this.matrizTiempos.guardarTiempos(nombreArchivo);
    }
}
