
/**
 * Esta clase representa a un jugador.
 * @file Player.java
 * @author angel_rodriguez
 */

package irrgarten;

import irrgarten.enums.Directions;
import java.util.ArrayList;
import java.util.List;


public class Player {
    //Atributos de clase privados.
    private static final int MAX_WEAPONS = 2;
    private static final int MAX_SHIELDS = 3;
    private static final int INITIAL_HEALTH = 10;
    private static final int HITS2LOSE = 3;
    
    //Atributos de instancia privados.
    private String name;            // Nombre.
    private char number;            // Número identificador.
    private float intelligence;     // Inteligencia.
    private float strength;         // Fuerza.
    private float health;           // Salud.
    private int row;                // Nº de fila de su posición.
    private int col;                // Nº de columna de su posición.
    private int consecutiveHits = 0; // Nº de impactos consecutivos.
    
    //Atributos de relación privados.
    private ArrayList<Weapon> weapons;   // contenedor de armas.
    private ArrayList<Shield> shields;   // contenedor de escudos.
    
    
    //Métodos de instancia públicos.
    
    
    /**
     * Constructor paramétrico.
     * @param name              Nombre del jugador.
     * @param number            Número identificador de jugador.
     * @param intelligence      Inteligencia del jugador.
     * @param strength          Fuerza del jugador.
     * @param health            Salud del jugador.
     * @param row               Fila de la posición del jugador.
     * @param col               Columna de la posición del jugador.
     * @param consecutiveHits   Impactos consecutivos del jugador.
     * @param weapons           Contenedor de armas del juagdor.
     * @param shields           Contenedor de escudos del jugador.
     */
    public Player(String name, char number, float intelligence, float strength,
            float health, int row, int col, int consecutiveHits,
            List<Weapon> weapons, List<Shield> shields) {
        this.number = number;
        this.name = "Player #" + this.number;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = INITIAL_HEALTH;
        this.row = row;
        this.col = col;
        this.consecutiveHits = consecutiveHits;
        this.weapons = new ArrayList<Weapon>();
        this.shields = new ArrayList<Shield>();
    }
    
    /**
     * Constructor sin parámetros.
     */
    public Player() {
        this("", '0', 0f, 0f, 0f, 0, 0,     
                0, new ArrayList<Weapon>(), new ArrayList<Shield>());
    }
    
    /**
     * Constructor con algunos parámetros.
     * @param number        Número identificador del jugador.
     * @param intelligence  Inteligencia del jugador.
     * @param strength      Fuerza del jugador.    
     */
    public Player(char number, float intelligence, float strength) {
        this("", number, intelligence, strength,INITIAL_HEALTH, 0,
                0, 0, new ArrayList<Weapon>(), 
                new ArrayList<Shield>());
    }
    
    /**
     * Constructor por copia
     * @param p objeto de tipo Player
     */
    public Player(Player p){
        this(p.name, p.number, p.intelligence, 
                p.strength, p.health, p.row, p.col, 
                p.consecutiveHits, p.weapons, p.shields);
    }
    
    /**
     * Método que hace todas las tareas asociadas a la resurrección.
     */
    public void resurrect(){
        weapons.clear();
        shields.clear();
        health = INITIAL_HEALTH;
        consecutiveHits = 0;
 
    }
    
    /**
     * Consultor de fila.
     * @return row nº de fila en la que está el jugador.
     */
    public int getRow() {
        return row;
    }
    
    /**
     * Consultor de columna.
     * @return col nº de columna en la que está el jugador.
     */
    public int getCol() {
        return col;
    }

    /**
     * Consultor de número.
     * @return number número de jugador.
     */
    public char getNumber() {
        return number;
    }
    
    /**
     * Modificador de fila y columna.
     * @param row número de fila.
     * @param col número de columna.
     */
    public void setPos(int row, int col) {
        this.row = row;
        this.col = col;
    }
    
    /**
     * Comprueba si el jugador está muerto.
     * @return Devuelve true si está muerto, 
     *                  false si no lo está.
     */
    public boolean dead(){
        return health < 1;
    }
    
    /**
     * Comprueba si el movimineto que quiere hacer es válido.
     * @param direction dirección en la que se quiere mover
     * @param validMoves arrayList de los movimientos válidos
     * @return la dirección en la que se debe de mover
     */
    public Directions move(Directions direction, ArrayList<Directions> validMoves ){
        //1.1
        int size = validMoves.size();
        
        // 1.2 El método .contains devuelve true si esta lista sobre la que se 
        // invoca contiene todos los elementos de la colección pasada por parámetro.
        boolean contained = validMoves.contains(direction);
        if( size > 0 && !contained )
            //1.3 y 1.4
            return validMoves.get(0);   // firstElement         
        
        return direction;
    }
    
