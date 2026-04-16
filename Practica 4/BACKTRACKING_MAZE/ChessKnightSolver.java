
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
        {-2, -1}}; 
        int [] pos = this.board.randomPos(); 
        this.board.setStart(pos[0], pos[1]); 
        int [] end = this.board.randomPos();
        this.board.setEnd(end[0], end[1]); 
        success = findPath(pos, movements); 
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
        {-2, -1}}; 
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
        if (this.board.getValue(pos[0], pos[1]) == 8 && steps > 0) {
            return true; 
        } 
        //Si no es meta: Paso 2: comprobar las posibles opciones 
        else {
            boolean hayCamino = false; 
            for (int i = 0; i < movements.length; i++) {
                int nextX = pos[0] + movements[i][0]; 
                int nextY = pos[1] + movements[i][1]; 
                //Si la opción es válida: Paso 3 actualizar posición y llamar a la función
                if (nextX <= board.getCols() && nextY <= board.getRows() && board.getValue(nextX, nextY) == 0){
                    hayCamino = true; 
                    pos[0] += movements[i][0]; 
                    pos[1] += movements[i][1]; 
                    this.board.setPath(pos[0], pos[1]);
                    findPath(pos, movements, steps++); 

                    //Paso 4: Deshacer la opción 
                    this.board.setFree(pos[0], pos[1]);
                }
        }
        //Paso 5: En caso de no tener camino devuelve false 
            return hayCamino; 
        }
    }
}