import java.util.ArrayList;
import java.util.List;
 
public class ChessKnightSolverAll {
 
    private final ChessBoard board;
    private final boolean openPath;
    private final List<int[][]> soluciones;
 
    private static final int[] movimiento_fila = { -2, -1, +1, +2, +2, +1, -1, -2 };
    private static final int[] movimiento_columna = { +1, +2, +2, +1, -1, -2, -2, -1 };
 
    public ChessKnightSolverAll(ChessBoard board, boolean openPath) {
        if (board == null) {
            throw new IllegalArgumentException("El tablero no puede ser null.");
        }
        this.board = board;
        this.openPath = openPath;
        this.soluciones = new ArrayList<>();
    }
 
    public ChessBoard getBoard() {
        return board;
    }
 
    public boolean isOpenPath() {
        return openPath;
    }
 
    public List<int[][]> getSolutions() {
        return soluciones;
    }
 
    
    public int findAllPaths() {
        soluciones.clear();
        int totalCells = board.getRows() * board.getCols();
 
        board.getGrid()[0][0] = 1;
 
        findAllPaths(0, 0, 2, totalCells);
 
        board.getGrid()[0][0] = 0;
 
        return soluciones.size();
    }
 
    
    private void findAllPaths(int fila, int columna, int step, int totalCells) {
 
        if (step == totalCells + 1) {
            boolean valid = openPath || puedeLlegarInicio(fila, columna);
            if (valid) {
                soluciones.add(copyGrid());
            }
            return; 
        }
 
        for (int k = 0; k < 8; k++) {
            int nuevaFila = fila + movimiento_fila[k];
            int nuevaColumna = columna + movimiento_columna[k];
 
            if (movimientoValido(nuevaFila, nuevaColumna)) {
                board.getGrid()[nuevaFila][nuevaColumna] = step;
 
                findAllPaths(nuevaFila, nuevaColumna, step + 1, totalCells);
 
                board.getGrid()[nuevaFila][nuevaColumna] = 0;
            }
        }
    }
 
    private boolean movimientoValido(int fila, int columna) {
        return fila >= 0 && fila < board.getRows()
            && columna >= 0 && columna < board.getCols()
            && board.getGrid()[fila][columna] == 0;
    }
 
    private boolean puedeLlegarInicio(int fila, int columna) {
        for (int k = 0; k < 8; k++) {
            if (fila + movimiento_fila[k] == 0 && columna + movimiento_columna[k] == 0) {
                return true;
            }
        }
        return false;
    }
 
    
    private int[][] copyGrid() {
        int filas = board.getRows();
        int columnas = board.getCols();
        int[][] copia = new int[filas][columnas];
        for (int r = 0; r < filas; r++) {
            System.arraycopy(board.getGrid()[r], 0, copia[r], 0, columnas);
        }
        return copia;
    }
}
