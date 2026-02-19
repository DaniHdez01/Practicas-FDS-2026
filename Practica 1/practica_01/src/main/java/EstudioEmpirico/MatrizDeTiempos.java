package EstudioEmpirico;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class MatrizDeTiempos {
    private int nFilas; 
    private int nColumnas; 
    private long [][] matriz; 
    private final String [] titulos = {"Burbuja", "Mezcla", "SelectionSort", "Caso mejor", "Caso peor", "Caso medio"};

    public MatrizDeTiempos (int nFilas, int nColumnas){
        this.nFilas = nFilas; 
        this.nColumnas = nColumnas; 
        this.matriz = new long[nFilas][nColumnas]; 
    }

    public void asignaValor(int i, int j, long valor){
        matriz[i][j] = valor;  
    }

    public void imprimeTiempos(){
        for(int i = 0; i<nFilas; i++){
            for(int j = 0; j<nColumnas; j++){
                System.out.println( "Posicion "+i + " " + j + " " + matriz[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public void guardarTiempos(String nombreArchivo){
        String rutaArchivo = nombreArchivo + ".csv"; 
         
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))){

            bw.write(";");
            for(int j = 0; j < nColumnas; j++){
             
                bw.write(titulos[j + 3] + ";");
            }
            bw.newLine();

         
            for(int i = 0; i<nFilas; i++){
              
                bw.write(titulos[i] + ";");
                for (int j = 0; j<nColumnas; j++){
                    bw.write(String.valueOf(matriz[i][j])+"; "); 
                }
                bw.newLine(); 
            }

            System.out.println("Archivo cargado con éxito"); 
        } catch (IOException e) {
            System.err.println("Error al cargar el archivo"); 
        }
    }
}
