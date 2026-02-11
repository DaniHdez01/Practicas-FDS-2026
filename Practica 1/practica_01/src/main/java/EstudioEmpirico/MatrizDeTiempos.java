package EstudioEmpirico;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class MatrizDeTiempos {
    private int nFilas; 
    private int nColumnas; 
    private long [][] matriz; 
    private String [] titulos;

    public MatrizDeTiempos (int nFilas, int nColumnas){
        this.nFilas = nFilas; 
        this.nColumnas = nColumnas; 
        this.matriz = new long[nFilas][nColumnas]; 
        this.titulos = new String[nColumnas]; 
    }

    public void asignaValor(int i, int j, long valor){
        matriz[i][j] = valor;  
    }

    public void imprimeTiempos(){
        for(int i = 0; i<nColumnas; i++){
            for(int j = 0; j<nFilas; j++){
                System.out.println(matriz[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public void guardarTiempos(String nombreArchivo){
        String rutaArchivo = nombreArchivo + ".csv"; 
         
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))){

            //Escribir títulos 
            for(int i = 0; i<nColumnas ; i++){
                bw.write(titulos[i] + ";"); 
            }
            bw.newLine();; 

            for(int i = 0; i<nColumnas; i++){
                for (int j = 0; j<nFilas; j++){
                    bw.write(String.valueOf(matriz[i][j])+","); 
                }
                bw.newLine(); 
            }

            System.out.println("Archivo cargado con éxito"); 
        } catch (IOException e){
            System.err.println("Error al cargar el archivo"); 
        }
    }
}
