
package vista;

import controlador.Controlador;
import java.awt.event.ActionEvent;
import modelo.TipoCultivo;

/**
 *
 * @author angel_rodriguez
 */
public class BotonCultivar extends javax.swing.JButton{
    //-------------------------------------------------
    // Atributos
    //-------------------------------------------------
    private VistaParcela parcelaView;
    private TipoCultivo tipoCultivo;
    
    //-------------------------------------------------
    // Constructores
    //-------------------------------------------------
    public BotonCultivar(VistaParcela pv, TipoCultivo tipoCultivo, String rutaIcono){
        this.parcelaView = pv;
        this.tipoCultivo = tipoCultivo;
        this.setText("Producir");
        this.setIcon(new javax.swing.ImageIcon(getClass().getResource(rutaIcono)));
        
        this.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCultivarActionPerformed(evt);
            }
        });
    }
    
    //-------------------------------------------------
    // Getters && Setters
    //-------------------------------------------------
    public VistaParcela getParcelaView(){
        return this.parcelaView;
    }
    public TipoCultivo getTipoCultivo(){
        return this.tipoCultivo;
    }
    
    //-------------------------------------------------
    // Funciones
    //-------------------------------------------------    
    private void btnCultivarActionPerformed(ActionEvent evt) {
        Controlador.instancia.producir(this.parcelaView.getParcela(), this.tipoCultivo);
    }
    
}
