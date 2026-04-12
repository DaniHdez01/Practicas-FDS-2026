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
        int [][] movements = {{1, 2}, 
        {2, 1}, 
        {-1, 2}, 
        {2, -1}, 
        {1, -2}, 
        {-1, -2}, 
        {-2, -1}}; 
        this.board.setValue(0, 0); 
        int[] pos = this.board.findCellWithValueOne(); 
        success = findPath(pos, movements, 0); 
        return success;
    }
    //Función principal para backtracking. LLAMAR EN EL MAIN A LA HORA DE EJECUTAR EL ALGORTIMO 
    private boolean findPath(int [] pos, int [][]movements, int steps){
        this.board.setValue(pos[0],pos[1]); //Paso 1: damos como visitada la casilla actual
        // Paso 2: comprobamos si el estado actual es solución: 
        //Para cada posible solución 
        for(int i = 0; i<movements.length; i++){

        }

    }

}