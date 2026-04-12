import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int rows = 6;
        int cols = 8;

        int startRow = 0;
        int startCol = 0;

        int endRow = rows - 1;
        int endCol = cols - 1;

        double wallProbability = 0.30;

        MazeGenerator generator = new MazeGenerator();
        Maze maze = generator.generate(
                rows,
                cols,
                wallProbability,
                startRow,
                startCol,
                endRow,
                endCol);

        System.out.println("Laberinto generado (S=inicio, E=fin, #=pared, .=camino):");
        maze.print();

        System.out.println("\nBuscando camino...");
        MazeSolver solver = new MazeSolver(maze);
        boolean pathExists = solver.PathExists();
        if (pathExists) {
            System.out.println("Se encontró un camino.");
            maze.print();
        } else {
            System.out.println("No se encontró un camino.");
        }

        /*
         * PARA HACER EL MAIN PARA EL ALGORITMO DEL CABALLO:
         * 1. Que haga un tablero de las dimensiones que quiere el usuario::
         * 1.a pedir por entrada dimensiones.
         * 1.b ChessBoard nombre = new ChessBoard(x,y) CREAR EL TABLERO
         * 1.c ChessKnightSolver solucion = new ChessKnightSolver (Tablero,
         * caminoAbierto (booleano))
         * 1.d Llamar función PathExists()
         * 1.e imprimir el tablero solucionado (En caso de encontrarlo)
         * HACER ESTO 2 VECES: Una con camino abierto y otra con cerrado
         * 2. Baterá de prebas:
         * Que cree tableros random y que genere un archivo csv en el que haya una tabla
         * con lo siguiente
         * Tamaño | Con camino abierto | Con camino cerrado | Tiempo para camino abierto
         * | Tiempo para camino cerrado
         * 
         */

        //ENTRADA DE USUARIO 
        Scanner scanner = new Scanner(System.in);

        System.out.println("PROBLEMA DEL CABALLO");
        System.out.print("Introucir numero de filas del tablero: ");
        int filasTablero = scanner.nextInt();
        System.out.print("Introducir numero de columnas del tablero: ");
        int columnasTablero = scanner.nextInt();

        
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
                boolean resAbierto = solAbierto.PathExists();
                long tiempoAb = System.currentTimeMillis() - t0;

                // Camino cerrado
                ChessBoard tabCerrado = new ChessBoard(f, c);
                ChessKnightSolver solCerrado = new ChessKnightSolver(tabCerrado, false);
                long t1 = System.currentTimeMillis();
                boolean resCerrado = solCerrado.PathExists();
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