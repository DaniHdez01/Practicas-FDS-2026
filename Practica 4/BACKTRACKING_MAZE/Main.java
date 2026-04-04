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

    }
}