
package red_social.main;

import java.util.ArrayList;
import red_social.Contenido;
import red_social.Foto;
import red_social.Usuario;
import red_social.Video;

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
        
        //8.a
        ArrayList<Contenido> contenidos = new ArrayList<Contenido>();
        contenidos.add(new Foto(1280,720, 3, "Paisaje"));
        contenidos.add(new Video(3, 5, "Baile"));
        for(Contenido c: contenidos){
            c.visualizar();
        }
        
        //8.b
        Foto foto = (Foto) contenidos.get(0);
        foto.imprimir();
        
        //8.c
        Usuario usuario1 = new Usuario("usuario1");
        for(int i = 0; i < contenidos.size(); i++){
            usuario1.addLike(contenidos.get(i));
        }
        Usuario usuario2 = new Usuario(usuario1);
        
        //8.d
        System.out.println(usuario2.contenidoMasPesado().getPeso());
        
        //8.e
        
        
        
        
        
        
    }
    
    
}
