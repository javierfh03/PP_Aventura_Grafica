package com.aventuragrafica.cargarmapa;

import com.aventuragrafica.objetos.Llave;
import com.aventuragrafica.objetos.Sala;
import java.util.Map;

/**
 * Esta clase establece las imágenes de las salas teniendo
 * en cuenta su perspectiva.
 * 
 * @author javier
 */
public class Imagenes {
    private Map<String, Sala> mapa;
    
    public Imagenes(Map<String, Sala> mapa) {
        this.mapa = mapa;
    }
    
    /**
     * Este método se encarga de cargar las imágenes del
     * mapa.
     */
    public void cargarImagenes(){
        Sala sala;
        
        for (String o : mapa.keySet()) {            
            sala = mapa.get(o);
            
            switch(sala.tieneObjeto()){
                case '0':
                case 'M':
                    salaNormal(sala);
                    break;
                case 'L':
                    salaLlave(sala);
                    break;
            }
        }
    }

    /**
     * Este método carga las imágenes del mapa que tengan
     * una llave.
     * 
     * @param sala La sala a la que se le cargarán las imágenes.
     */
    private void salaLlave(Sala sala) {
        Llave l = (Llave) sala.getObjetoSala();
        
        if (sala.getPerspectivaN() == null && sala.getSalidaN() != null){
            llavePerspectivaNorte(l, sala);
        }else if (sala.getPerspectivaO() == null && sala.getSalidaO() != null){
            llavePerspectivaOeste(l, sala);
        }else if (sala.getPerspectivaE() == null && sala.getSalidaE() != null){
            llavePerspectivaEste(l, sala);
        }else if (sala.getPerspectivaS() == null && sala.getSalidaS() != null){
            llavePerspectivaSur(l, sala);
        }
    }

    /**
     * Este método carga las imágenes del mapa que tengan
     * o no un ordenador.
     * 
     * @param sala 
     */
    private void salaNormal(Sala sala) {
        for (int i = 0; i < 4; i++) {
            switch(i){
                case 0:
                    asignarPerspectivaNorte(sala);
                    break;  
                case 1:
                    asignarPerspectivaSur(sala);
                    break;  
                case 2:
                    asignarPerspectivaEste(sala);
                    break;   
                case 3:
                    asignarPerspectivaOeste(sala);
                    break;
            }
        }
    }

    /**
     * Asigna la perspectiva oeste de una sala.
     * 
     * @param sala La sala a la que se le quiere poner la perspectiva.
     */
    private void asignarPerspectivaOeste(Sala sala) {
        if (sala.getPuertaN() > 0 && sala.getPuertaE() > 0 && sala.getPuertaS() > 0){
            asignarPerspectivaOesteEsNoSu(sala, sala.tieneObjeto());
        }else if (sala.getPuertaN() > 0 && sala.getPuertaE() > 0){
            asignarPerspectivaOesteEsNo(sala, sala.tieneObjeto());
        }else if (sala.getPuertaE() > 0 && sala.getPuertaS() > 0){
            asignarPerspectivaOesteSuEs(sala, sala.tieneObjeto());
        }else if (sala.getPuertaN() > 0 && sala.getPuertaS() > 0){
            asignarPerspectivaOesteNoSu(sala, sala.tieneObjeto());
        }else if (sala.getPuertaE() > 0){
            asignarPerspectivaOesteEs(sala, sala.tieneObjeto());
        }else if (sala.getPuertaN() > 0){
            asignarPerspectivaOesteNo(sala, sala.tieneObjeto());
        }else if (sala.getPuertaS() > 0){
            asignarPerspectivaOesteSu(sala, sala.tieneObjeto());
        }else{
            if (sala.tieneObjeto() == 'M'){
                sala.setPerspectivaO("/cuartos/ordenador/C.png");
            }else{
                sala.setPerspectivaO("/cuartos/sin_ordenador/C.png");
            }
        }
    }

