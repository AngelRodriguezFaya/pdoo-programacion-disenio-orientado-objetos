
package materiales;

/**
 *
 * @author angel_rodriguez
 */
public abstract class Material implements Cloneable{
    private static int total;
    public static final int MIN_PAG_EE = 300;
    private String titulo;
    private int numPaginas;

    public Material(String titulo, int numPaginas) {
        this.titulo = titulo;
        this.numPaginas = numPaginas;
        this.total++;
    }

    public static int getTotal() {
        return total;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getNumPaginas() {
        return numPaginas;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public boolean esEdicionEspecial(){
        return numPaginas >= MIN_PAG_EE;
    }
    
    public abstract void prestar();
    
    public abstract Material copia();

    @Override
    protected Material clone() throws CloneNotSupportedException {
        return copia();
    }  
    
}
