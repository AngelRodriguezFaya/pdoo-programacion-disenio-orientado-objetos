
package biblioteca;

/**
 * Clase Libro
 * @author Ángel Rodríguez Faya
 */ 

/**
 * IMPORTANTE!!!!!
 * He decidido cambiar el tipo del atributo de 'id' ya que veía más sencillo
 * que sea int en vez de string. El id de un libro es un número que lo identifica,
 * y que durante el programa se va a ir modificando con respecto a un contador,
 * por lo que si es int, es más sencillo.
 * @author Ángel Rodríguez Faya
 */
public class Libro {
    // Atributo de clase.
    private static int cuentaLibros = 0;
    
    // Atributos de instancia
    private String titulo;
    private String autor;
    private int id;
    private int anyoPublicacion;
    
    // Métodos 
    
    /**
     * Constructor paramétrico
     * @param titulo    título del libro
     * @param autor     autor del libro
     * @param anyoPublicacion año de publicación del libro.
     */
    public Libro(String titulo, String autor, int anyoPublicacion) {
        this.titulo          = titulo;
        this.autor           = autor;
        this.id              = cuentaLibros + 1;
        this.anyoPublicacion = anyoPublicacion;
        this.cuentaLibros++;
    }
    
    /**
     * Constructor por defecto
     */
    public Libro(){
        this("", "", 0);
    }
    
    /**
     * Constructor por copia
     * @param l objeto de tipo Libro
     */
    public Libro(Libro l){
        this(l.titulo, l.autor, l.anyoPublicacion);
    }
    
    /**
     * Get de cuentaLibros
     * @return int cuentaLibros
     */
    public static int getCuentaLibros() {
        return cuentaLibros;
    }
    
    /**
     * Get de titulo
     * @return String titulo del libro
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Get de autor
     * @return String autor del libro
     */
    public String getAutor() {
        return autor;
    }
    
    /**
     * Get de id
     * @return int id del libro
     */
    public int getId() {
        return id;
    }
    
    /**
     * Get de anyoPublicación
     * @return int año de publicación del libro
     */
    public int getAnyoPublicacion() {
        return anyoPublicacion;
    }
    
    /**
     * To String
     * @return String representación interna del libro
     */
    @Override
    public String toString() {
        return "Libro{" + "titulo=" + titulo + ", autor=" + autor 
                + ", id=" + id + ", anyoPublicacion=" + anyoPublicacion + '}';
    }           
    
}