    /**
     * Asigna la perspectiva este de una sala.
     * 
     * @param sala La sala a la que se le quiere poner la perspectiva.
     */
    private void asignarPerspectivaEste(Sala sala) {
        if (sala.getPuertaN() > 0 && sala.getPuertaO() > 0 && sala.getPuertaS() > 0){
            asignarPerspectivaEsteOeNoSu(sala, sala.tieneObjeto());
        }else if (sala.getPuertaN() > 0 && sala.getPuertaS() > 0){
            asignarPerspectivaEsteNoSu(sala, sala.tieneObjeto());
        }else if (sala.getPuertaO() > 0 && sala.getPuertaS() > 0){
            asignarPerspectivaEsteSuOe(sala, sala.tieneObjeto());
        }else if (sala.getPuertaO() > 0 && sala.getPuertaN() > 0){
            asignarPerspectivaEsteOeNo(sala, sala.tieneObjeto());
        }else if (sala.getPuertaO() > 0){
            asignarPerspectivaEsteOe(sala, sala.tieneObjeto());
        }else if (sala.getPuertaN() > 0){
            asignarPerspectivaEsteNo(sala, sala.tieneObjeto());
        }else if (sala.getPuertaS() > 0){
            asignarPerspectivaEsteSu(sala, sala.tieneObjeto());
        }else{
            if (sala.tieneObjeto() == 'M'){
                sala.setPerspectivaE("/cuartos/ordenador/C.png");
            }else{
                sala.setPerspectivaE("/cuartos/sin_ordenador/C.png");
            }
        }
    }

    
    /**
     * Asigna la perspectiva sur de una sala.
     * 
     * @param sala La sala a la que se le quiere poner la perspectiva.
     */
    private void asignarPerspectivaSur(Sala sala) {
        if (sala.getPuertaE() > 0 && sala.getPuertaO() > 0 && sala.getPuertaN() > 0){
            asignarPerspectivaSurOeNoEs(sala, sala.tieneObjeto());
        }else if (sala.getPuertaE() > 0 && sala.getPuertaO() > 0){
            asignarPerspectivaSurOeEs(sala, sala.tieneObjeto());
        }else if (sala.getPuertaO() > 0 && sala.getPuertaN() > 0){
            asignarPerspectivaSurOeNo(sala, sala.tieneObjeto());
        }else if (sala.getPuertaE() > 0 && sala.getPuertaN() > 0){
            asignarPerspectivaSurEsNo(sala, sala.tieneObjeto());
        }else if (sala.getPuertaO() > 0){
            asignarPerspectivaSurOe(sala, sala.tieneObjeto());
        }else if (sala.getPuertaE() > 0){
            asignarPerspectivaSurEs(sala, sala.tieneObjeto());
        }else if (sala.getPuertaN() > 0){
            asignarPerspectivaSurNo(sala, sala.tieneObjeto());
        }else{
            if (sala.tieneObjeto() == 'M'){
                sala.setPerspectivaS("/cuartos/ordenador/C.png");
            }else{
                sala.setPerspectivaS("/cuartos/sin_ordenador/C.png");
            }
        }
    }
    
    /**
     * Asigna la perspectiva norte de una sala.
     * 
     * @param sala La sala a la que se le quiere poner la perspectiva.
     */
    private void asignarPerspectivaNorte(Sala sala) {
        if (sala.getPuertaE() > 0 && sala.getPuertaO() > 0 && sala.getPuertaS() > 0){
            asignarPerspectivaNorteOeSuEs(sala, sala.tieneObjeto());
        }else if (sala.getPuertaE() > 0 && sala.getPuertaO() > 0){
            asignarPerspectivaNorteOeEs(sala, sala.tieneObjeto());
        }else if (sala.getPuertaO() > 0 && sala.getPuertaS() > 0){
            asignarPerspectivaNorteOeSu(sala, sala.tieneObjeto());
        }else if (sala.getPuertaE() > 0 && sala.getPuertaS() > 0){
            asignarPerspectivaNorteEsSu(sala, sala.tieneObjeto());
        }else if (sala.getPuertaO() > 0){
            asignarPerspectivaNorteOe(sala, sala.tieneObjeto());
        }else if (sala.getPuertaE() > 0){
            asignarPerspectivaNorteEs(sala, sala.tieneObjeto());
        }else if (sala.getPuertaS() > 0){
            asignarPerspectivaNorteSu(sala, sala.tieneObjeto());
        }else{
            if (sala.tieneObjeto() == 'M'){
                sala.setPerspectivaN("/cuartos/ordenador/C.png");
            }else{
                sala.setPerspectivaN("/cuartos/sin_ordenador/C.png");
            }
        }
    }

