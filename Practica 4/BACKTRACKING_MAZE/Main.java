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
            PARA HACER EL MAIN PARA EL ALGORITMO DEL CABALLO: 
            1. Que haga un tablero de las dimensiones que quiere el usuario:: 
                1.a pedir por entrada dimensiones. 
                1.b ChessBoard nombre = new ChessBoard(x,y) CREAR EL TABLERO 
                1.c ChessKnightSolver solucion = new ChessKnightSolver (Tablero, caminoAbierto (booleano))
                1.d Llamar función PathExists()
                1.e imprimir el tablero solucionado (En caso de encontrarlo)
                HACER ESTO 2 VECES: Una con camino abierto y otra con cerrado
            2. Baterá de prebas: 
                Que cree tableros random y que genere un archivo csv en el que haya una tabla con lo siguiente 
                    Tamaño | Con camino abierto | Con camino cerrado | Tiempo para camino abierto | Tiempo para camino cerrado 

         */

    }
}