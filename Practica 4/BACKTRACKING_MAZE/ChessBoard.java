/**
 * Representa un tablero de ajedrez con una cuadrícula de valores enteros.
 * Esta clase puede ser utilizada para modelar el tablero en problemas como el del Salto del Caballo.
 */
import CellType; 
import java.util.random.*;
public class ChessBoard {

    private final int[][] grid;

    /**
     * Constructor de un nuevo ChessBoard con las dimensiones especificadas.
     * La cuadrícula se inicializa con todas las celdas a 0.
     *
     * @param rows El número de filas del tablero de ajedrez.
     * @param cols El número de columnas del tablero de ajedrez.
     * @throws IllegalArgumentException si las filas o columnas no son mayores que 0.
     */
    public ChessBoard(int rows, int cols) {
        validateDimensions(rows, cols);
        this.grid = new int[rows][cols];

        // Inicializa todas las celdas de la cuadrícula a 0
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                this.grid[row][col] = CellType.FREE;
            }
        }
    }
    
    /**
     * Devuelve el array 2D subyacente que representa la cuadrícula del tablero de ajedrez.
     *
     * @return El array 2D de enteros de la cuadrícula.
     */
    public int[][] getGrid() {
        return grid;
    }
    
    /**
     * Devuelve el número de filas del tablero de ajedrez.
     *
     * @return El número de filas.
     */
    public int getRows() {
        return grid.length;
    }
    
    /**
     * Devuelve el número de columnas del tablero de ajedrez.
     *
     * @return El número de columnas.
     */
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
        int x = random.nextInt(getCols()); 
        int y = random.nextInt(getRows()); 
        return new int[]{x, y}; 
    }
    
    /**
     * Imprime el estado actual del tablero de ajedrez en la consola.
     * El valor de cada celda se formatea para asegurar un ancho de columna consistente.
     */
    public void print() {
        System.out.println("--------------------");
        // Determina el ancho máximo necesario para imprimir los valores de las celdas y alinear las columnas
        int cellWidth = getCellWidth();

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                // Imprime el valor de cada celda, justificado a la derecha con el ancho calculado
                System.out.printf("%" + cellWidth + "d ", grid[row][col]);
            }
            System.out.println();
        }
    }
    
    /**
     * Calcula el ancho máximo de caracteres requerido para imprimir cualquier valor de celda en la cuadrícula.
     * Esto se utiliza para formatear la salida en el método {@code print()}.
     *
     * @return El ancho máximo de caracteres de cualquier valor de celda.
     */
    private int getCellWidth() {
        int maxWidth = 1;

        // Itera a través de la cuadrícula para encontrar la representación de cadena de número más larga
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
    
    /**
     * Encuentra las coordenadas de la primera celda en la cuadrícula que tiene un valor de 1.
     * Esto podría usarse, por ejemplo, para localizar la posición inicial de un caballo.
     *
     * @return Un array de enteros {@code {fila, columna}} que representa las coordenadas de la celda con valor 1.
     *         Devuelve {@code {-1, -1}} si no se encuentra ninguna celda con ese valor.
     */
    public int[] findCellWithValueOne() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 1) {
                    // Devuelve las coordenadas si se encuentra una celda con valor 1
                    return new int[] { row, col };
                }
            }
        }
        // Devuelve {-1, -1} si no se encuentra ninguna celda con valor 1
        return new int[] { -1, -1 };
    }
    
    /**
     * Valida que las dimensiones dadas (filas y columnas) sean positivas.
     *
     * @param rows El número de filas.
     * @param cols El número de columnas.
     * @throws IllegalArgumentException si las filas o columnas son menores o iguales a 0.
     */
    private void validateDimensions(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Las dimensiones deben ser mayores que 0.");
        }
    }


}