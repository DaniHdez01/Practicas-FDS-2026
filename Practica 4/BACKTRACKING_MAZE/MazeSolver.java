public class MazeSolver {

    private final Maze maze;

    public MazeSolver(Maze maze) {
        if (maze == null) {
            throw new IllegalArgumentException("El laberinto no puede ser null.");
        }
        this.maze = maze;
    }

    public Maze getMaze() {
        return maze;
    }

    public boolean PathExists() {
        int[] incrementRow = { 0, 1, 0, -1 };
        int[] incrementCol = { 1, 0, -1, 0 };

        boolean success = findPath(maze.getStartRow(), maze.getStartCol(), incrementRow, incrementCol);
        return success;

    }

    private boolean findPath(int row, int col, int[] incrementRow, int[] incrementCol) {

        boolean success = false;
        for (int k = 0; k < 4 && !success; k++) {
            int newRow = row + incrementRow[k];
            int newCol = col + incrementCol[k];

            if (newRow >= 0 && newRow < maze.getRows() && newCol >= 0 && newCol < maze.getCols()) {
                if (maze.getGrid()[newRow][newCol] == CellType.END) {
                    return true;
                } else if (maze.getGrid()[newRow][newCol] == CellType.FREE) {
                    maze.getGrid()[newRow][newCol] = CellType.PATH; // Marcar como visitado
                    success = findPath(newRow, newCol, incrementRow, incrementCol);
                    if (!success) {
                        maze.getGrid()[newRow][newCol] = CellType.FREE; // Desmarcar para backtracking
                    }
                }
            }
        }
        return success;
    }
}