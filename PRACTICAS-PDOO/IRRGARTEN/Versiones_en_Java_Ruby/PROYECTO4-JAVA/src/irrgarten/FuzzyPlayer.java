
package irrgarten;

/**
 *
 * @author angel_rodriguez
 */
public class FuzzyPlayer extends Player{

    public FuzzyPlayer(Player o) {
        super(o);
    }

    public String toString() {
        return "FuzzyPlayer{" + "name= Player #" + getNumber() + ", number= " + getNumber()
                + ", intelligence= " + getIntelligence() + ", strength= " 
                + getStrength() + ", health= " + getHealth();
    }
    
    
    
}
