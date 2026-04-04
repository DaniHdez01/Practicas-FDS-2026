public class ChessBoard {

    private final int[][] grid;

    public ChessBoard(int rows, int cols) {
        validateDimensions(rows, cols);
        this.grid = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                this.grid[row][col] = 0;
            }
        }
    }

    public int[][] getGrid() {
        return grid;
    }

    public int getRows() {
        return grid.length;
    }

    public int getCols() {
        return grid[0].length;
    }

    public void print() {
        System.out.println("--------------------");
        int cellWidth = getCellWidth();

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                System.out.printf("%" + cellWidth + "d ", grid[row][col]);
            }
            System.out.println();
        }
    }

    private int getCellWidth() {
        int maxWidth = 1;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                int width = String.valueOf(grid[row][col]).length();
                if (width > maxWidth) {
                    maxWidth = width;
                }
            }
        }

        return maxWidth;
    }

    public int[] findCellWithValueOne() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 1) {
                    return new int[] { row, col };
                }
            }
        }
        return new int[] { -1, -1 };
    }

    private void validateDimensions(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Las dimensiones deben ser mayores que 0.");
        }
    }
}