    /**
     * Asigna la perspectiva sur de una sala que contiene una llave.
     * 
     * @param sala La sala a la que se le quiere poner la perspectiva.
     */
    private void llavePerspectivaSur(Llave l, Sala sala) {
        switch (l.getColor()){
            case 'B':
                sala.setPerspectivaS("/cuartos/llave/CBK.png");
                break;
            case 'Y':
                sala.setPerspectivaS("/cuartos/llave/CYK.png");
                break;
            case 'R':
                sala.setPerspectivaS("/cuartos/llave/CRK.png");
                break;
            case 'V':
                sala.setPerspectivaS("/cuartos/llave/CGK.png");
                break;
            default:
                sala.setPerspectivaS("/cuartos/sin_ordenador/C.png");
        }
    }

    /**
     * Asigna la perspectiva este de una sala que contiene una llave.
     * 
     * @param sala La sala a la que se le quiere poner la perspectiva.
     */
    private void llavePerspectivaEste(Llave l, Sala sala) {
        switch (l.getColor()){
            case 'B':
                sala.setPerspectivaE("/cuartos/llave/CBK.png");
                break;
            case 'Y':
                sala.setPerspectivaE("/cuartos/llave/CYK.png");
                break;
            case 'R':
                sala.setPerspectivaE("/cuartos/llave/CRK.png");
                break;
            case 'V':
                sala.setPerspectivaE("/cuartos/llave/CGK.png");
                break;
            default:
                sala.setPerspectivaE("/cuartos/sin_ordenador/C.png");
        }
    }

    /**
     * Asigna la perspectiva oeste de una sala que contiene una llave.
     * 
     * @param sala La sala a la que se le quiere poner la perspectiva.
     */
    private void llavePerspectivaOeste(Llave l, Sala sala) {
        switch (l.getColor()){
            case 'B':
                sala.setPerspectivaO("/cuartos/llave/CBK.png");
                break;
            case 'Y':
                sala.setPerspectivaO("/cuartos/llave/CYK.png");
                break;
            case 'R':
                sala.setPerspectivaO("/cuartos/llave/CRK.png");
                break;
            case 'V':
                sala.setPerspectivaO("/cuartos/llave/CGK.png");
                break;
            default:
                sala.setPerspectivaO("/cuartos/sin_ordenador/C.png");
        }
    }

    /**
     * Asigna la perspectiva norte de una sala que contiene una llave.
     * 
     * @param sala La sala a la que se le quiere poner la perspectiva.
     */
    private void llavePerspectivaNorte(Llave l, Sala sala) {
        switch (l.getColor()){
            case 'B':
                sala.setPerspectivaN("/cuartos/llave/CBK.png");
                break;
            case 'Y':
                sala.setPerspectivaN("/cuartos/llave/CYK.png");
                break;
            case 'R':
                sala.setPerspectivaN("/cuartos/llave/CRK.png");
                break;
            case 'V':
                sala.setPerspectivaN("/cuartos/llave/CGK.png");
                break;
            default:
                sala.setPerspectivaO("/cuartos/sin_ordenador/C.png");
        }
    }
    
    private void asignarPerspectivaNorteOeSuEs(Sala sala, char flag) {
        StringBuilder nombre = new StringBuilder("C");

        if (sala.getPuertaE() > 1){
            nombre.append("1B");
        }else{
            nombre.append("1L");
        }

        if (sala.getPuertaS() > 1){
            nombre.append("2B");
        }else{
            nombre.append("2L");
        }

        if (sala.getPuertaO() > 1){
            nombre.append("3B");
        }else{
            nombre.append("3L");
        }

        nombre.append(".png");
        
        if (flag == 'M'){
            sala.setPerspectivaN("/cuartos/ordenador/salidas_123/" + nombre.toString());
        }else{
            sala.setPerspectivaN("/cuartos/sin_ordenador/salidas_123/" + nombre.toString());
        }
    }
    
