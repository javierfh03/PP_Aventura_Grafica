package com.aventuragrafica.objetos;

import com.aventuragrafica.cargarmapa.Descripcion;
import com.aventuragrafica.cargarmapa.Imagenes;
import com.aventuragrafica.ventanas.PantallaJuego;
import com.aventuragrafica.ventanas.PantallaOrdenador;
import java.util.HashMap;

/**
 * Esta clase  simula un ordenador el cual tiene una pregunta 
 * y hay que resolverla con una respuesta.
 * 
 * @author javier
 */
public class Ordenador extends Objeto{
    private String pregunta;
    private String respuesta;

    public Ordenador() {
        super(new Sala(), 'N');
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getPregunta() {
        return pregunta;
    }

    public String getRespuesta() {
        return respuesta;
    }
    
    /**
     * Este método inicia la pantalla del ordenador.
     * 
     * @param ventana La ventana de juego padre.
     */
    public void usarOrdenador(PantallaJuego ventana){
        PantallaOrdenador po = new PantallaOrdenador(ventana, true, this);
       
        po.setVisible(true);    
    }
    
    /**
     * Este método desbloquea la puerta de la sala a la que hace
     * referencia el ordenador y elimina el ordenador de la sala.
     * 
     * @param ventana La ventana en la que se encuentra el ordenador.
     */
    public void desbloquearOrdenador(PantallaJuego ventana) {
        HashMap<String, Sala> listaActualizar = new HashMap();
        Descripcion ed = new Descripcion(listaActualizar);
        Imagenes ei = new Imagenes(listaActualizar);
        Sala sala = ventana.getSalaMostrar();
        Sala salaReferencia = sala.getObjetoSala().getReferencia();
        
        listaActualizar.put(sala.getNombre(), sala);
        listaActualizar.put(salaReferencia.getNombre(), salaReferencia);

        super.desbloquearPuerta();
        sala.setObjetoSala(null);

        ei.cargarImagenes();
        ed.cargarDescripciones();

        ventana.aumentarContadorOrdenadores();
        ventana.cambiarImagen();
    }
}
