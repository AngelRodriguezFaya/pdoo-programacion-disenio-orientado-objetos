
package materiales;

/**
 *
 * @author angel_rodriguez
 */
public class Libro extends Material{
    public static final int MAX_PAG_EE = 500;
    private String isbn;

    public Libro(String isbn, String titulo, int numPaginas) {
        super(titulo, numPaginas);
        this.isbn = isbn;
    }
    
    public Libro(Libro libro){
        this(libro.isbn, libro.getTitulo(), libro.getNumPaginas());
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public void prestar() {
        System.out.println("Se ha presatdo el libro " + getTitulo() + " cuyo isbn es "
                + getIsbn());
    }
    
    @Override
    public boolean esEdicionEspecial(){
        return (super.esEdicionEspecial()) && (getNumPaginas() <= MAX_PAG_EE); 
    }

    @Override
    public Material copia() {
        return (new Libro(this));
    }
}
