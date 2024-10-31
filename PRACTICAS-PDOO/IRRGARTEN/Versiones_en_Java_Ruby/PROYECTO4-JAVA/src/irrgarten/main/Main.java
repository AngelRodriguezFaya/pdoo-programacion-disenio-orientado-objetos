
package irrgarten.main;
import irrgarten.Game;
import irrgarten.vista.TextUI;
import irrgarten.controller.Controller;
import java.util.Scanner;

/**
 *
 * @author angel_rodriguez
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        System.out.println("\t-----------------------------------------------");
        System.out.println("\t          Bienvenido a IRRGARTEN             ");
        System.out.println("\t-----------------------------------------------");

        System.out.println("\n¡Que comienze la partida, buena suerte! :)\n");

        
        TextUI vista            = new TextUI();
        Game juego              = new Game(2);
        Controller controlador  = new Controller(juego, vista);
        controlador.play();
    }
    
}
