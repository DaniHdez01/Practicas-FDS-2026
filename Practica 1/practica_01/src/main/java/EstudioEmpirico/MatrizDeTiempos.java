package EstudioEmpirico;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class MatrizDeTiempos {
    private int nFilas; 
    private int nColumnas; 
    private long [][] matriz; 
    private final String [] titulos = {"Burbuja", "Mezcla", "Selection Sort", "Caso mejor", "Caso peor", "Caso medio"};

    public MatrizDeTiempos (int nFilas, int nColumnas){
        this.nFilas = nFilas; 
        this.nColumnas = nColumnas; 
        this.matriz = new long[nFilas][nColumnas]; 
    }

    public void asignaValor(int i, int j, long valor){
        matriz[i][j] = valor;  
    }

    public void imprimeTiempos(){
        for(int i = 0; i<nColumnas; i++){
            for(int j = 0; j<nFilas; j++){
                System.out.println( "Posicion "+i + " " + j + " " + matriz[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public void guardarTiempos(String nombreArchivo){
        String rutaArchivo = nombreArchivo + ".csv"; 
         
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))){

            //Escribir títulos de Algoritmos 
            for(int i = 0; i<nColumnas ; i++){
                if (i==0){
                    bw.write(";"); 
                }
                bw.write(titulos[i] + ";"); 
            }
            bw.newLine();

            for(int i = 0; i<nColumnas; i++){
                for (int j = 0; j<nFilas; j++){
                    if(j == 0){
                        bw.write(titulos[i+3] + ";"); 
                    }
                    bw.write(String.valueOf(matriz[i][j])+"; "); 
                }
                bw.newLine(); 
            }

            System.out.println("Archivo cargado con éxito"); 
        } catch (IOException e){
            System.err.println("Error al cargar el archivo"); 
        }
    }
}
