/**
 * Representa un tablero de ajedrez con una cuadrícula de valores enteros.
 * Esta clase puede ser utilizada para modelar el tablero en problemas como el del Salto del Caballo.
 */

import java.util.Random;
public class ChessBoard {

    private final int[][] grid;

    
    public ChessBoard(int rows, int cols) {
        validateDimensions(rows, cols);
        this.grid = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                this.grid[row][col] = CellType.FREE;
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
    public void setStart(int x, int y){
        this.grid[x][y] = CellType.START; 
    }
    public void setEnd(int x, int y){
        this.grid[x][y] = CellType.END; 
    }
    public void setPath(int x, int y){
        this.grid[x][y] = CellType.PATH; 
    }
    public void setFree(int x, int y){
        this.grid[x][y] = CellType.FREE; 
    }
    public int getValue(int x, int y){
        return this.grid[x][y]; 
    }
    public int[] randomPos(){
        Random random = new Random(); 
        int x = random.nextInt(getRows()); 
        int y = random.nextInt(getCols()); 
        return new int[]{x, y}; 
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
                    // Devuelve  coordenadas si se encuentra una celda con valor 1
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