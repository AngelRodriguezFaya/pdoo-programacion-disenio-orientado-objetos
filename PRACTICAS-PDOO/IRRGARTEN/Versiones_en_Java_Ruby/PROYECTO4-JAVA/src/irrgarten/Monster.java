
/**
 * Esta clase representa a un monstruo.
 * @file Monster.java
 * @author angel_rodriguez
 */

package irrgarten;

public class Monster extends LabyrinthCharacter{
    
    //---------------------------------
    // Atributes
    //---------------------------------
    
    private static final int INITIAL_HEALTH = 5;
    
    
    //---------------------------------
    // Constructors
    //---------------------------------
    
    /**
     * Constructor por defecto.
     */
    public Monster() {    
        this("", 0f, 0f, (float)INITIAL_HEALTH, 0, 0);
    }

    /**
     * Constructor paramétrico.
     * Delega su funcionalidad al constructor de su clase padre.
     * @see Constructor de LabyrinthCharacter.
     * @param name          nombre del monstruo.
     * @param intelligence  inteligencia del monstruo.
     * @param strength      fuerza del monstruo.
     * @param health        salud del monstruo.
     * @param row           fila donde se encuentra el monstruo.
     * @param col           columna donde se encuentra el mosntruo.
     */
    public Monster(String name, float intelligence, float strength,
                   float health, int row, int col) {
        super(name, intelligence, strength, health, row, col);
    }
    
    /**
     * Constructor con algunos parámetros
     * Delega su funcionalidad al constructor de su clase padre.
     * @see Constructor de LabyrinthCharacter.
     * @param name          nombre del monstruo.
     * @param intelligence  inteligencia del monstruo.
     * @param strength      fuerza del monstruo.
     */
    public Monster(String name, float intelligence, float strength){
        this(name, intelligence, strength, INITIAL_HEALTH, 0, 0); 
    }
    
    //---------------------------------
    // Getters & Setters
    //---------------------------------
    
    
    //---------------------------------
    // Public Methods
    //---------------------------------
    
    
    //---------------------------------
    // Protected Methods
    //---------------------------------
    
    
    //---------------------------------
    // Private Methods
    //---------------------------------
    
    
    //---------------------------------
    // Abstract Methods
    //---------------------------------
    
    
    //---------------------------------
    // Override Methods
    //---------------------------------
    
    /**
     * Genera el resultado delegando en el método intensity del dado
     * pasando como parámetro la fuerza del monstruo.
     * @see Dice.intensity(competence)
     * @return Devuelve la fuerza del ataque del monstruo.
     */
    @Override
    public float attack() {
        return Dice.intensity(getStrength());
    }
    
    /**
     * Devuelve si un monstruo muere tras recibir un ataque.
     * @param receivedAttack ataque recibido.
     * @return true si muere.
     *         false si no muere.
     */
    @Override
    public boolean defend(float receivedAttack){
        //1.1
        boolean isDead = dead();
        
        if(!isDead){
            //1.2
            float defensiveEnergy = Dice.intensity(getIntelligence());
            
            if (defensiveEnergy < receivedAttack){
                gotWounded();       //1.3 // health--
                isDead = dead();    //1.4
            }
        }
        
        return isDead;
    }

}