    private void asignarPerspectivaNorteOeEs(Sala sala, char flag) {
        StringBuilder nombre = new StringBuilder("C");
        
        if (sala.getPuertaE() > 1){
            nombre.append("1B");
        }else{
            nombre.append("1L");
        }

        if (sala.getPuertaO() > 1){
            nombre.append("3B");
        }else{
            nombre.append("3L");
        }

        nombre.append(".png");
        
        if (flag == 'M'){
            sala.setPerspectivaN("/cuartos/ordenador/salidas_13/" + nombre.toString());
        }else{
            sala.setPerspectivaN("/cuartos/sin_ordenador/salidas_13/" + nombre.toString());
        }
    }
    
    private void asignarPerspectivaNorteEsSu(Sala sala, char flag) {
        StringBuilder nombre = new StringBuilder("C");
        
        if (sala.getPuertaE() > 1){
            nombre.append("1B");
        }else{
            nombre.append("1L");
        }

        if (sala.getPuertaS() > 1){
            nombre.append("2B");
        }else{
            nombre.append("2L");
        }

        nombre.append(".png");
        if (flag == 'M'){
            sala.setPerspectivaN("/cuartos/ordenador/salidas_12/" + nombre.toString());
        }else{
            sala.setPerspectivaN("/cuartos/sin_ordenador/salidas_12/" + nombre.toString());
        }
    }
    
    private void asignarPerspectivaNorteOeSu(Sala sala, char flag) {
        StringBuilder nombre = new StringBuilder("C");
        
        if (sala.getPuertaS() > 1){
            nombre.append("2B");
        }else{
            nombre.append("2L");
        }

        if (sala.getPuertaO() > 1){
            nombre.append("3B");
        }else{
            nombre.append("3L");
        }

        nombre.append(".png");
        if (flag == 'M'){
            sala.setPerspectivaN("/cuartos/ordenador/salidas_23/" + nombre.toString());
        }else{
            sala.setPerspectivaN("/cuartos/sin_ordenador/salidas_23/" + nombre.toString());
        }
    }
    
    private void asignarPerspectivaNorteEs(Sala sala, char flag) {
        StringBuilder nombre = new StringBuilder("C");

        if (sala.getPuertaE() > 1){
            nombre.append("1B");
        }else{
            nombre.append("1L");
        }

        nombre.append(".png");
        
        if (flag == 'M'){
            sala.setPerspectivaN("/cuartos/ordenador/salidas_1/" + nombre.toString());
        }else{
            sala.setPerspectivaN("/cuartos/sin_ordenador/salidas_1/" + nombre.toString());
        }
    }
    
    private void asignarPerspectivaNorteOe(Sala sala, char flag) {
        StringBuilder nombre = new StringBuilder("C");

        if (sala.getPuertaO() > 1){
            nombre.append("3B");
        }else{
            nombre.append("3L");
        }

        nombre.append(".png");
        
        if (flag == 'M'){
            sala.setPerspectivaN("/cuartos/ordenador/salidas_3/" + nombre.toString());
        }else{
            sala.setPerspectivaN("/cuartos/sin_ordenador/salidas_3/" + nombre.toString());
        }
    }
    
    private void asignarPerspectivaNorteSu(Sala sala, char flag) {
        StringBuilder nombre = new StringBuilder("C");

        if (sala.getPuertaS() > 1){
            nombre.append("2B");
        }else{
            nombre.append("2L");
        }

        nombre.append(".png");
        
        if (flag == 'M'){
            sala.setPerspectivaN("/cuartos/ordenador/salidas_2/" + nombre.toString());
        }else{
            sala.setPerspectivaN("/cuartos/sin_ordenador/salidas_2/" + nombre.toString());
        }
    }
    
    private void asignarPerspectivaSurOeNoEs(Sala sala, char flag) {
        StringBuilder nombre = new StringBuilder("C");

        if (sala.getPuertaO() > 1){
            nombre.append("1B");
        }else{
            nombre.append("1L");
        }

        if (sala.getPuertaN() > 1){
            nombre.append("2B");
        }else{
            nombre.append("2L");
        }

        if (sala.getPuertaE() > 1){
            nombre.append("3B");
        }else{
            nombre.append("3L");
        }

        nombre.append(".png");
        
        if (flag == 'M'){
            sala.setPerspectivaS("/cuartos/ordenador/salidas_123/" + nombre.toString());
        }else{
            sala.setPerspectivaS("/cuartos/sin_ordenador/salidas_123/" + nombre.toString());
        }
    }
    
