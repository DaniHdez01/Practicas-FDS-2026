import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final int[][] tablas = {

            // Tabla 1, filas, culumnas, abierto/cerrado
            { 3, 3, 1 }, { 3, 3, 0 },
            { 4, 4, 1 }, { 4, 4, 0 },
            { 5, 5, 1 }, { 5, 5, 0 },
            { 6, 6, 1 }, { 6, 6, 0 },
            { 3, 10, 1 }, { 3, 10, 0 },
            { 3, 12, 1 }, { 3, 12, 0 },
            { 5, 6, 1 }, { 5, 6, 0 },
            { 3, 4, 1 }, { 3, 4, 0 },
            { 3, 5, 1 }, { 3, 5, 0 },
            { 3, 6, 1 }, { 3, 6, 0 },
            { 3, 8, 1 }, { 3, 8, 0 },
            { 4, 6, 1 }, { 4, 6, 0 },
            { 4, 3, 1 }, { 4, 3, 0 },
            { 5, 3, 1 }, { 5, 3, 0 },
            { 6, 3, 1 }, { 6, 3, 0 },
            { 8, 3, 1 }, { 8, 3, 0 },
            { 6, 4, 1 }, { 6, 4, 0 },
            { 6, 5, 1 }, { 6, 5, 0 },
            { 10, 3, 1 }, { 10, 3, 0 },
            { 12, 3, 1 }, { 12, 3, 0 },
            // Tabla 2, solo con tiempos pequeños
            { 8, 8, 1 }, { 8, 8, 0 },
            { 5, 8, 1 }, { 5, 8, 0 },
            { 6, 8, 1 }, { 6, 8, 0 },
            { 4, 8, 1 }, { 4, 8, 0 },
            { 8, 4, 1 }, { 8, 4, 0 },
            { 14, 3, 1 }, { 14, 3, 0 },

    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Selecciona una opción: ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    casoUno(scanner);
                    break;
                case "2":
                    casoDos(scanner);
                    break;
                case "3":
                    bateria(scanner);
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    System.out.println("opcion no valida");
            }
        }

        scanner.close();
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("1. Buscar una solución (introducir tablero por teclado)");
        System.out.println("2. Buscar todas las soluciones");
        System.out.println("3. Ejecutar batería de pruebas ");
        System.out.println("0. Salir");
    }

    private static void casoUno(Scanner scanner) {
        System.out.print("Introucir numero de filas del tablero: ");
        int filas = scanner.nextInt();
        System.out.print("Introucir numero de columnas del tablero: ");
        int columnas = scanner.nextInt();
        System.out.print("Introucir el tipo de camino(1 abierto, o cerrado) ");
        boolean open;
        int opcion = scanner.nextInt();
        open = opcion == 1;

        scanner.nextLine();

        ChessBoard board = new ChessBoard(filas, columnas);
        ChessKnightSolver solver = new ChessKnightSolver(board, open);

        System.out.println("\nBuscando solución para tablero " + filas + "x" + columnas
                + " (" + (open ? "abierto" : "cerrado") + ")...");

        long start = System.currentTimeMillis();
        boolean found = solver.PathExists();
        long tiempo = System.currentTimeMillis() - start;

        System.out.println("Resultado: " + (found ? "SOLUCIÓN ENCONTRADA" : "NO HAY SOLUCIÓN"));
        System.out.println("Tiempo: " + tiempo + " ms");
        board.print();
    }

    private static void casoDos(Scanner scanner) {
        System.out.print("Introucir numero de filas del tablero: ");
        int filas = scanner.nextInt();
        System.out.print("Introucir numero de columnas del tablero: ");
        int columnas = scanner.nextInt();
        System.out.print("Introucir el tipo de camino(1 abierto, o cerrado) ");

        int opcion = scanner.nextInt();
        boolean open = opcion == 1;

        scanner.nextLine();

        System.out.println("Buscando todas las soluciones para " + filas + "x" + columnas
                + " (" + (open ? "abierto" : "cerrado") + ")...");

        ChessBoard board = new ChessBoard(filas, columnas);
        ChessKnightSolverAll solver = new ChessKnightSolverAll(board, open);

        long start = System.currentTimeMillis();
        int count = solver.findAllPaths();
        long tiempo = System.currentTimeMillis() - start;

        System.out.println("Número de soluciones encontradas: " + count);
        System.out.println("Tiempo: " + tiempo + " ms");

        if (count == 0) {
            return;
        }

        System.out.print("¿Imprimir soluciones por pantalla? (s/n): ");
        boolean imprimir = scanner.nextLine().trim().equalsIgnoreCase("s");

        if (imprimir) {
            imprimirTodasSoluciones(solver.getSolutions(), System.out);
        }

        System.out.print("¿Guardar soluciones en fichero? (s/n): ");
        boolean guardar = scanner.nextLine().trim().equalsIgnoreCase("s");

        if (guardar) {
            String archivo = "soluciones_" + filas + "x" + columnas
                    + "_" + (open ? "abierto" : "cerrado") + ".txt";

            try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
                imprimirTodasSoluciones(solver.getSolutions(), pw);
                System.out.println("Guardado en: " + archivo);

            } catch (IOException e) {
                System.err.println("Error al guardar el fichero: " + e.getMessage());
            }
        }

    }

    private static void imprimirTodasSoluciones(List<int[][]> solutions, java.io.PrintStream out) {
        for (int[][] grid : solutions) {
            out.println("\n------");
            printGrid(grid, out);
        }
    }

    private static void imprimirTodasSoluciones(List<int[][]> solutions, PrintWriter pw) {
        for (int[][] grid : solutions) {
            pw.println("\n------");
            printGrid(grid, pw);
        }
    }

    private static void bateria(Scanner scanner) {
        System.out.println("\nBATERÍA DE PRUEBAS");

        String archivo = "resultados_bateria.txt";
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {

            for (int[] conjunto : tablas) {
                int filas = conjunto[0];
                int columnas = conjunto[1];
                boolean open = conjunto[2] == 1;

                ChessBoard board = new ChessBoard(filas, columnas);
                ChessKnightSolver solver = new ChessKnightSolver(board, open);

                System.out.print("Probando " + filas + "x" + columnas
                        + " " + (open ? "ABIERTO" : "CERRADO") + "... ");

                long start = System.currentTimeMillis();
                boolean found = solver.PathExists();
                long tiempo = System.currentTimeMillis() - start;

                String resultado = found ? "SÍ" : "NO";
                System.out.println(resultado + " (" + tiempo + " ms)");

                pw.printf(
                        filas + "x" + columnas,
                        open ? "ABIERTO" : "CERRADO",
                        resultado,
                        tiempo);

                pw.println("Tablero:");
                printGrid(board.getGrid(), pw);
                pw.println();
            }

            System.out.println("\nResultados guardados en: " + archivo);

        } catch (IOException e) {
            System.err.println("Error al crear el fichero: " + e.getMessage());
        }
    }

    private static void printGrid(int[][] grid, java.io.PrintStream out) {
        int cellWidth = getCellWidth(grid);
        for (int[] fila : grid) {
            for (int val : fila) {
                out.printf("%" + cellWidth + "d ", val);
            }
            out.println();
        }
    }

    private static void printGrid(int[][] grid, PrintWriter pw) {
        int cellWidth = getCellWidth(grid);
        for (int[] fila : grid) {
            for (int val : fila) {
                pw.printf("%" + cellWidth + "d ", val);
            }
            pw.println();
        }
    }

    private static int getCellWidth(int[][] grid) {
        int max = 1;
        for (int[] fila : grid) {
            for (int val : fila) {
                int w = String.valueOf(val).length();
                if (w > max)
                    max = w;
            }
        }
        return max;
    }
}