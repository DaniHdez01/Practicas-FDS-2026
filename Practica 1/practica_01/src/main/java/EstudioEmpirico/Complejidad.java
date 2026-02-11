package EstudioEmpirico;

public class Complejidad {
    private int nIteracciones; 
    private vectorOrdenable vectorOrdenado; 
    private vectorOrdenable vectorInverso; 
    private vectorOrdenable vectorAleatorio; 
    private MatrizDeTiempos matrizTiempos; 

    public Complejidad(int n){
        this.nIteracciones = n;
        this.vectorOrdenado = new vectorOrdenable(nIteracciones);  
        this.vectorInverso = new vectorOrdenable(nIteracciones); 
        this.vectorAleatorio = new vectorOrdenable(nIteracciones); 
        this.matrizTiempos = new MatrizDeTiempos(3, 3); 
    }
    public void ejecutarAlgoritmos(){


        vectorOrdenado.rellenarArray();
        vectorInverso.rellenarInverso();
        vectorAleatorio.rellenarAleatorio(nIteracciones);

        matrizTiempos.asignaValor(0, 0, vectorOrdenado.ordenarPorBurbuja()); 
        matrizTiempos.asignaValor(0, 1,vectorOrdenado.ordenarPorMezcla());
        matrizTiempos.asignaValor(0, 2, vectorOrdenado.ordenarPorSeleccion());

        matrizTiempos.asignaValor(1, 0, vectorInverso.ordenarPorBurbuja());
        matrizTiempos.asignaValor(1, 1, vectorInverso.ordenarPorMezcla());
        matrizTiempos.asignaValor(1, 2,vectorInverso.ordenarPorSeleccion());

        matrizTiempos.asignaValor(2, 0, vectorAleatorio.ordenarPorBurbuja());
        matrizTiempos.asignaValor(2, 1, vectorAleatorio.ordenarPorMezcla());
        matrizTiempos.asignaValor(2, 2, vectorAleatorio.ordenarPorSeleccion());

    }

    public void imprimeTiempos(){
        this.matrizTiempos.imprimeTiempos();
    }
    public void guardarDatos(String nombreArchivo){
        this.matrizTiempos.guardarTiempos(nombreArchivo);
    }
}
