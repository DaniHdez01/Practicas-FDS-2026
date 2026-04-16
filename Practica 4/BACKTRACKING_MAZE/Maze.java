public class Maze {


        private final int[][] grid;
        private final int startRow;
        private final int startCol;
        private final int endRow;
        private final int endCol;

        public Maze(int[][] grid, int startRow, int startCol, int endRow, int endCol) {
            this.grid = grid;
            this.startRow = startRow;
            this.startCol = startCol;
            this.endRow = endRow;
            this.endCol = endCol;
            this.grid[startRow][startCol] = CellType.START;
            this.grid[endRow][endCol] = CellType.END;
        }

        public int[][] getGrid() {
            return grid;
        }

        public int getStartRow() {
            return startRow;
        }

        public int getStartCol() {
            return startCol;
        }

        public int getEndRow() {
            return endRow;
        }

        public int getEndCol() {
            return endCol;
        }

        public int getRows() {
            return grid.length;
        }

        public int getCols() {
            return grid[0].length;
        }

        public void print() {
            System.out.println("--------------------");
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[row].length; col++) {
                    if (grid[row][col] == CellType.START) {
                        System.out.print("S ");
                    } else if (grid[row][col] == CellType.END) {
                        System.out.print("E ");
                    } else if (grid[row][col] == CellType.WALL) {
                        System.out.print("# ");
                    } else if (grid[row][col] == CellType.PATH) {
                        System.out.print("* ");
                    } else {
                        System.out.print(". ");
                    }
                }
                System.out.println();
            }
        }
    }