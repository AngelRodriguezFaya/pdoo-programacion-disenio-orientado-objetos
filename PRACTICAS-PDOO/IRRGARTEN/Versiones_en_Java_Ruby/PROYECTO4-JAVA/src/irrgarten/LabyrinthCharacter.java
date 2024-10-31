
package irrgarten;

/**
 *
 * @author angel_rodriguez
 */
public abstract class LabyrinthCharacter {
    
    //---------------------------------
    // Atributes
    //---------------------------------
    
    private String name;        // Nombre
    private float intelligence; // Inteligencia
    private float strength;     // Fuerza
    private float health;       // Salud
    private int row;            // nº Fila (de su posición)
    private int col;            // nº Columna (de su posición)
    
    
    //---------------------------------
    // Constructors
    //---------------------------------
    
    /**
     * Constructor paramétrico.
     * @param name          nombre del personaje.
     * @param intelligence  inteligencia del personaje.
     * @param strength      fuerza del personaje.
     * @param health        salud del personaje.
     * @param row           fila donde se encuentra el personaje.
     * @param col           columna donde se encuentra el personaje.
     */
    public LabyrinthCharacter(String name, float intelligence, float strength, float health, int row, int col) {
        this.name = name;
        this.intelligence = intelligence;
        this.strength = strength;
        this.health = health;
        this.row = row;
        this.col = col;
    }
    
    public LabyrinthCharacter(LabyrinthCharacter other){
        this(other.name, other.intelligence, other.strength, 
                other.health, other.row, other.col);
    }
    
    //---------------------------------
    // Public Methods
    //---------------------------------
    
    /**
     * Comprueba si el jugador está muerto.
     * @return Devuelve true si está muerto, 
     *                  false si no lo está.
     */
    public boolean dead(){
        return health <= 0;
    }
    
    //---------------------------------
    // Getters & Setters
    //---------------------------------
    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    protected float getIntelligence() {
        return intelligence;
    }

    protected float getStrength() {
        return strength;
    }

    protected float getHealth() {
        return health;
    }

    protected void setHealth(float health) {
        this.health = health;
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
    
    //---------------------------------
    // Override Methods
    //---------------------------------
    
    /**
     * toString
     * @return Genera un representación del estado completo del personaje en 
     *          forma de cadena de caracteres.
     */
    @Override
    public String toString() {
        return "LabyrinthCharacter{" + "name= " + name + ", intelligence= " + 
                intelligence + ", strength= " + strength + ", health= " + health 
                + ", row= " + row + ", col= " + col + '}';
    }

    //---------------------------------
    // Protected Methods
    //---------------------------------
    /**
     * Decrementa en una unidad la salud del personaje.
     */
    protected void gotWounded() {
        health--;
    }
    
    
    //---------------------------------
    // Abstract Methods
    //---------------------------------
    
    public abstract float attack();
    
    public abstract boolean defend(float attack);  
    
    
    
    
    
    
    
    
    
    
    
    
}
