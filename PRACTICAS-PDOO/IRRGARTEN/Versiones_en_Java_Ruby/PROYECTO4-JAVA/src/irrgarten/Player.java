
/**
 * Esta clase representa a un jugador.
 * @file Player.java
 * @author angel_rodriguez
 */

package irrgarten;

import irrgarten.enums.Directions;
import java.util.ArrayList;
import java.util.List;
import javax.swing.text.html.parser.AttributeList;


public class Player extends LabyrinthCharacter{

    //---------------------------------
    // Atributes
    //---------------------------------
    
    private static final int MAX_WEAPONS = 2;
    private static final int MAX_SHIELDS = 3;
    private static final int INITIAL_HEALTH = 10;
    private static final int HITS2LOSE = 3;
    private char number;                    // Número identificador.
    private int consecutiveHits = 0;        // Nº de impactos consecutivos.
    private ArrayList<Weapon> weapons;      // contenedor de armas.
    private ArrayList<Shield> shields;      // contenedor de escudos.
    private WeaponCardDeck weaponCardDeck;
    private ShieldCardDeck shieldCardDeck;
    
    
    //---------------------------------
    // Constructors
    //---------------------------------    
    
    /**
     * Constructor paramétrico.
     * @param number            Número identificador de jugador.
     * @param intelligence      Inteligencia del jugador.
     * @param strength          Fuerza del jugador.
     */
    public Player(char number, float intelligence, float strength){
        super("Player #" + number, intelligence, strength, INITIAL_HEALTH, 0, 0);
        this.number = number;
        this.consecutiveHits = 0;
        this.weapons = new ArrayList<Weapon>();
        this.shields = new ArrayList<Shield>();
        this.weaponCardDeck = new WeaponCardDeck(weapons);
        this.shieldCardDeck = new ShieldCardDeck(shields);
    }

    /**
     * Constructor por copia
     * @param p objeto de tipo Player
     */
    public Player(Player o){
        this(o.number, o.getIntelligence(), o.getStrength());
    }
    
    
    //---------------------------------
    // Public Methods
    //---------------------------------
    
    /**
     * Método que hace todas las tareas asociadas a la resurrección.
     */
    public void resurrect(){
        weapons.clear();
        shields.clear();
        setHealth(INITIAL_HEALTH);
        consecutiveHits = 0;
 
    }

    /**
     * Consultor de número.
     * @return number número de jugador.
     */
    public char getNumber() {
        return number;
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
        
        setHealth(getHealth() + Dice.healthReward());
    }
    
    //---------------------------------
    // Getters & Setters
    //---------------------------------
    
    
    //---------------------------------
    // Protected Methods
    //---------------------------------
    
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
        float defensiveEnergy = getIntelligence() + sumShields();
        return  defensiveEnergy;
    }
    
    
    //---------------------------------
    // Private Methods
    //---------------------------------
    
    /**
     * Añade el arma al contenedor de armas, si es posible.
     * @param w arma que se quiere añadir
     */
    private void receiveWeapon(Weapon w){
        for(int i = 0; i < weapons.size(); i++){
            Weapon wi = weapons.get(i);
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
    private void receiveShield(Shield s){                   //1
        for(int i = 0; i < shields.size(); i++){
            Shield si = shields.get(i);
            boolean discard = si.discard();
            if(discard)
                shields.remove(si);         
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
     * Incrementa en una unidad el contador de impactos consecutivos.
     */
    private void incConsecutiveHits(){
        consecutiveHits++;
    }
    
    //---------------------------------
    // Abstract Methods
    //---------------------------------
   
    //---------------------------------
    // Override Methods
    //---------------------------------
    
    /**
     * Calcula la suma de las fuerzas.
     * @return Devuelve la suma de la fuerza del jugador 
     *         más lo aportado por sus armas.
     */
    @Override
    public float attack(){
        float attack = getStrength() + sumWeapons();
        return attack;
    }
    
    /**
     * 
     */
    @Override
    public boolean defend(float receivedAttack){
        return manageHit(receivedAttack);
    }
    
    /**
     * toString
     * @return Genera un representación del estado completo del 
     *         jugador en forma de cadena de caracteres.
     */
    @Override
    public String toString() {
        return "Player#" + getNumber() + ", intelligence= " + getIntelligence() 
                + ", strength= " + getStrength() + ", health= " + getHealth();
    }
}
