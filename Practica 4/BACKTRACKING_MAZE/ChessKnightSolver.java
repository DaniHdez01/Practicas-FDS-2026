public class ChessKnightSolver {

    private final ChessBoard board;
    private final boolean openPath;

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
        boolean success = false;
        /// Implementar el algoritmo de búsqueda para encontrar un camino del caballo en el tablero.
        return success;
    }

}