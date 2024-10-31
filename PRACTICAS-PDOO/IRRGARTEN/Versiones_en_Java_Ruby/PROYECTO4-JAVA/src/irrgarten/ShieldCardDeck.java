
package irrgarten;

import static irrgarten.WeaponCardDeck.MAX_WEAPON_CARDS;
import java.util.ArrayList;

/**
 *
 * @author angel_rodriguez
 */
public class ShieldCardDeck extends CardDeck<Shield>{
    public static final int MAX_SHIIELDS_CARDS = 5;

    public ShieldCardDeck(ArrayList<Shield> cardDeck) {
        super(cardDeck);
    }
    
    @Override
    protected void addCards() {
        for(int i = 0; i < MAX_SHIIELDS_CARDS; i++)
            addCard(new Shield(Dice.shieldPower(), Dice.usesLeft()));
    }
        
}
