
public class ChessKnightSolver {
 
    private final ChessBoard board;
    private final boolean openPath;
 
    
    private static final int[] movimiento_fila = { -2, -1, +1, +2, +2, +1, -1, -2 };
    private static final int[] movimiento_columnaumna = { +1, +2, +2, +1, -1, -2, -2, -1 };
 
    public ChessKnightSolver(ChessBoard board, boolean openPath) {
        if (board == null) {
            throw new IllegalArgumentException("El tablero no puede ser null.");
        }
        this.board = board;
        this.openPath = openPath;
    }
 
    public ChessBoard getBoard() {
        return board;
    }
 
    public boolean isOpenPath() {
        return openPath;
    }
 
    
    public boolean PathExists() {
        int totalCells = board.getRows() * board.getCols();
 
        board.getGrid()[0][0] = 1;
 
        boolean solucionado = findPath(0, 0, 2, totalCells);
 
        if (!solucionado) {
            board.getGrid()[0][0] = 0;
        }
 
        return solucionado;
    }
 
    
    private boolean findPath(int fila, int columna, int step, int totalCells) {
 
        if (step == totalCells + 1) {
            if (openPath) {
                return true;
            } else {
                return puedeLlegarInicio(fila, columna);
            }
        }
 
        boolean solucionado = false;
        for (int k = 0; k < 8 && !solucionado; k++) {
            int nuevaFila = fila + movimiento_fila[k];
            int nuevacolumnaumna = columna + movimiento_columnaumna[k];
 
            if (movimientoValido(nuevaFila, nuevacolumnaumna)) {
                board.getGrid()[nuevaFila][nuevacolumnaumna] = step;
 
                solucionado = findPath(nuevaFila, nuevacolumnaumna, step + 1, totalCells);
 
                if (!solucionado) {
                    board.getGrid()[nuevaFila][nuevacolumnaumna] = 0;
                }
            }
        }
 
        return solucionado;
    }
 
    
    private boolean movimientoValido(int fila, int columna) {
        return fila >= 0 && fila < board.getRows() && columna >= 0 && columna < board.getCols() && board.getGrid()[fila][columna] == 0;
    }
 
    
    private boolean puedeLlegarInicio(int fila, int columna) {
        for (int k = 0; k < 8; k++) {
            if (fila + movimiento_fila[k] == 0 && columna + movimiento_columnaumna[k] == 0) {
                return true;
            }
        }
        return false;
    }
}