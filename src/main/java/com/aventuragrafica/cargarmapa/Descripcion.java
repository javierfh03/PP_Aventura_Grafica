package com.aventuragrafica.cargarmapa;

import com.aventuragrafica.objetos.Sala;
import java.util.Map;

/**
 * Esta clase establece la descripción de las salas teniendo
 * en cuenta el contenido que tienen.
 * 
 * @author javier
 */
public class Descripcion {
    private Map<String, Sala> mapa;

    public Descripcion(Map<String, Sala> mapa) {
        this.mapa = mapa;
    }
    
    /**
     * Este método carga a la salas del mapa con descripciones.
     */
    public void cargarDescripciones(){
        Sala sala;
        
        for (String nombre : mapa.keySet()) {
            sala = mapa.get(nombre);
            
            sala.setDescripcion(sacarDescripcion(sala));
        }
    }
    
    /**
     * Este método devuelve la descripción de la sala que
     * se le mete como parámetro.
     * 
     * @param sala La sala a la que se le sacará la descripción.
     * @return La descripción.
     */
    private String sacarDescripcion(Sala sala){
        int cantPuertas, cantPuertasBloqueadas;
        StringBuilder descripcion = new StringBuilder("");
        
        cantPuertas = contadorPuertas(sala);
        cantPuertasBloqueadas = contadorPuertasBloqueadas(sala);
        
        descripcion.append(cantidadPuertas(cantPuertas));
        descripcion.append(cantidadPuertasBloqueadas(cantPuertasBloqueadas));
        descripcion.append(establecerObjeto(sala));
        descripcion.append(".");
        
        return descripcion.toString();
    }

    /**
     * Este método devuelve si hay objeto en la sala.
     * 
     * @param sala La sala que se evaluará.
     * @return El fragmanto de la descripción.
     */
    private String establecerObjeto(Sala sala) {
        switch(sala.tieneObjeto()){
            case 'L':
                return ", hay una llave";
            case 'M':
                return ", hay un ordenador";
            case '0':
                return ", no hay objetos";
        }
        return "";
    }

    /**
     * Cuenta la cantidad de puertas bloqueadas que hay en la 
     * sala y devuelve el fragmanto de la descripción.
     * 
     * @param cantPuertasBloqueadas La cantidad de puertas bloqueadas.
     * @return El fragmanto de la descripción.
     */
    private String cantidadPuertasBloqueadas(int cantPuertasBloqueadas) {
        switch (cantPuertasBloqueadas) {
            case 4:
                return ", 4 están bloqueadas";
            case 3:
                return ", 3 están bloqueadas";
            case 2:
                return ", 2 están bloqueadas";
            case 1:
                return ", 1 está bloqueada";
        }
        return "";
    }

    /**
     * Cuenta la cantidad de puertas que hay en la 
     * sala y devuelve el fragmanto de la descripción.
     * 
     * @param cantPuertasLa cantidad de puertas.
     * @return El fragmanto de la descripción.
     */
    private String cantidadPuertas(int cantPuertas) {
        switch (cantPuertas) {
            case 4:
                return "Hay 4 puertas";
            case 3:
                return "Hay 3 puertas";
            case 2:
                return "Hay 2 puertas";
            case 1:
                return "Hay 1 puerta";
            default:
                return "No hay salida";
        }
    }

    /**
     * Esta función cuenta la cantidad de puertas que
     * hay en la sala.
     * 
     * @param sala La sala a la que se le contarán las puertas.
     * @return La cantidad de puertas.
     */
    private int contadorPuertas(Sala sala) {
        int cantPuertas = 0;
                
        if (sala.getPuertaN() > 0){
            cantPuertas++;
        }
        if (sala.getPuertaS() > 0){
            cantPuertas++;
        }
        if (sala.getPuertaO() > 0){
            cantPuertas++;
        }
        if (sala.getPuertaE() > 0){
            cantPuertas++;
        }
        
        return cantPuertas;
    }
    
    /**
     * Esta función cuenta la cantidad de puertas que
     * hay en la sala.
     * 
     * @param sala La sala a la que se le contarán las puertas 
     * que están bloqueadas.
     * @return La cantidad de puertas.
     */
    private int contadorPuertasBloqueadas(Sala sala) {
        int cantPuertasBloqueadas = 0;
        
        if (sala.getPuertaN() > 1){
            cantPuertasBloqueadas++;
        }
        if (sala.getPuertaS() > 1){
            cantPuertasBloqueadas++;
        }
        if (sala.getPuertaO() > 1){
            cantPuertasBloqueadas++;
        }
        if (sala.getPuertaE() > 1){
            cantPuertasBloqueadas++;
        }
        
        return cantPuertasBloqueadas;
    }
}
