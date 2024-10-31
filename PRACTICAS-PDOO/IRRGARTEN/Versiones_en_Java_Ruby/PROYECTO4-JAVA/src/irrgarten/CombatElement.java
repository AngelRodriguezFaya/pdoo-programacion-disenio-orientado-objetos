
package irrgarten;

/**
 *
 * @author angel_rodriguez  
 */
public abstract class CombatElement {
    
    //---------------------------------
    // Atributes
    //---------------------------------
    private float effect;
    private int uses;

    //---------------------------------
    // Constructors
    //---------------------------------
    public CombatElement(float effect, int uses) {
        this.effect = effect;
        this.uses = uses;
    }
    
    //---------------------------------
    // Getters & Setters
    //---------------------------------
    
    //---------------------------------
    // Public Methods
    //---------------------------------
    
    /**
     * Implementa la decisión de si el arma o escudo debe ser descartados.
     * Delega en el método discardElement de la clase Dice.
     * Hay que pasarle como parámetros la cantidad de usos restantes del arma o escudo.
     * @see Dice.discardElement(usesLeft).
     * @return boolean si el arma o escudo se descarta o no.
     */
    public boolean discard(){
        return Dice.discardElement(uses);
    }
    
    //---------------------------------
    // Protected Methods
    //---------------------------------
    protected float produceEffect(){
        float r = 0;
        if(uses > 0){
            uses--;
            r = effect; 
        }
        return r;
    }
    
    //---------------------------------
    // Private Methods
    //---------------------------------
    
    //---------------------------------
    // Abstract Methods
    //---------------------------------
    
    //---------------------------------
    // Override Methods
    //---------------------------------

    @Override
    public String toString() {
        return "CombatElement{" + "effect=" + effect + ", uses=" + uses + '}';
    }
    
}
