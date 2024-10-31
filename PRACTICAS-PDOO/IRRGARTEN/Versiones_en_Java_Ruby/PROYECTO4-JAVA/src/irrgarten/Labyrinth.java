/**
 * Esta clase representa el laberinto.
 * @file Labyrinth.java
 * @author angel_rodriguez
 */

package irrgarten;

import irrgarten.enums.Orientation;
import irrgarten.enums.Directions;
import java.util.ArrayList;
import java.util.List;

public class Labyrinth {
    //Atributos de clase privados.
        // Estos atributos son caracteres que representan el estado de cada casilla del laberinto.
    private static final char BLOCK_CHAR = 'X';     // ‘X’= obstáculo
    private static final char EMPTY_CHAR = '-';     // '-'= casilla vacía
    private static final char MONSTER_CHAR = 'M';   // 'M'= monstruo
    private static final char COMBAT_CHAR = 'C';    // 'C'= combate entre jugador y monstruo.
    private static final char EXIT_CHAR = 'E';      // 'E'= casilla de salida.
    private static final int ROW = 0;
    private static final int COL = 1;

    //Atributos de instancia privados.
    private int nRows;              // Número de filas del laberinto.
    private int nCols;              // Número de columnas del laberinto.
    private int exitRow;            // Fila de la casilla de salida.
    private int exitCol;            // Columna de la casilla de salida.
    
    //Atributos de relación privados.
    private static Monster[][] monsterGrid;
    private static Player[][] playerGrid;
    private static char[][] characterGrid;

    //Métodos de instancia públicos.
    
    /**
     * Constructor paramétrico.
     * @param nRows         // Número de filas del laberinto.
     * @param nCols         // Número de columnas del laberinto.
     * @param exitRow       // Número de fila de la casilla de salida.
     * @param exitCol       // Número de columna de la casilla de salida.
     * @param Monster[][]   // 
     * @param char[][]      //
     * @param Player[][]    //
     */
    public Labyrinth(int nRows, int nCols, int exitRow, int exitCol) 
    {
        this.nRows = nRows;
        this.nCols = nCols;
        this.exitRow = exitRow;
        this.exitCol = exitCol;
        monsterGrid = new Monster[nRows][nCols];
        playerGrid = new Player[nRows][nCols];
        characterGrid = new char[nRows][nCols];
        // Inicializo la matriz de char con '-' casillas vacías y añado la casilla de salida.
        for(int i = 0; i < nRows; i++)   
            for(int j = 0; j < nCols; j++)
                characterGrid[i][j] = EMPTY_CHAR;
        characterGrid[exitRow][exitCol] = EXIT_CHAR;
    }   
    
    /**
     * Constructor sin parámetros.
     */
    public Labyrinth(){
        this(0, 0, 0, 0);
    }
    
    /**
     * Constructor por copia
     * @param l objeto de tipo Labyrinth
     */
    public Labyrinth(Labyrinth l){
        this(l.nRows, l.nCols, l.exitRow, l.exitCol);
    }
    
    /**
     * Pone los juagdores en el tablero, de forma aleatoria, para ello utiliza
     * las funciones: Player.randomEmptyPos() y Player.putPlayer2D.
     * @param players ArrayList de Player
     */
    public void spreadPlayers(ArrayList<Player> players){
        for(Player p: players){
            int[] pos = randomEmptyPos();
            putPlayer2D(-1, -1, pos[ROW], pos[COL], p);
        }
    }
    
    /**
     * Devuelve si hay un ganador en la casilla de salida.
     * @return Devuelve true si hay un ganador,
     *                  false si no hay un ganador.
     */
    public boolean haveAWinner(){
        return playerGrid[exitRow][exitCol] != null;
    }

    /**
     * Método toString
     * @return Deveuelve una representación interna del laberinto.
     */
    @Override
    public String toString() {
        String estado = "\nLabyrinth{" + "nRows=" + nRows + ", nCols=" + nCols 
                + ", exitRow=" + exitRow + ", exitCol=" + exitCol + '}' + "\n\n";
        for(int i = 0; i < nRows; i++){
            for(int j = 0; j < nCols; j++){
                estado += characterGrid[i][j];
                estado += " ";
            }
            estado += "\n";
        }
        return estado;
        
    }

    /**
     * Añade un monstruo al laberinto. 
     * Anota su presencia en el tablero de caracteres, guarda la referencia del
     * monstruo en el atributo contendor e indica al mosntruo su posición (setPos)
     * @param row       Número de fila en la que irá el monstruo.
     * @param col       Número de columna en la que irá el monstruo.
     * @param monster   Objeto monstruo.
     * @see Monster.setPos  Método de la clase Monster para guardar la nueva posición del mosntruo.
     */
    public void addMonster(int row, int col, Monster monster){
        if(posOK(row, col) && emptyPos(row, col)){
            characterGrid[row][col] = MONSTER_CHAR;
            monsterGrid[row][col] = monster;
            monster.setPos(row, col);
        }else
            System.out.println("\nNo ha sido posible añadir el monstruo en"
                    + " esta posición, ya que no es una posición válida o"
                    + " no está vacía\n");
 
    }
    
