
package irrgarten;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Clase paramétrica 
 * @author angel_rodriguez
 */
public abstract class CardDeck<T> {
    //---------------------------------
    // Atributes
    //---------------------------------
    private ArrayList<T> cardDeck;
    
    //---------------------------------
    // Constructors
    //---------------------------------
    public CardDeck(ArrayList<T> cardDeck) {
        this.cardDeck = cardDeck;
    }

    //---------------------------------
    // Getters & Setters
    //---------------------------------
    
    //---------------------------------
    // Public Methods
    //---------------------------------
    public T nextCard(){
        if(this.cardDeck.isEmpty()){
            addCards();
            // Se pasa un objeto al método shuffle que baraja la colección.
            Collections.shuffle(cardDeck); 
        }
        T card = this.cardDeck.get(0);
        this.cardDeck.remove(card);
        return card;
    }
    
    //---------------------------------
    // Protected Methods
    //---------------------------------
    protected abstract void addCards();
    
    protected void addCard(T card){
        this.cardDeck.add(card);
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
}
