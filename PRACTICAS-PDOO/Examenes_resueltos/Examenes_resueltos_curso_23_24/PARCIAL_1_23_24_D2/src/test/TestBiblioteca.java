
package test;
import biblioteca.Biblioteca;
import biblioteca.Libro;

/**
 * Programa principal
 * @author Ángel Rodríguez Faya
 */
public class TestBiblioteca {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        System.out.println("Ejercicio 5.1");
        Biblioteca biblioteca = new Biblioteca("Biblioteca Ciencias");
        
        System.out.println("\n\nEjercicio 5.2");
        biblioteca.addLibro("1984", "George Orwell", 1949);
        biblioteca.addLibro("Un mundo feliz", "Aldous Huxley", 1932);
        
        System.out.println("\n\nEjercicio 5.3");
        final int copiasLibro = 5;
        for(int i = 0; i < copiasLibro; i++)
            biblioteca.addLibro("1984", "George Orwell", 1949);
        
        System.out.println("\n\nEjercicio 5.4");
        System.out.println("Número total de libros que tiene la biblioteca " + 
                " de ciencias: " + biblioteca.getNumTotalLibros());
        
        System.out.println("\n\nEjercicio 5.5");
        System.out.println("Número total de libros del sistema informático: " + 
                Libro.getCuentaLibros());
    
        System.out.println("\n\nEjercicio M.H");
        Biblioteca biblio_etsiit = new Biblioteca(biblioteca);
        biblio_etsiit.setNombre("Biblioteca ETSIIT");
        biblio_etsiit.addLibro("Piensa en Java", "Bruce Eckel", 2007);
        System.out.println("Número total de libros que tiene la biblioteca " + 
                " de la ETSIIT: " + biblio_etsiit.getNumTotalLibros());
        System.out.println("Número total de libros del sistema informático: " + 
                Libro.getCuentaLibros());

    }
    
    
}
