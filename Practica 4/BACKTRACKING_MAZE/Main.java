import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {      

        //ENTRADA DE USUARIO 
        Scanner scanner = new Scanner(System.in);

        System.out.println("PROBLEMA DEL CABALLO");
        System.out.println("Opción 1: Prueba unitaria: ");
        System.out.println("Opción 2: Generar batería de pruebas: "); 
        System.out.println("Cualquier otra tecla que se introduzca se usará para salir del programa"); 
        int opcion = scaner.nextInt();
         Pruebas prueba = new Pruebas();  
            if (opcion == 1){
            System.out.print("Introucir numero de filas del tablero: ");
            int filasTablero = scanner.nextInt();
            System.out.print("Introducir numero de columnas del tablero: ");
            int columnasTablero = scanner.nextInt();
            prueba.Camino_abierto(filasTablero, columnasTablero); 
            prueba.Camino_cerrado(filasTablero, columnasTablero); 
            } else if (opcion == 2){
            System.out.print("Generando CSV con la batería de pruebas");
            prueba.bateria_pruebas();
        } else {
            break; 
        }
}
}