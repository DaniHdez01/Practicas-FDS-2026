import java.util.Random;

public class MazeGenerator {

    private final Random random;

    public MazeGenerator() {
        this.random = new Random();
    }

    public MazeGenerator(long seed) {
        this.random = new Random(seed);
    }

    public Maze generate(int rows,
                         int cols,
                         double wallProbability,
                         int startRow,
                         int startCol,
                         int endRow,
                         int endCol) {

        validateDimensions(rows, cols);
        validateProbability(wallProbability);
        validateCoordinates(rows, cols, startRow, startCol, "inicio");
        validateCoordinates(rows, cols, endRow, endCol, "fin");

        int[][] grid = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                grid[row][col] = random.nextDouble() < wallProbability ? CellType.WALL : CellType.FREE;
            }
        }

        grid[startRow][startCol] = CellType.FREE;
        grid[endRow][endCol] = CellType.FREE;

        return new Maze(grid, startRow, startCol, endRow, endCol);
    }

    private void validateDimensions(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Las dimensiones deben ser mayores que 0.");
        }
    }

    private void validateProbability(double wallProbability) {
        if (wallProbability < 0.0 || wallProbability > 1.0) {
            throw new IllegalArgumentException("wallProbability debe estar entre 0.0 y 1.0.");
        }
    }

    private void validateCoordinates(int rows, int cols, int row, int col, String label) {
        if (row < 0 || row >= rows || col < 0 || col >= cols) {
            throw new IllegalArgumentException(
                    "La casilla de " + label + " está fuera de la matriz.");
        }
    }

    
}