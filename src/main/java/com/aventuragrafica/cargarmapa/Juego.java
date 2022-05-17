package com.aventuragrafica.cargarmapa;

import com.aventuragrafica.objetos.Sala;
import com.aventuragrafica.ventanas.PantallaJuego;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import javax.swing.JOptionPane;

/**
 * Esta clase se encarga de cargar el juego e iniciarlo.
 * 
 * @author javier
 */
public class Juego {
    private LinkedHashMap<String, Sala> salas;

    public Juego() {
        salas = new LinkedHashMap();
    }
    
    /**
     * Este método inicializa el juego con el mapa por defecto.
     */
    public void juego(){
        Mapa cm;
        Sala salaInicial, salaFinal;
        PantallaJuego pj;
        
        try {
            cm = new Mapa(salas);
            cm.cargarMapa();
            
            salaInicial = salas.get(salas.keySet().iterator().next());
            salaFinal = prepararSalaFinal();
            
            pj = new PantallaJuego(salaInicial, salaFinal, cm.getCantOrdenadores());
            pj.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo iniciar el juego.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Este juego carga el juego con un mapa personalizado.
     * 
     * @param mapa El mapa que se quiere cargar.
     * @return Se cargó el juego correctamente.
     */
    public boolean juego(File mapa){
        Mapa cm;
        Sala salaInicial, salaFinal;
        PantallaJuego pj;
        
        try {
            cm = new Mapa(salas, mapa);
            cm.cargarMapa();
            
            salaInicial = salas.get(salas.keySet().iterator().next());
            salaFinal = prepararSalaFinal();
            
            pj = new PantallaJuego(salaInicial, salaFinal, cm.getCantOrdenadores());
            pj.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo iniciar el juego.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }
    
    /**
     * Este método obtiene la sala final y la devuelve.
     * 
     * @return La sala final.
     */
    private Sala prepararSalaFinal(){
        int con = 0;
        Sala salaFinal = null;
        
        for (String nombre : salas.keySet()) {
            if (con < (salas.size() - 1)){
                con++;
            }else{
                salaFinal = salas.get(nombre);
            }
        }
        
        salaFinal.setDescripcion("Terminaste el laberinto, felicidades.");
        
        salaFinal.setPerspectivaE("/cuartos/sin_ordenador/CF.png");
        salaFinal.setPerspectivaO("/cuartos/sin_ordenador/CF.png");
        salaFinal.setPerspectivaS("/cuartos/sin_ordenador/CF.png");
        salaFinal.setPerspectivaN("/cuartos/sin_ordenador/CF.png");
        
        return salaFinal;
    }
}
