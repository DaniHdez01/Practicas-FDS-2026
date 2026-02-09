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
        this.matrizTiempos = new MatrizDeTiempos(3, 12); 
    }
    public void ejecutarAlgoritmos(){


        vectorOrdenado.rellenarArray();
        vectorOrdenado.imprimirVector();
        vectorInverso.rellenarInverso();
        vectorAleatorio.rellenarAleatorio(nIteracciones);

        vectorOrdenado.ordenarPorBurbuja();
        vectorOrdenado.ordenarPorMezcla();
        vectorOrdenado.ordenarPorSeleccion();

        vectorInverso.ordenarPorBurbuja();
        vectorInverso.ordenarPorMezcla();
        vectorInverso.ordenarPorSeleccion();

        vectorAleatorio.ordenarPorBurbuja();
        vectorAleatorio.ordenarPorMezcla();
        vectorAleatorio.ordenarPorSeleccion();

    }

    public void imprimeTiempos(){
        this.matrizTiempos.imprimeTiempos();
    }
    public void guardarDatos(String nombreArchivo){
        this.matrizTiempos.guardarTiempos(nombreArchivo);
    }
}