    /**
     * 
     * @param direction
     * @param player
     * @return 
     */
    public Monster putPlayer(Directions direction,Player player){
        int oldRow = player.getRow();
        int oldCol = player.getCol();
        int[] newPos = dir2Pos(oldRow, oldCol, direction);
        Monster monster = putPlayer2D(oldRow, oldCol, newPos[ROW], newPos[COL], 
                player);
        return monster;
    }
    
    /**
     * 
     * @param orientation
     * @param startRow
     * @param startCol
     * @param length 
     */
    public void addBlock(Orientation orientation, int startRow, int startCol, 
            int length)
    {
        int incRow, incCol, row, col;
        if(orientation == Orientation.VERTICAL){
            incRow = 1;
            incCol = 0;
        }else{
            incRow = 0;
            incCol = 1;
        }
        
        row = startRow;
        col = startCol;
        
        while( (posOK(row, col)) && (emptyPos(row, col)) && (length > 0) ){
            characterGrid[row][col] = BLOCK_CHAR;
            length -= 1;
            row += incRow;
            col += incCol;
        }
    }
    
    /**
     * Devuelve una colección de la dirección a la que nos podemos dirigir 
     * dependiendo de la posición que le pasemos cómo parámetro.
     * @param row número de fila.
     * @param col número de columna.
     * @return arrayList de movimientos válidos.
     */
    public ArrayList<Directions> validMoves(int row, int col){
        ArrayList<Directions> output = new ArrayList<Directions>();
        
        if(canStepOn(row+1, col))
            output.add(Directions.DOWN);
        
        if(canStepOn(row-1, col))
            output.add(Directions.UP);
        
        if(canStepOn(row, col+1))
            output.add(Directions.RIGHT);
        
        if(canStepOn(row, col-1))
            output.add(Directions.LEFT);
        
        return output;
    }
    
    /**
     * Comprueba si la posición suministrada está dentro del laberinto.
     * @param row   Número de fila.
     * @param col   Número de columna.
     * @return Devuelve true si la posición está dentro,
     *                  false si la posición está fuera.
     */
    private boolean posOK(int row, int col){
        boolean rowOK, colOK;
        rowOK = (row >= 0 && row < nRows);  //nRows es el número TOTAL de filas, no puede ser <=, sino <
        colOK = (col >= 0 && col < nCols);  //aquí pasa lo mismo
        
        return rowOK && colOK;
    }
    
    /**
     * Comprueba si la posición suministrada está vacía.
     * @param row   Número de fila.
     * @param col   Número de columna.
     * @return Devuelve true si la posición está vacía,
     *                  false si la posición tiene contenido.
     */
    private boolean emptyPos(int row, int col){
        return characterGrid[row][col] == EMPTY_CHAR;
    }
       
    /**
     * Comprueba si la posición suministrada alberga exclusivamente un monstruo.
     * @param row   Número de fila.
     * @param col   Número de columna.
     * @return Devuelve true si lo hay,
     *                  false si no lo hay.
     */
    private boolean monsterPos(int row, int col){
        boolean charGridOK, monsterGridOK;
        charGridOK      = characterGrid[row][col] == MONSTER_CHAR;
        monsterGridOK   = monsterGrid[row][col]   != null;
        return charGridOK && monsterGridOK;
    }
        
    /**
     * Comprueba si la posición suministrada es la de salida.
     * @param row   Número de fila.
     * @param col   Número de columna.
     * @return Devuelve true si lo es,
     *                  false si no lo es.
     */
    private boolean exitPos(int row, int col){
        boolean rowColOK, charGridOK;
        rowColOK = ((row == exitRow) && (col == exitCol));
        charGridOK = characterGrid[row][col] == EXIT_CHAR;
        return rowColOK && charGridOK;
    }
    
    /**
     * Comprueba si la posición suministrada contiene un combate (carácter 'C').
     * Cuando hay un combate, quiere decir que hay un jugador y un mostruo en la
     * misma casilla.
     * @param row   Número de fila.
     * @param col   Número de columna.
     * @return Devuelve true si lo hay,
     *                  false si no lo hay.
     */
    private boolean combatPos(int row, int col){
        boolean
                charGridOK = characterGrid[row][col] == COMBAT_CHAR,
                playerOK   = playerGrid[row][col]    != null,
                monsterOK  = monsterGrid[row][col]   != null;
        return charGridOK && playerOK && monsterOK;
    }
    
