
package biblioteca;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Biblioteca
 * @author Ángel Rodríguez Faya
 */
public class Biblioteca {
    public static final int MAX_COPIAS_POR_TITULO = 3;
    private String nombre;
    private List<Libro> libros;
    
    /**
     * Constructor paramétrico.
     * @param nombre        nombre de la biblioteca
     * @param otrosLibros   contenedor de libros
     */
    private Biblioteca(String nombre, List<Libro> otrosLibros) {
        this.nombre = nombre;
        this.libros = new ArrayList<Libro>();
        for(Libro l : otrosLibros)
            this.libros.add(new Libro(l));
    }
    
    /**
     * Constructor con un parámetro
     * @param nombre nombre de la biblioteca
     */
    public Biblioteca(String nombre){
        this(nombre, new ArrayList<Libro>());
    }    
    
    /**
     * Constructor por defecto
     */
    public Biblioteca(){
        this("", new ArrayList<Libro>());
    }
    
    /**
     * constructor por copia
     * @param b objeto de tipo Biblioteca
     */
    public Biblioteca(Biblioteca b){
        this(b.nombre, b.libros);
    }
    
    /**
     * Cuenta las copias que hay de un libro en una biblioteca, según el título del libro.
     * @param titulo titulo del libro
     * @return int número de copias
     */
    public int contarCopias(String titulo){
        int copias = 0;
        for(Libro l : libros){
            if(l.getTitulo().equals(titulo))
                copias++;
        }
        return copias;
    }
    
    /**
     * Añade un libro a la biblioteca siempre y cunado no haya superado el máximo
     * de copias de un libro para una misma librería.
     * @param titulo título del libro
     * @param autor  autor del libro
     * @param anyo   año de publicación
     * @return true si ha podido añadirlo
     *         false si no ha podido añadirlo
     */
    public boolean addLibro(String titulo, String autor, int anyo){
        int exito = contarCopias(titulo);
        /*
        exito < MAX_COPIAS_POR_TITULO exito debe ser menor que el maximo de 
        copias por titulo porque si fuera igual, al añadir un nuevo libro con el mismo
        título, ya no sería válido, pues superaría el máximo.
        */
        if(exito < MAX_COPIAS_POR_TITULO){  
            Libro libro = new Libro(titulo, autor, anyo);
            libros.add(libro);
            System.out.println("\nAñadido correctamente el título titulado: " +
                    titulo + "\n");
        }else{
            System.out.println("\nNo se puede añadir el libro titulado: " + titulo 
                    + ". Se ha alcanzado el número máximo de copias " +
                    "para este título.\n");
        }
        return (exito < MAX_COPIAS_POR_TITULO);
    }
    
    /**
     * Devuelve un Libro que hemos buscado por el título.
     * @param titulo titulo del libro
     * @return libro
     */
    public Libro buscarLibro(String titulo){
        for(Libro l : libros){
            if(l.getTitulo().equals(titulo)){
                return l;
            }
        }
        return null;
    }
    
    /**
     * Elimina un libro, lo busca por su título.
     * @param titulo título del libro.
     * @return true si se ha podido eliminar
     *          false si no se ha podido eliminar.
     */
    public boolean eliminarLibro(String titulo){
        if(buscarLibro(titulo) != null)
            libros.remove(buscarLibro(titulo));
        
        return buscarLibro(titulo) != null;       
    }
    
    /**
     * get de numTotalLibros
     * @return total de libros
     */
    public int getNumTotalLibros(){
        return libros.size();
    }
    
    /**
     * get de libros
     * @return contendor de libros
     */
    public List<Libro> getLibros() {
        return libros;
    }
    
    /**
     * get del nombre
     * @return nombre del libro
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Set del nombre del libro
     * @param nombre nombre del libro
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Biblioteca{" + "nombre=" + nombre + ", libros=" + libros + '}';
    }
      
}
