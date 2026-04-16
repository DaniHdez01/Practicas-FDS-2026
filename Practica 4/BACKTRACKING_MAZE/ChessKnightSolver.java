
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

    public boolean openPathExists() {
        boolean success = false;
        int [][] movements = {{1, 2}, 
        {2, 1}, 
        {-1, 2}, 
        {2, -1}, 
        {1, -2}, 
        {-1, -2}, 
        {-2, -1}, 
        {-2, 1}}; 
        int [] pos = this.board.randomPos(); 
        this.board.setStart(pos[0], pos[1]); 
        int [] end = this.board.randomPos();
        this.board.setEnd(end[0], end[1]); 
        success = findPath(pos, movements, 0); 
        return success;
    }
      public boolean closePathExists() {
        boolean success = false;
        int [][] movements = {{1, 2}, 
        {2, 1}, 
        {-1, 2}, 
        {2, -1}, 
        {1, -2}, 
        {-1, -2}, 
        {-2, -1}, 
        {-2, 1}}; 
        int [] pos = this.board.randomPos(); 
        this.board.setStart(pos[0], pos[1]); 
        int [] end = pos;
        this.board.setEnd(end[0], end[1]); 
        success = findPath(pos, movements, 0); 
        return success;
    }
    //Función principal para backtracking. 
    private boolean findPath(int[] pos, int[][] movements, int steps) {

        //Paso 1: comprobamos si la casilla es meta
        if (this.board.getValue(pos[0], pos[1]) == CellType.END && steps > 0) {
            return true; 
        } 
        //Si no es meta: Paso 2: comprobar las posibles opciones 
        else {

            for (int i = 0; i < movements.length; i++) {
                int nextX = pos[0] + movements[i][0]; 
                int nextY = pos[1] + movements[i][1]; 
                //Si la opción es válida: Paso 3 actualizar posición y llamar a la función
                if (nextX >= 0 && nextX < board.getRows() && nextY >= 0 && nextY < board.getCols()){
                    if(this.board.getValue(nextX, nextY) == CellType.FREE || this.board.getValue(nextX, nextY) == CellType.END){
                    if (this.board.getValue(nextX, nextY)== CellType.FREE) {
                        this.board.setPath(nextX, nextY); 
                }

                if (findPath(new int[]{nextX, nextY}, movements, steps + 1)) { 
                    return true; 
                }


                if (this.board.getValue(nextX, nextY) == CellType.FREE) {
                    this.board.setFree(nextX, nextY);
                }
                    }
                   
                }
        }
            return false; 
        }
    }
}