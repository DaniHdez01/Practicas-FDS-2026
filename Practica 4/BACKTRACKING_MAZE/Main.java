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
        int opcion = scanner.nextInt(); 
         Pruebas prueba = new Pruebas();  
            if (opcion == 1){
                System.out.print("Introucir numero de filas del tablero: ");
                int filasTablero = scanner.nextInt();
                System.out.print("Introducir numero de columnas del tablero: ");
                int columnasTablero = scanner.nextInt();
                System.out.println("Opción 1: Camino abierto"); 
                System.out.println("Opción 2: Camino cerrado"); 
                int optdos = scanner.nextInt(); 
                if (optdos == 1){ prueba.Camino_abierto(filasTablero, columnasTablero); }
                else if (optdos == 2) { prueba.Camino_cerrado(filasTablero, columnasTablero);}
                else{System.out.println("Opción no válida"); } 
            } else if (opcion == 2){
                System.out.print("Generando CSV con la batería de pruebas");
                prueba.bateria_pruebas();
        } else {
            System.out.println("Opción no válida");
        }
        scanner.close(); 
}
}