    /**
     * Comprueba si la posición suministrada está dentro del laberinto y si 
     * se corresponde con una de estas tres opciones:
     *      casilla vacía,
     *      casilla donde habita un mosntruo,
     *      casilla de salida.
     * @param row   Número de fila.
     * @param col   Número de columna.
     * @see posOk, emptyPos, monsterPos, exitPos.
     * @return Devuelve true si se cumple,
     *                  false si nos se cumple.
     */
    private boolean canStepOn(int row, int col){
        if(posOK(row, col)){
            return (emptyPos(row, col) || monsterPos(row, col) || exitPos(row, col));
        }
        return false;
    }
    
    /**
     * Este método es llamado cuando un jugador abandona una casilla y se 
     * encarga de dejar la casilla que se abandona en el estado correcto.
     * 
     * Este método solo realiza su función si la posición suministrada está 
     * dentro del laberinto. Si es así, tenemos:
     *      - si en esa posición, había un combate, el estado de esa casilla 
     *          pasa a indicar que simplemente hay un monstruo. 
     *      - en otro caso, el estado de esa casilla pasa a indicar que está vacía. 
     * @see posOk
     * @param row   Número de fila.
     * @param col   Número de columna.
     */
    private void updateOldPos(int row, int col){
        if(posOK(row, col)){
            if(characterGrid[row][col] == COMBAT_CHAR)
                characterGrid[row][col] = MONSTER_CHAR;
            else 
                characterGrid[row][col] = EMPTY_CHAR;
        }
    }
    
    /**
     * Este método calcula la posición del laberinto a la que se llegaría si 
     * desde la posición suministrada se avanza en la dirección pasada como 
     * parámetro. 
     * 
     * No realiza comprobaciones relativas a no generar posiciones fuera del 
     * laberinto.
     * 
     * @param row       Número de fila.
     * @param col       Número de columna.
     * @param direction Instancia del enum Directions
     * @return Devuelve: en la posición 0, el número de fila,
     *                   en la posición 1, el número de columna.
     */
    private int[] dir2Pos(int row, int col, Directions direction){
        
        switch (direction){
            case UP:
                row --;
                break;
            
            case DOWN:
                row++;
                break;
            
            case LEFT:
                col--;
                break;
                
            case RIGHT:
                col++;
                break;              
        }
        
        int newPos[] = {row, col};
        
        return newPos;
    }
    
    
    /**
     * Este método genera una posición aleatoria en el laberinto (fila y columna)
     * asegurandose que esté vacía. Si no hay posiciones vacías se producirá un 
     * bucle infinito.
     * @see Dice.randomPos(max), 
     *      emptyPos.
     * @return una posición aleatoria en el tablero y que está vacía. 
     *         en la posición 0, se guarda el número de fila,
     *         en la posición 1, se guarda el número de columna.
     */
    private int[] randomEmptyPos(){
        int rowRandom = 0, 
            colRandom = 0;
        boolean posValida = false;
        
        while (!posValida){
            rowRandom = Dice.randomPos(nRows);
            colRandom = Dice.randomPos(nCols);
            if(emptyPos(rowRandom, colRandom))
                posValida = true;       
        }
        int[] randomPos = {rowRandom, colRandom};
        return randomPos;
    }
    
    /**
     * Coloca un jugador en las matrices que hacen de capas del tablero/laberinto.
     * @param oldRow fila de la antigua posición del jugador
     * @param oldCol columna de la antigua posición del jugador
     * @param row    fila de la nueva posición del jugador
     * @param col    columna de la nueva posición del juagdor
     * @param player jugador que se quiere colocar
     * @return output.
     */
    private Monster putPlayer2D(int oldRow, int oldCol, int row, int col, 
            Player player)
    {
        Monster output = null;
        
        if(canStepOn(row, col)){    
            //------------------------------------------------------------------
            if(posOK(oldRow, oldCol)){
                Player p = playerGrid[oldRow][oldCol];
                if(p == player){
                    updateOldPos(oldRow, oldCol);
                    playerGrid[oldRow][oldCol] = null;
                }                
            }
            
            //------------------------------------------------------------------
            
            boolean monsterPos = monsterPos(row, col);
            
            //------------------------------------------------------------------
            if(monsterPos){
                characterGrid[row][col] = COMBAT_CHAR;
                output = monsterGrid[row][col];
            }else {
                char number = player.getNumber();
                characterGrid[row][col] = number;
            }
            
            //------------------------------------------------------------------
            playerGrid[row][col] = player;
            player.setPos(row, col);                 
        }
                
        return output;
    }

}
