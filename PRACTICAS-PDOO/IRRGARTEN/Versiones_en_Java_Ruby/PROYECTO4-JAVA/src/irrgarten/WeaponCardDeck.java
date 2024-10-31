
package irrgarten;

import java.util.ArrayList;

/**
 *
 * @author angel_rodriguez
 */
public class WeaponCardDeck extends CardDeck<Weapon>{
    public static final int MAX_WEAPON_CARDS = 4;

    public WeaponCardDeck(ArrayList<Weapon> cardDeck) {
        super(cardDeck);
    }

    @Override
    protected void addCards() {
        for(int i = 0; i < MAX_WEAPON_CARDS; i++)
            addCard(new Weapon(Dice.weaponPower(), Dice.usesLeft()));
    }
    
}