    /**
     * Calcula la suma de las fuerzas.
     * @return Devuelve la suma de la fuerza del jugador 
     *         más lo aportado por sus armas.
     */
    public float attack(){
        float attack = strength + sumWeapons();
        return attack;
    }
    
    /**
     * 
     */
    public boolean defend(float receivedAttack){
        return manageHit(receivedAttack);
    }
    
    /**
     * 
     */
    public void receiveReward(){
        //1.1
        int wReward = Dice.weaponsReward();
        int sReward = Dice.shieldsReward();
        
        for(int i = 1; i <= wReward; i++){
            Weapon wnew = newWeapon();          //1.3
            receiveWeapon(wnew);              //1.4
        }
        
        for(int i = 1; i <= sReward; i++){
            Shield snew = newShield();          //1.5
            receiveShield(snew);              //1.6   
        }
        
        int extraHealth = Dice.healthReward();  //1.7
        health += extraHealth;
    }
    
    /**
     * toString
     * @return Genera un representación del estado completo del 
     *         jugador en forma de cadena de caracteres.
     */
    @Override
    public String toString() {
        return "Player{" + "name= " + name + ", number= " + number 
                + ", intelligence= " + intelligence + ", strength= " + strength 
                + ", health= " + health + ", row= " + row + ", col= " + col 
                + ", consecutiveHits= " + consecutiveHits + '}';
    }
    
    
    
    //Métodos de instancia privados.
    
    
    /**
     * Añade el arma al contenedor de armas, si es posible.
     * @param w arma que se quiere añadir
     */
    private void receiveWeapon(Weapon w){
        receiveWeapon(w);
        for(Weapon wi: weapons){
            boolean discard = wi.discard();
            if(discard)
                weapons.remove(wi);
        }
        
        int size = weapons.size();
        
        if(size < MAX_WEAPONS)
            weapons.add(w);
    }
    
    /**
     * Añade el escudo al contenedor de escudos, si es posible.
     * @param s escudo que se quiere añadir
     */
    private void receiveShield(Shield s){
        receiveShield(s);                     //1
        for(Shield si: shields){              //1.1
            boolean discard = si.discard();   //1.2
            if(discard)
                shields.remove(si);         //1.3
        }
        
        int size = shields.size();
        
        if(size < MAX_SHIELDS)
            shields.add(s);        
    }
    
    /**
     * Genera una nueva instancia de arma.
     * @pre Los parámetros necesarios se le solicitarán al dado.
     */
    private Weapon newWeapon(){
        float power = Dice.weaponPower();
        int uses = Dice.usesLeft();
        Weapon weaponNew = new Weapon(power, uses);
        return weaponNew;
    }
    
    /**
     * Genera una nueva instancia de escudo.
     * @pre Los parámetros necesarios se le solicitarán al dado.
     */
    private Shield newShield(){
        float protection = Dice.shieldPower();
        int uses = Dice.usesLeft();
        Shield shieldNew = new Shield(protection, uses);
        return shieldNew;
    }
    
    /**
     * Suma del ataque de todas las armas.
     * @return Devuelve la suma del resultado de llamar al método attack 
     *         de todas sus armas.
     */
    private float sumWeapons(){
        float suma = (float) 0.0;
        for(int i = 0; i < weapons.size(); i++)
            suma += weapons.get(i).attack();
        return suma;
    }
    
    /**
     * Suma de la protección de todos los escudos.
     * @return Devuelve la suma del resultado de llamar al método protect 
     *         de todos sus escudos.
     */
    private float sumShields(){
        float suma = (float) 0.0;
        for(int i = 0; i < shields.size(); i++)
            suma += shields.get(i).protect();
        return suma;
    }    
    
    /**
     * Suma de la inteligencia del jugador y la suma de los escudos.
     * @return Devuelve la suma de la inteligencia más sumShields.
     */
    private float defensiveEnergy(){
        float defensiveEnergy = intelligence + sumShields();
        return  defensiveEnergy;
    }
    
    /**
     * @brief 
     */
    private boolean manageHit(float receivedAttack){
        float defense = defensiveEnergy();
        if(defense < receivedAttack){
            gotWounded();
            incConsecutiveHits();
        }else
            resetHits();
        
        if( ( (consecutiveHits == HITS2LOSE) || (dead()) ) ){
            resetHits();
            return true;            
        }
        
        return false;                  
    }
    
    /**
     * Fija el valor del contador de impactos consecutivos a cero.
     */
    private void resetHits(){
        consecutiveHits = 0;
    }
    
    /**
     * Decrementa en una unidad el atributo que representa la salud
     *  del jugador (health).
     */
    private void gotWounded(){
        health--;
    }
    
    /**
     * Incrementa en una unidad el contador de impactos consecutivos.
     */
    private void incConsecutiveHits(){
        consecutiveHits++;
    }
    
}
