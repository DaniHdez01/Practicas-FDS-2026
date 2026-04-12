import java.util.Scanner;

public class Pruebas {
    

        public long Camino_abierto(int filasTablero, int columnasTablero) {

           // camino abierto
        ChessBoard tablero = new ChessBoard(filasTablero, columnasTablero);

        ChessKnightSolver solverAbierto = new ChessKnightSolver(tablero, true);

        System.out.println("Camino abierto");
        long inicioAbierto = System.currentTimeMillis();
        boolean encontradoAbierto = solverAbierto.PathExists();
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
        boolean encontradoCerrado = solverCerrado.PathExists();
        long tiempoCerrado = System.currentTimeMillis() - inicioCerrado;

        if (encontradoCerrado) {
            System.out.println("Solucion encontrada en " + tiempoCerrado + " ms");
        } else {
            System.out.println("No se encontro solucion en " + tiempoCerrado + " ms");
        }
        tableroCerrado.print();

        scanner.close();
        }
        
}
