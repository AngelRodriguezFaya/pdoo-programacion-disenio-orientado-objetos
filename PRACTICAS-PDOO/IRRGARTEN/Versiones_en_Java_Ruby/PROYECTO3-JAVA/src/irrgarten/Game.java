/**
 * @file Game.java
 * @author angel_rodriguez
 */

package irrgarten;

import irrgarten.enums.GameCharacter;
import irrgarten.enums.Directions;
import irrgarten.enums.Orientation;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    //Atributo de clase privado.
    private static final int MAX_ROUNDS = 10;
    
    //Atributos de instancias privados.
    private int currentPlayerIndex;     // índice del jugador actual.
    private String log;                 // cadena para almacenar el estado del juego.
    
    //Atributos de relación privados.
    private ArrayList <Player> players;      // contenedor de jugadores
    private Player currentPlayer;       // jugador actual
    private ArrayList <Monster> monsters;    // contenedor de monstruos
    private Labyrinth labyrinth;        // laberinto
    
    //Métodos de instancias públicos.
    
    /**
     * Constructor paramétrico.
     * @param nplayers número de jugadores.
     */
    public Game(int nplayers){
        /* SE DECIDE TODO DE FORMA ALEATORIA.
            -   Jugador que empieza la partida ---> currentPlayerIndex
            -   Número de monstruos.
            -   Posición de la casilla de salida (exitRow, exitCol)
        */
        
        Random generator = new Random();
        
        this.log = "";
        this.players = new ArrayList<Player>();
        this.monsters = new ArrayList<Monster>();
        
        // Creo y agrego los jugadores al contenedor.
        for (int i = 0; i < nplayers; i++){
            char numberPlayer = (char) ('0' + i);
            Player player = new Player(numberPlayer, 
                    Dice.randomIntelligence(), Dice.randomStrength());
            this.players.add(player);
        }
        
        this.currentPlayerIndex = generator.nextInt(nplayers);      
        this.currentPlayer = players.get(currentPlayerIndex);
        
        // El número de monstruos y la casilla de salida
        final int ROWS = 8; 
        final int COLS = 8;
        int 
            numMonster = generator.nextInt(10) + 1, //mínimo 1 monstruo.
            exitRow    = Dice.randomPos(ROWS),
            exitCol    = Dice.randomPos(COLS);
        
        // Añado los monstruos
        for( int i = 0; i < numMonster; i++){
            String nameMonster = "Monstruo " + i;
            Monster monster = new Monster(nameMonster, 
                    Dice.randomIntelligence(), Dice.randomStrength());
            this.monsters.add(monster);
        }
        
        // Instancio el laberinto
        this.labyrinth = new Labyrinth(ROWS, COLS, exitRow, exitCol);
        
        // Configuro el laberinto.
        configureLabyrinth();
        
        // Reparte los jugadores por el tablero.
        labyrinth.spreadPlayers(this.players);
    }
    
    /**
     * Constructor por defecto.
     */
    public Game(){ this(2);}
    
    public Game(int currentPlayerIndex, int nplayers, ArrayList<Player> players){
        this.currentPlayerIndex = 0;
        this.log = "";
        this.players = new ArrayList<Player>();
        
        // Creo y agrego los jugadores al contenedor.
        for (int i = 0; i < nplayers; i++){
            Player player = new Player();
            this.players.add(player);
        }
        
        this.currentPlayer = new Player();
        this.monsters = new ArrayList<Monster>();
        this.labyrinth = new Labyrinth();
    }
    
    public boolean finished(){
        return labyrinth.haveAWinner();        
    }
    
    public boolean nextStep(Directions preferredDirection){
        log = "";
        boolean dead = currentPlayer.dead();
        
        if(!dead){
            Directions direction = actualDirection(preferredDirection);
            
            if(direction != preferredDirection)
                logPlayerNoOrders();
            
            Monster monster = labyrinth.putPlayer(direction, currentPlayer);
            
            if(monster == null)
                logNoMonster();
            else{
                GameCharacter winner = combat(monster);
                manageReward(winner);
            }
            
        }else
            manageResurrection();
        
        boolean endGame = finished();
        
        if(!endGame)
            nextPlayer();
        
        return endGame;
    }
    
    /**
     * Devuelve un estado actual del juego.
     * @return GameState
     */
    public GameState getGameState(){
        String statePlayers = "", stateMonsters = "\n";
        for(int i = 0; i < players.size(); i++)
            statePlayers += players.get(i).toString() + "\n";
        for(int j = 0; j < monsters.size(); j++)
            stateMonsters += monsters.get(j).toString() + "\n";
        GameState gameState = new GameState(labyrinth.toString(), 
                statePlayers, stateMonsters,
                currentPlayerIndex, finished(), log);
        return gameState;
    }
    
    /**
     * Configura el laberinto añadiendo bloques e indicando la posición a
     * los monstruos.
     */
    private void configureLabyrinth(){
        Random generator = new Random();
        final int ROWS = 8; 
        final int COLS = 8;

        labyrinth.addBlock(Orientation.HORIZONTAL, 0, 0, 8);
        
        for(int i = 0; i < monsters.size(); i++){
          int row = generator.nextInt(ROWS),
              col = generator.nextInt(COLS);
            labyrinth.addMonster(row, col, monsters.get(i));
        }
    }

    
    private void nextPlayer(){
        this.currentPlayerIndex++;
        this.currentPlayer = this.players.get(currentPlayerIndex);
    }
    
    private Directions actualDirection(Directions preferredDirection){
        int currentRow = currentPlayer.getRow();
        int currentCol = currentPlayer.getCol();
        ArrayList<Directions> validMoves = labyrinth.validMoves(currentRow, 
                currentCol);
        Directions output = currentPlayer.move(preferredDirection, validMoves);
        
        return output;        
    }
    
    private GameCharacter combat(Monster monster){
        int rounds = 0;
        GameCharacter winner = GameCharacter.PLAYER;
        float playerAttack = currentPlayer.attack();
        boolean lose = monster.defend(playerAttack);
        while( (!lose) && (rounds < MAX_ROUNDS) ){
            winner = GameCharacter.MONSTER;
            rounds++;
            float monsterAttack = monster.attack();
            lose = currentPlayer.defend(monsterAttack);
            if(!lose){
                playerAttack = currentPlayer.attack();
                winner = GameCharacter.PLAYER;
                lose = monster.defend(playerAttack);
            }
        }
        
        logRounds(rounds, MAX_ROUNDS);
        
        return winner;    
    }
    
    private void manageReward(GameCharacter winner){
        if(winner == GameCharacter.PLAYER){
            currentPlayer.receiveReward();
            logPlayerWon();
        }else
            logMonsterWon();
    }
    
    private void manageResurrection(){
        boolean resurrect = Dice.resurrectPlayer();
        if(resurrect){
            currentPlayer.resurrect();
            logResurrected();
        }else
            logPlayerSkipTurn();
        
    }
    
    private void logPlayerWon(){
        this.log += "El jugador ha ganado el combate. \n";
    }
    
    private void logMonsterWon(){
        this.log += "El monstruo ha ganado el combate.\n";
    }
    
    private void logResurrected(){
        this.log += "El jugador ha resucitado.\n";
    }
    
    private void logPlayerSkipTurn(){
        this.log += "El jugador ha perdido el turno por estar muerto.\n";
    }
    
    private void logPlayerNoOrders(){
        this.log += "El jugador no ha podido seguir las instrucciones del jugador humano (no ha sido posible). \n";
    }
    
    private void logNoMonster(){
        this.log += "El jugador se ha movido a una celda vacía o no le ha sido posible moverse.\n";  
    }
    
    private void logRounds(int rounds, int max){
        this.log += "Se han producido " + rounds + " de " + max + " rondas de combate.\n";
    }
    
}
