
package simulacro;

/**
 * Programa principal.
 * @author angel_rodriguez
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        /*
        a) Declare un volcán de nombre «Cumbre Vieja» y añada al volcán tres coladas: 
           - la primera por defecto, 
           - la segunda con una longitud de 10 y que ha llegado al mar 
           - la tercera con una longitud de 12 y que no ha llegado al mar.
        */
        Volcan volcan = new Volcan("CumbreVieja");
        
        volcan.addColada();
        volcan.addColada(10, true);
        volcan.addColada(12, false);
        
        /*
        b) Partiendo del volcán, muestre por consola la longitud de su segunda 
           colada, utilizando el método implementado en el ejercicio 5.
        */
        Colada colada2 = volcan.getColada(1);
        System.out.println("\nLa longitud de la 2ª colada es: " + colada2.getLongitud());
         
        // c) Partiendo del volcán, establezca que la tercera colada ha llegado al mar.
        Colada colada3 = volcan.getColada(2);
        colada3.setLlegaMar();
        System.out.println("\nAhora la colada 3 llega al mar: " + colada3.getLlegaMar());
        
        // d) Muestre por consola la información (método informacion()) del volcán.
        System.out.println("\nMétodo información: " + volcan.informacion() + "\n");
        
    }
    
}