    private void asignarPerspectivaSurOeEs(Sala sala, char flag) {
        StringBuilder nombre = new StringBuilder("C");
        
        if (sala.getPuertaO() > 1){
            nombre.append("1B");
        }else{
            nombre.append("1L");
        }

        if (sala.getPuertaE() > 1){
            nombre.append("3B");
        }else{
            nombre.append("3L");
        }

        nombre.append(".png");
        
        if (flag == 'M'){
            sala.setPerspectivaS("/cuartos/ordenador/salidas_13/" + nombre.toString());
        }else{
            sala.setPerspectivaS("/cuartos/sin_ordenador/salidas_13/" + nombre.toString());
        }
    }
    
    private void asignarPerspectivaSurOeNo(Sala sala, char flag) {
        StringBuilder nombre = new StringBuilder("C");
        
        if (sala.getPuertaO() > 1){
            nombre.append("1B");
        }else{
            nombre.append("1L");
        }

        if (sala.getPuertaN() > 1){
            nombre.append("2B");
        }else{
            nombre.append("2L");
        }

        nombre.append(".png");
        
        if (flag == 'M'){
            sala.setPerspectivaS("/cuartos/ordenador/salidas_12/" + nombre.toString());
        }else{
            sala.setPerspectivaS("/cuartos/sin_ordenador/salidas_12/" + nombre.toString());
        }
    }
    
    private void asignarPerspectivaSurEsNo(Sala sala, char flag) {
        StringBuilder nombre = new StringBuilder("C");
        
        if (sala.getPuertaN() > 1){
            nombre.append("2B");
        }else{
            nombre.append("2L");
        }

        if (sala.getPuertaE() > 1){
            nombre.append("3B");
        }else{
            nombre.append("3L");
        }

        nombre.append(".png");
        if (flag == 'M'){
            sala.setPerspectivaS("/cuartos/ordenador/salidas_23/" + nombre.toString());
        }else{
            sala.setPerspectivaS("/cuartos/sin_ordenador/salidas_23/" + nombre.toString());
        }
    }
    
    private void asignarPerspectivaSurEs(Sala sala, char flag) {
        StringBuilder nombre = new StringBuilder("C");

        if (sala.getPuertaE() > 1){
            nombre.append("3B");
        }else{
            nombre.append("3L");
        }

        nombre.append(".png");
        
        if (flag == 'M'){
            sala.setPerspectivaS("/cuartos/ordenador/salidas_3/" + nombre.toString());
        }else{
            sala.setPerspectivaS("/cuartos/sin_ordenador/salidas_3/" + nombre.toString());
        }
    }
    
    private void asignarPerspectivaSurOe(Sala sala, char flag) {
        StringBuilder nombre = new StringBuilder("C");

        if (sala.getPuertaO() > 1){
            nombre.append("1B");
        }else{
            nombre.append("1L");
        }

        nombre.append(".png");
        
        if (flag == 'M'){
            sala.setPerspectivaS("/cuartos/ordenador/salidas_1/" + nombre.toString());
        }else{
            sala.setPerspectivaS("/cuartos/sin_ordenador/salidas_1/" + nombre.toString());
        }
    }
    
    private void asignarPerspectivaSurNo(Sala sala, char flag) {
        StringBuilder nombre = new StringBuilder("C");

        if (sala.getPuertaN() > 1){
            nombre.append("2B");
        }else{
            nombre.append("2L");
        }

        nombre.append(".png");
        
        if (flag == 'M'){
            sala.setPerspectivaS("/cuartos/ordenador/salidas_2/" + nombre.toString());
        }else{
            sala.setPerspectivaS("/cuartos/sin_ordenador/salidas_2/" + nombre.toString());
        }
    }
    
    private void asignarPerspectivaEsteOeNoSu(Sala sala, char flag) {
        StringBuilder nombre = new StringBuilder("C");

        if (sala.getPuertaS() > 1){
            nombre.append("1B");
        }else{
            nombre.append("1L");
        }

        if (sala.getPuertaO() > 1){
            nombre.append("2B");
        }else{
            nombre.append("2L");
        }

        if (sala.getPuertaN() > 1){
            nombre.append("3B");
        }else{
            nombre.append("3L");
        }

        nombre.append(".png");
        
        if (flag == 'M'){
            sala.setPerspectivaE("/cuartos/ordenador/salidas_123/" + nombre.toString());
        }else{
            sala.setPerspectivaE("/cuartos/sin_ordenador/salidas_123/" + nombre.toString());
        }
    }
    
