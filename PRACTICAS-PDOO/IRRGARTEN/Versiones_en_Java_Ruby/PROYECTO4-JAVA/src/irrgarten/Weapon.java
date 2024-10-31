
/**
 * Esta clase representa las armas que utiliza el jugador en los ataques 
 * durante los combates.
 * @file Weapon.java
 * @author angel_rodriguez
 */

package irrgarten;

public class Weapon extends CombatElement{
    
    /**
     * Constructor con parámetros.
     * @param power potencia de disparo del arma.
     * @param uses  usos restantes del arma.
     */
    public Weapon(float power, int uses) {
        super(power, uses);
    }
    
    /**
     * Constructor por defecto.
     */
    public Weapon() {
        this(0f, 0);
    }
    
    /**
     * Devuelve el daño/ataque del arma si uses > 0, si no, devuelve 0.
     * @return float representando la intensidad del ataque del arma.
     */
    public float attack () {
        return super.produceEffect();
    } 
    
    /**
     * Método toString.
     * @return representación del estado interno del arma.
     */
    @Override
    public String toString(){       
        return "Weapon: " + super.toString();
    }
    
    /**
     * Implementa la decisión de si el arma debe ser descartada.
     * Delega en el método discardElement de la clase Dice.
     * Hay que pasarle como parámetros la cantidad de usos restantes del arma.
     * @see Dice.discardElement(usesLeft).
     * @return boolean si el arma se descarta o no.
     */
    public boolean discard(){
        return super.discard();
    }
    
}
