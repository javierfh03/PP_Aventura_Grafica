package com.aventuragrafica.objetos;

import com.aventuragrafica.cargarmapa.Descripcion;
import com.aventuragrafica.cargarmapa.Imagenes;
import com.aventuragrafica.ventanas.PantallaJuego;
import java.util.HashMap;
import javax.swing.ImageIcon;

/**
 * Esta clase es una llave con la que se pueden
 * desbloquear puertas en el laberinto.
 * 
 * @author javier
 */
public class Llave extends Objeto{
    private char color;
    private String imagenLlave;

    /**
     * Constructor de la llave.
     * 
     * @param color Color de la llave (B, Y, R o V)
     */
    public Llave(char color) {
        super(new Sala(), 'S');
        
        this.color = color;
        switch (color) {
            case 'B':
                this.imagenLlave = "/objetos/llave_azul.png";
                break;
            case 'Y':
                this.imagenLlave = "/objetos/llave_amarilla.png";
                break;
            case 'R':
                this.imagenLlave = "/objetos/llave_roja.png";
                break;
            case 'V':
                this.imagenLlave = "/objetos/llave_verde.png";
                break;
        }
        
    }

    public char getColor() {
        return color;
    }

    public String getImagenLlave() {
        return imagenLlave;
    }
    
    /**
     * Este método se encarga de que se recoja una
     * llave en una ventana.
     * 
     * @param ventana La ventana en la que se recogerá la llave.
     */
    public void recogerLlave(PantallaJuego ventana){
        Sala sala = ventana.getSalaMostrar();
        Sala salaReferencia = sala.getObjetoSala().getReferencia();
        HashMap<String, Sala> listaActualizar = new HashMap();
        Descripcion ed = new Descripcion(listaActualizar);
        Imagenes ei = new Imagenes(listaActualizar);
        
        listaActualizar.put(sala.getNombre(), sala);
        listaActualizar.put(salaReferencia.getNombre(), salaReferencia);
        
        desbloquearPuerta();
        mostrarLlave(ventana);
        sala.setObjetoSala(null);
        
        ei.cargarImagenes();
        ed.cargarDescripciones();
        
        ventana.cambiarImagen();
    }
    
    /**
     * Este método se encarga de que en caso de que se recoja
     * una llave, se muestre en la ventana.
     * 
     * @param ventana La ventana en la que se mostrará la llave.
     */
    private void mostrarLlave(PantallaJuego ventana){
        switch (color){
            case 'B':
                ventana.getjLlaveAzul().setIcon(new ImageIcon(getClass().getResource(getImagenLlave())));
                break;
            case 'Y':
                ventana.getjLlaveAmarilla().setIcon(new ImageIcon(getClass().getResource(getImagenLlave())));
                break;
            case 'R':
                ventana.getjLlaveRoja().setIcon(new ImageIcon(getClass().getResource(getImagenLlave())));
                break;
            case 'V':
                ventana.getjLlaveVerde().setIcon(new ImageIcon(getClass().getResource(getImagenLlave())));
                break;
        }
    }
}