    private void asignarPerspectivaEsteNoSu(Sala sala, char flag) {
        StringBuilder nombre = new StringBuilder("C");
        
        if (sala.getPuertaS() > 1){
            nombre.append("1B");
        }else{
            nombre.append("1L");
        }

        if (sala.getPuertaN() > 1){
            nombre.append("3B");
        }else{
            nombre.append("3L");
        }

        nombre.append(".png");
        
        if (flag == 'M'){
            sala.setPerspectivaE("/cuartos/ordenador/salidas_13/" + nombre.toString());
        }else{
            sala.setPerspectivaE("/cuartos/sin_ordenador/salidas_13/" + nombre.toString());
        }
    }
    
    private void asignarPerspectivaEsteSuOe(Sala sala, char flag) {
        StringBuilder nombre = new StringBuilder("C");
        
        if (sala.getPuertaS() > 1){
            nombre.append("1B");
        }else{
            nombre.append("1L");
        }

        if (sala.getPuertaO() > 1){
            nombre.append("2B");
        }else{
            nombre.append("2L");
        }

        nombre.append(".png");
        
        if (flag == 'M'){
            sala.setPerspectivaE("/cuartos/ordenador/salidas_12/" + nombre.toString());
        }else{
            sala.setPerspectivaE("/cuartos/sin_ordenador/salidas_12/" + nombre.toString());
        }
    }
    
    private void asignarPerspectivaEsteOeNo(Sala sala, char flag) {
        StringBuilder nombre = new StringBuilder("C");
        
        if (sala.getPuertaO() > 1){
            nombre.append("2B");
        }else{
            nombre.append("2L");
        }

        if (sala.getPuertaN() > 1){
            nombre.append("3B");
        }else{
            nombre.append("3L");
        }

        nombre.append(".png");
        if (flag == 'M'){
            sala.setPerspectivaE("/cuartos/ordenador/salidas_23/" + nombre.toString());
        }else{
            sala.setPerspectivaE("/cuartos/sin_ordenador/salidas_23/" + nombre.toString());
        }
    }
    
    private void asignarPerspectivaEsteSu(Sala sala, char flag) {
        StringBuilder nombre = new StringBuilder("C");

        if (sala.getPuertaS() > 1){
            nombre.append("1B");
        }else{
            nombre.append("1L");
        }

        nombre.append(".png");
        
        if (flag == 'M'){
            sala.setPerspectivaE("/cuartos/ordenador/salidas_1/" + nombre.toString());
        }else{
            sala.setPerspectivaE("/cuartos/sin_ordenador/salidas_1/" + nombre.toString());
        }
    }
    
    private void asignarPerspectivaEsteOe(Sala sala, char flag) {
        StringBuilder nombre = new StringBuilder("C");

        if (sala.getPuertaO() > 1){
            nombre.append("2B");
        }else{
            nombre.append("2L");
        }

        nombre.append(".png");
        
        if (flag == 'M'){
            sala.setPerspectivaE("/cuartos/ordenador/salidas_2/" + nombre.toString());
        }else{
            sala.setPerspectivaE("/cuartos/sin_ordenador/salidas_2/" + nombre.toString());
        }
    }
    
    private void asignarPerspectivaEsteNo(Sala sala, char flag) {
        StringBuilder nombre = new StringBuilder("C");

        if (sala.getPuertaN() > 1){
            nombre.append("3B");
        }else{
            nombre.append("3L");
        }

        nombre.append(".png");
        
        if (flag == 'M'){
            sala.setPerspectivaE("/cuartos/ordenador/salidas_3/" + nombre.toString());
        }else{
            sala.setPerspectivaE("/cuartos/sin_ordenador/salidas_3/" + nombre.toString());
        }
    }
    
