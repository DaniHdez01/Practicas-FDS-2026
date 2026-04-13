import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Pruebas {
    

    public long Camino_abierto(int filasTablero, int columnasTablero) {

           // camino abierto
        ChessBoard tablero = new ChessBoard(filasTablero, columnasTablero);

        ChessKnightSolver solverAbierto = new ChessKnightSolver(tablero, true);

        System.out.println("Camino abierto");
        long inicioAbierto = System.currentTimeMillis();
        boolean encontradoAbierto = solverAbierto.openPathExists();
        long tiempoAbierto = System.currentTimeMillis() - inicioAbierto;

        if (encontradoAbierto) {
            System.out.println("Solución encontrada en " + tiempoAbierto + " ms");
        } else {
            System.out.println("No se encontró solución en " + tiempoAbierto + " ms");
        }
        tablero.print();

        return tiempoAbierto;
        
    }
        
    public long Camino_cerrado(int filasTablero, int columnasTablero) {
            // camino cerrado
        ChessBoard tableroCerrado = new ChessBoard(filasTablero, columnasTablero);

        ChessKnightSolver solverCerrado = new ChessKnightSolver(tableroCerrado, false);

        System.out.println("Camino cerrado");
        long inicioCerrado = System.currentTimeMillis();
        boolean encontradoCerrado = solverCerrado.closePathExists();
        long tiempoCerrado = System.currentTimeMillis() - inicioCerrado;

        if (encontradoCerrado) {
            System.out.println("Solucion encontrada en " + tiempoCerrado + " ms");
        } else {
            System.out.println("No se encontro solucion en " + tiempoCerrado + " ms");
        }
        tableroCerrado.print();

        scanner.close();

        return tiempoCerrado;
    }

    public void bateria_pruebas(){
        // Tamaños de tableros a probar
        int[][] tamaños = { { 3, 3 }, { 4, 4 }, { 5, 5 }, { 6, 6 }, {3,10}, {3,12}, {5,6}, {3,4}, {3,5}, {3,6}, {3,8}, {4,6}, {4,3}, {5,3}, {6,3}, {8,3}, {6,4}, {6,5}, {10,3}, {12,3} };

        try (PrintWriter pw = new PrintWriter(new FileWriter("bateria_pruebas.csv"))) {

            // Cabecera del CSV
            pw.println("Tamaño,Camino abierto,Camino cerrado,Tiempo abierto (ms),Tiempo cerrado (ms)");

            for (int[] tamaño : tamaños) {
                int f = tamaño[0];
                int c = tamaño[1];

                // Camino abierto
                ChessBoard tableroAbierto = new ChessBoard(f, c);
                ChessKnightSolver solAbierto = new ChessKnightSolver(tableroAbierto, true);
                long t0 = System.currentTimeMillis();
                boolean resAbierto = solAbierto.openPathExists();
                long tiempoAb = System.currentTimeMillis() - t0;

                // Camino cerrado
                ChessBoard tabCerrado = new ChessBoard(f, c);
                ChessKnightSolver solCerrado = new ChessKnightSolver(tabCerrado, false);
                long t1 = System.currentTimeMillis();
                boolean resCerrado = solCerrado.closePathExists();
                long tiempoCe = System.currentTimeMillis() - t1;

                // Escribir fila en el CSV
                pw.println(f + "x" + c + ","+ (resAbierto ? "SÍ" : "NO") + ","+ (resCerrado ? "SÍ" : "NO") + ","+ tiempoAb + ","+ tiempoCe);

                System.out.println("Resuelta prueba " + f + "x" + c);
            }

            

        } catch (IOException e) {
            System.err.println("Error al escribir el CSV: " + e.getMessage());
        }
    }
        
}
