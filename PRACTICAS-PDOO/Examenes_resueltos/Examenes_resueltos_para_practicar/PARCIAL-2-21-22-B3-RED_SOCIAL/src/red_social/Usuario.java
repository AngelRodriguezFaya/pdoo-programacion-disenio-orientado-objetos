
package red_social;
import java.util.ArrayList;
/**
 *
 * @author angel_rodriguez
 */
public class Usuario {
    private String nombre;
    private ArrayList<Contenido> likes;
    private static ArrayList<Usuario> usuarios = new ArrayList<>();
    
    public Usuario(String nombre) {
        this.nombre = nombre;
        this.likes = new ArrayList<Contenido>();
        Usuario.usuarios.add(this);
    }
    
    //Nos ayudamos del metodo ABSTRACTO copiar() de la clase Contenido.
    public Usuario(Usuario usuario){
        this(usuario.nombre);
        for(Contenido cont: usuario.likes){
            this.likes.add(cont.copia());
        }
    }

    public String getNombre() {
        return nombre;
    } 
    
    //OJO hay que tener cuidado de que el nombre no este ya registrado.
    public void setNombre(String nombre) {
        boolean nombreDuplicado = false;
        for(Usuario usu: Usuario.usuarios){
            nombreDuplicado = (usu.nombre.equals(nombre)) && (usu != this);
            if(nombreDuplicado){
                System.out.println("El nombre ya está registrado");
                break;
            }
        }
        if(!nombreDuplicado)
            this.nombre = nombre;
    }
    
    public ArrayList<Contenido> getLikes(){
        return this.likes;
    }
    
    public void addLike(Contenido contenido){
        this.likes.add(contenido);   // Al usuario le añadimos el contenido
        contenido.addLike(this); // Al contenido le añadimos el usuario
    }
    
    public static Usuario usuarioMasActivo(){
        int masLikes = 0;
        Usuario usuarioMasActivo = null;
        for(int i = 0; i < usuarios.size(); i++){
            if(usuarios.get(i).getLikes().size() > masLikes){
                masLikes = usuarios.get(i).getLikes().size();
                usuarioMasActivo = usuarios.get(i);
            }
        }
        return usuarioMasActivo;
    }
    
    public Contenido contenidoMasPesado(){
        int mayorPeso = Integer.MIN_VALUE;
        Contenido masPesado = null;
        for(int i = 0; i < likes.size(); i++){
            if(likes.get(i).getPeso() > mayorPeso){
                mayorPeso = likes.get(i).getPeso();
                masPesado = likes.get(i);
            }
        }
        return masPesado;
    } 
    
    
    
    
}