    private void asignarPerspectivaOesteEsNoSu(Sala sala, char flag) {
        StringBuilder nombre = new StringBuilder("C");

        if (sala.getPuertaN() > 1){
            nombre.append("1B");
        }else{
            nombre.append("1L");
        }

        if (sala.getPuertaE() > 1){
            nombre.append("2B");
        }else{
            nombre.append("2L");
        }

        if (sala.getPuertaS() > 1){
            nombre.append("3B");
        }else{
            nombre.append("3L");
        }

        nombre.append(".png");
        
        if (flag == 'M'){
            sala.setPerspectivaO("/cuartos/ordenador/salidas_123/" + nombre.toString());
        }else{
            sala.setPerspectivaO("/cuartos/sin_ordenador/salidas_123/" + nombre.toString());
        }
    }
    
    private void asignarPerspectivaOesteNoSu(Sala sala, char flag) {
        StringBuilder nombre = new StringBuilder("C");
        
        if (sala.getPuertaN() > 1){
            nombre.append("1B");
        }else{
            nombre.append("1L");
        }

        if (sala.getPuertaS() > 1){
            nombre.append("3B");
        }else{
            nombre.append("3L");
        }

        nombre.append(".png");
        
        if (flag == 'M'){
            sala.setPerspectivaO("/cuartos/ordenador/salidas_13/" + nombre.toString());
        }else{
            sala.setPerspectivaO("/cuartos/sin_ordenador/salidas_13/" + nombre.toString());
        }
    }
    
    private void asignarPerspectivaOesteEsNo(Sala sala, char flag) {
        StringBuilder nombre = new StringBuilder("C");
        
        if (sala.getPuertaN() > 1){
            nombre.append("1B");
        }else{
            nombre.append("1L");
        }

        if (sala.getPuertaE() > 1){
            nombre.append("2B");
        }else{
            nombre.append("2L");
        }

        nombre.append(".png");
        
        if (flag == 'M'){
            sala.setPerspectivaO("/cuartos/ordenador/salidas_12/" + nombre.toString());
        }else{
            sala.setPerspectivaO("/cuartos/sin_ordenador/salidas_12/" + nombre.toString());
        }
    }
    
    private void asignarPerspectivaOesteSuEs(Sala sala, char flag) {
        StringBuilder nombre = new StringBuilder("C");
        
        if (sala.getPuertaE() > 1){
            nombre.append("2B");
        }else{
            nombre.append("2L");
        }

        if (sala.getPuertaS() > 1){
            nombre.append("3B");
        }else{
            nombre.append("3L");
        }

        nombre.append(".png");
        if (flag == 'M'){
            sala.setPerspectivaO("/cuartos/ordenador/salidas_23/" + nombre.toString());
        }else{
            sala.setPerspectivaO("/cuartos/sin_ordenador/salidas_23/" + nombre.toString());
        }
    }
    
    private void asignarPerspectivaOesteSu(Sala sala, char flag) {
        StringBuilder nombre = new StringBuilder("C");

        if (sala.getPuertaS() > 1){
            nombre.append("3B");
        }else{
            nombre.append("3L");
        }

        nombre.append(".png");
        
        if (flag == 'M'){
            sala.setPerspectivaO("/cuartos/ordenador/salidas_3/" + nombre.toString());
        }else{
            sala.setPerspectivaO("/cuartos/sin_ordenador/salidas_3/" + nombre.toString());
        }
    }
    
    private void asignarPerspectivaOesteEs(Sala sala, char flag) {
        StringBuilder nombre = new StringBuilder("C");

        if (sala.getPuertaE() > 1){
            nombre.append("2B");
        }else{
            nombre.append("2L");
        }

        nombre.append(".png");
        
        if (flag == 'M'){
            sala.setPerspectivaO("/cuartos/ordenador/salidas_2/" + nombre.toString());
        }else{
            sala.setPerspectivaO("/cuartos/sin_ordenador/salidas_2/" + nombre.toString());
        }
    }
    
    private void asignarPerspectivaOesteNo(Sala sala, char flag) {
        StringBuilder nombre = new StringBuilder("C");

        if (sala.getPuertaN() > 1){
            nombre.append("1B");
        }else{
            nombre.append("1L");
        }

        nombre.append(".png");
        
        if (flag == 'M'){
            sala.setPerspectivaO("/cuartos/ordenador/salidas_1/" + nombre.toString());
        }else{
            sala.setPerspectivaO("/cuartos/sin_ordenador/salidas_1/" + nombre.toString());
        }
    }
}
