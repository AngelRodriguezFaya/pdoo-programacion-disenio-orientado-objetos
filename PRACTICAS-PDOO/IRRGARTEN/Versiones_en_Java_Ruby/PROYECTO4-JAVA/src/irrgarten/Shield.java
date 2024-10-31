
/**
 * Esta clase representa los escudos que utiliza el jugador cuando se defiende 
 * de un ataque de un monstruo.
 * @file Shield.java
 * @author angel_rodriguez
 */

package irrgarten;

public class Shield extends CombatElement{

    /**
     * Constructor con parámetros
     * @param protection protección del escudo.
     * @param uses usos que le quedan al escudo.
     */
    public Shield (float protection, int uses){
        super(protection, uses);
    }
    
    /**
     * Constructor por defecto.
     */
    public Shield () {
        this(0f, 0);
    }
    
    /**
     * Devuelve un número representando la intensidad de defensa del jugador.
     * Sólo devolverá un número si uses > 0, si no, devuelve 0;
     * @return float representando la protección.
     */
    public float protect () {
        return super.produceEffect();
    }
    
    /**
     * Método toString.
     * @return representación del estado interno del arma. S[protection, uses]
     */
    @Override
    public String toString () {     
        return "Shield: " + super.produceEffect();
 
    }  
    
//    /**
//     * Implementa la decisión de si el escudo debe ser descartado.
//     * Delega en el método discardElement de la clase Dice.
//     * Hay que pasarle como parámetros la cantidad de usos restantes del escudo.
//     * @see Dice.discardElement(usesLeft).
//     * @return boolean si el escudo se descarta o no.
//     */
//    public boolean discard(){
//        return Dice.discardElement(uses);
//    }
    
}
