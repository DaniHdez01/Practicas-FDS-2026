package EstudioEmpirico;

public class Complejidad {
    private int nIteracciones; 
    private vectorOrdenable vectorOrdenado; 
    private vectorOrdenable vectorInverso; 
    private vectorOrdenable vectorAleatorio; 
    private MatrizDeTiempos matrizTiempos; 

    public Complejidad(int n){
        this.nIteracciones = n;
        this.matrizTiempos = new MatrizDeTiempos(3, 3); 
    }
    public void ejecutarAlgoritmos(){

        //BURBUJA
        //CASO MEJOR
        
        matrizTiempos.asignaValor(0, 0, vectorOrdenado.ordenarPorBurbuja()); 
        vectorOrdenado.rellenarArray();
        
        //CASO PEOR
        matrizTiempos.asignaValor(1, 0, vectorInverso.ordenarPorBurbuja());
        vectorInverso.rellenarInverso();

        //CASO MEDIO
        matrizTiempos.asignaValor(2, 0, vectorAleatorio.ordenarPorBurbuja());
        vectorAleatorio.rellenarAleatorio(nIteracciones);


        //MEZCLA 
        //CASO MEJOR: 
        matrizTiempos.asignaValor(0, 1,vectorOrdenado.ordenarPorMezcla());
        vectorOrdenado.rellenarArray();

        //CASO PEOR:
        matrizTiempos.asignaValor(1, 1, vectorInverso.ordenarPorMezcla());
        vectorInverso.rellenarInverso();
        
        //CASO MEDIO: 
        matrizTiempos.asignaValor(2, 1, vectorAleatorio.ordenarPorMezcla());
        vectorAleatorio.rellenarAleatorio(nIteracciones);
        
        //SLECTION SORT
        //CASO MEJOR 
        matrizTiempos.asignaValor(0, 2, vectorOrdenado.ordenarPorSeleccion());
        //CASO PEOR
        matrizTiempos.asignaValor(1, 2,vectorInverso.ordenarPorSeleccion());
        //CASO MEDIO
        matrizTiempos.asignaValor(2, 2, vectorAleatorio.ordenarPorSeleccion());
    

    }

    public void imprimeTiempos(){
        this.matrizTiempos.imprimeTiempos();
    }
    public void guardarDatos(String nombreArchivo){
        this.matrizTiempos.guardarTiempos(nombreArchivo);
    }
}
