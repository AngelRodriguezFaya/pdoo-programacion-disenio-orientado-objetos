
package irrgarten.test;

import irrgarten.Dice;
import irrgarten.Game;
import irrgarten.GameState;
import irrgarten.Labyrinth;
import irrgarten.Monster;
import irrgarten.Player;
import irrgarten.Shield;
import irrgarten.Weapon;

/**
 * Test sobre todos los métodos y prácticas que se ven en la práctica 2 de PDOO.
 * @author angel_rodriguez
 */
public class TestP2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        System.out.println("\n\n---------------TEST P1-----------------\n\n");
        Weapon weapon1 = new Weapon(9, 8);
        Shield shield1 = new Shield(16, 4);
        GameState gameState1 = new GameState("open", "good", 
                "bored", 2, Boolean.TRUE, "GOOO");
        
        // Pruebo la clase Weapon.
        System.out.println("Prueba de la clase Weapon: ");
        System.out.println(weapon1.attack());
        System.out.println(weapon1.toString());
        System.out.println(weapon1.discard());
        
        // Pruebo la clase Shield.
        System.out.println("\nPrueba de la clase Shield: ");
        System.out.println(shield1.protect());
        System.out.println(shield1.toString());
        System.out.println(shield1.discard());

        // Pruebo la clase Dice.
        System.out.println("\nPrueba de la clase Dice: ");     
        System.out.println("randomPos: " + Dice.randomPos(3));
        System.out.println("whoStarts: " + Dice.whoStarts(4));
        System.out.println("randomIntelligence: " + Dice.randomIntelligence());
        System.out.println("randomStrength(): " + Dice.randomStrength());
        System.out.println("resurrectPlayer: " + Dice.resurrectPlayer());
        System.out.println("weaponsReward: " + Dice.weaponsReward());
        System.out.println("shieldsReward: " + Dice.shieldsReward());
        System.out.println("healthReward: " + Dice.healthReward());
        System.out.println("weaponPower: " + Dice.weaponPower());
        System.out.println("shieldPower: " + Dice.shieldPower());
        System.out.println("usesLeft: " + Dice.usesLeft());
        System.out.println("intensity: " + Dice.intensity(2));
        System.out.println("discardElement: " + Dice.discardElement(3));

        // Pruebo la clase GameState.
        System.out.println("\nPrueba de la clase GameState: ");     
        System.out.println(gameState1.getLabyrinthv());
        System.out.println(gameState1.getPlayers());
        System.out.println(gameState1.getMonsters());
        System.out.println(gameState1.getCurrentPlayer());
        System.out.println(gameState1.getWinner());
        System.out.println(gameState1.getLog());
        
        
        System.out.println("\n\n---------------TEST P2-----------------\n");
        
        
        // Pruebo la clase Monster.
        Monster monster1 = new Monster("monster1", 2, 2);
        System.out.println("\nPrueba de la clase Monster: \n");     
        System.out.println(gameState1.getLabyrinthv());
        System.out.println("dead: " + monster1.dead());
        System.out.println("attack: " + monster1.attack());
        monster1.setPos(1, 1);
        System.out.println("toString: " + monster1.toString());
        
        // Pruebo la clase Player
        Player player1 = new Player('1', 2, 2);
        System.out.println("\nPrueba de la clase Player: \n"); 
        player1.resurrect();
        player1.setPos(1, 5);
        System.out.println("dead: " + player1.dead());
        System.out.println("attack: " + player1.attack());
        System.out.println("defend: " + player1.defend(0));
        System.out.println("toString: " + player1.toString());
//        System.out.println("dead: " + monster1.dead());
//        System.out.println("dead: " + monster1.dead());
//        System.out.println("dead: " + monster1.dead());


        
        
        
        
        

        
    }
    
}
