package com.aventuragrafica.cargarmapa;

import com.aventuragrafica.objetos.Llave;
import com.aventuragrafica.objetos.Ordenador;
import com.aventuragrafica.objetos.Sala;
import java.io.*;
import java.nio.charset.StandardCharsets;
import javax.swing.*;
import java.util.LinkedHashMap;

/**
 * Esta clase lee el mapa de un fichero de texto y lo carga.
 * 
 * @author javier
 */
public class Mapa {
    private LinkedHashMap<String, Sala> mapa;
    private String linea, path; 
    private InputStream rutaMapa;
    private Integer cantOrdenadores;
    private boolean esDefault;

    /**
     * Si quiere cargar el mapa por defecto, solo
     * pediremos el mapa en donde guardaremos las salas.
     * 
     * @param mapa Estructura en la que se guardarán las salas.
     */
    public Mapa(LinkedHashMap mapa) {
        this.mapa = mapa;
        rutaMapa = getClass().getResourceAsStream("/mapa/mapa.txt");
        cantOrdenadores = 0;
        esDefault = true;
    }

    /**
     * Si se quiere cargar un mapa personalizado, pediremos
     * el mapa en el que se guadarán las salas y el archivo que
     * se cargará.
     * 
     * @param mapa Estructura en la que se guardarán las salas.
     * @param rutaMapa Ruta del mapa que se cargará.
     * @throws java.io.FileNotFoundException Si el archivo no se encuentra, se devolverá
     * un error.
     */
    public Mapa(LinkedHashMap mapa, File rutaMapa) throws FileNotFoundException {
        this.mapa = mapa;
        this.rutaMapa = new FileInputStream(rutaMapa);
        cantOrdenadores = 0;
        esDefault = false;
        path = sacarRutaArchivo(rutaMapa.getPath());
    }

    public int getCantOrdenadores() {
        return cantOrdenadores;
    }
    
    /**
     * Esta método carga en objetos del archivo en el que
     * se encuentra el mapa.
     * 
     * @throws java.lang.Exception Si la sintaxis es incorrecta, se
     * devolverá un error.
     */
    public void cargarMapa() throws Exception{
        int con = 0, lineas = 1;
        Imagenes ei = new Imagenes(mapa);
        Descripcion ed = new Descripcion(mapa);
        
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(rutaMapa, StandardCharsets.UTF_8));
            
            while ((linea = bf.readLine()) != null) {
                if (linea.equals("@")){
                    if (con == 3){
                        ed.cargarDescripciones();
                        ei.cargarImagenes();
                    }
                    con++;
                }else if (linea.charAt(0) != '/' && linea.charAt(1) != '/'){
                    switch (con){
                        case 0:
                            cargarSalas();
                            break;
                        case 1:
                            definirUniones();
                            break;
                        case 2:
                            definirObjetos();
                            break;
                        case 3:
                            asignarPreguntas();
                            break;
                        case 4:
                            establecerImagenesPersonalizadas();
                            break;
                    }
                }
                lineas++;
            } 
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No existe el archivo", "Error", JOptionPane.ERROR_MESSAGE);
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error en la línea " + lineas + " del mapa", "Error", JOptionPane.ERROR_MESSAGE);
            throw new Exception();
        }
    }
    
    /**
     * Este método carga todas las salas y sus puertas
     * justo su estado.
     */
    private void cargarSalas(){
        StringBuilder nombreSala = new StringBuilder("");
        Sala sala = null;
        
        for (int i = 0, a = 0; i < linea.length(); i++) {
            if (linea.charAt(i) == ';'){
                mapa.put(sala.getNombre(), sala);
                break;
            }
            
            if (linea.charAt(i) == '#'){
                if (a == 0){
                    sala = new Sala();
                    sala.setNombre(nombreSala.toString());
                }
                a++;
            }else{
                switch (a){
                    case 0:
                        nombreSala.append(linea.charAt(i));
                        break;
                    case 1:
                        asignarPuerta(linea.charAt(i), linea.charAt(i+1), sala);
                        i++;
                        break;
                }
            }  
        }
    }
    
    /**
     * Este método anota en una sala las salas que tiene
     * alrededor.
     */
    private void definirUniones(){
        StringBuilder nombreSala = new StringBuilder("");
        Sala sala = null;
        char pos = 'a';
        
        for (int i = 0, a = 0; i < linea.length(); i++) {
            switch (linea.charAt(i)) {
                case '-':
                case ';':
                    establecerSala(pos, nombreSala.toString(), sala);
                    nombreSala = new StringBuilder("");
                    a++;
                    break;
                case '#':
                    sala = mapa.get(nombreSala.toString());
                    nombreSala = new StringBuilder("");
                    a++;
                    break;
                default:
                    switch (a){
                        case 0:
                            nombreSala.append(linea.charAt(i));
                            break;
                        case 1:
                            pos = linea.charAt(i);
                            a--;
                            break;
                    }   break;
            }
        }
    }
    
    /**
     * Este método carga los objetos que hay en las salas.
     */
    private void definirObjetos(){
        StringBuilder texto = new StringBuilder("");
        Sala sala = null;
        char tipo = 'a';
        
        for (int i = 0, a = 0; i < linea.length(); i++) {
            switch (linea.charAt(i)) {
                case '#':
                case ';':
                    switch (a){
                        case 0:
                            sala = mapa.get(texto.toString());
                            texto.setLength(0);
                            break;
                        case 1:
                            if (tipo == 'M'){
                                cantOrdenadores++;
                                sala.setObjetoSala(new Ordenador());
                            }else{
                                sala.setObjetoSala(new Llave(tipo));
                            }
                            break;
                        case 2:
                            sala.getObjetoSala().setReferencia(mapa.get(texto.toString()));
                            texto.setLength(0);
                            break;
                        case 3:
                            sala.getObjetoSala().setPosPuerta(tipo);
                            break;
                    }
                    a++;
                    break;
                default:
                    switch (a){
                        case 0:
                        case 2:
                        case 4:
                            texto.append(linea.charAt(i));
                            break;
                        case 1:
                        case 3:
                            tipo = linea.charAt(i);
                            break;
                        
                    }   
                    break;
            }
        }
    }
    
    /**
     * Este método carga las preguntas en los ordenadores.
     * 
     * @throws Exception Si la pregunta es más larga de 140
     * caracteres, devolveremos una excepción.
     */
    private void asignarPreguntas() throws Exception{
        int contPalabras = 0;
        Ordenador or = null;
        StringBuilder texto = new StringBuilder("");
        
        for (int i = 0, a = 0; i < linea.length(); i++) {
            switch (linea.charAt(i)) {
                case '#':
                case ';':
                    switch (a){
                        case 0:
                            or = (Ordenador) mapa.get(texto.toString()).getObjetoSala();
                            texto.setLength(0);
                            break;
                        case 1:
                            if (texto.length() < 171){
                                or.setPregunta(texto.toString());
                                texto.setLength(0);
                            }else{
                                System.out.println(texto.length());
                                throw new Exception();
                            }
                            break;
                        case 2:
                            or.setRespuesta(texto.toString());
                            texto.setLength(0);
                            break;
                    }
                    a++;
                    break;
                default:
                    texto.append(linea.charAt(i));
                    
                    if (a == 1){
                        contPalabras++;
                        
                        if (!(contPalabras < 57)){
                            texto.append("\n");
                            contPalabras = 0;
                        }
                    }
                    break;
            }
        }
    }
    
    /**
     * Este método establece las imágenes personalizadas en 
     * las salas.
     */
    private void establecerImagenesPersonalizadas(){
        StringBuilder texto = new StringBuilder("");
        Sala sala = null;
        char pos;
        
        for (int i = 0, a = 0; i < linea.length(); i++) {
            switch (linea.charAt(i)) {
                case '#':
                    switch (a){
                        case 0:
                            sala = mapa.get(texto.toString());
                            texto.setLength(0);
                            
                            if (!esDefault){
                                texto.append(path);
                            }
                            break;
                        case 1:
                            pos = linea.charAt(i+1);
                            
                            switch(pos){
                                case 'N':
                                    sala.setPerspectivaN(texto.toString());
                                    return;
                                case 'S':
                                    sala.setPerspectivaS(texto.toString());
                                    return;
                                case 'E':
                                    sala.setPerspectivaE(texto.toString());
                                    return;
                                case 'O':
                                    sala.setPerspectivaO(texto.toString());
                                    return;
                            }
                            break;
                    }
                    a++;
                    break;
                default:
                    texto.append(linea.charAt(i));
                    break;
            }
        }  
        
    }
    
    /**
     * Este método asigna el estado a una puerta.
     * 
     * @param pos Ubicación de la puerta (Norte, sur...)
     * @param estado El estado (Libre o bloqueado)
     * @param sala Sala a la que se le asigna la puerta.
     */
    private void asignarPuerta(char pos, char estado, Sala sala){
        switch(pos){
            case 'N':
                if (estado == 'L'){
                    sala.setPuertaN(1);
                }else{
                    sala.setPuertaN(2);
                }
                break;
              
            case 'S':
                if (estado == 'L'){
                    sala.setPuertaS(1);
                }else{
                    sala.setPuertaS(2);
                }
                break;
                
            case 'E':
                if (estado == 'L'){
                    sala.setPuertaE(1);
                }else{
                    sala.setPuertaE(2);
                }
                break;
                
            case 'O':
                if (estado == 'L'){
                    sala.setPuertaO(1);
                }else{
                    sala.setPuertaO(2);
                }
                break;
        }
    }
    
    /**
     * Este método asigna a una sala la sala que tiene al lado
     * dependiendo de su ubicación.
     * 
     * @param pos Posición de la puerta que da con la sala de al lado.
     * @param nombre Nombre de la sala que tenemos al lado.
     * @param sala  Sala a la que le vamos a establecer una sala la lado.
     */
    private void establecerSala(char pos, String nombre, Sala sala){
        switch (pos){
            case 'N':
                sala.setSalidaN(mapa.get(nombre));
                break;
            case 'S':
                sala.setSalidaS(mapa.get(nombre));
                break;
            case 'E':
                sala.setSalidaE(mapa.get(nombre)); 
                break;
            case 'O':
                sala.setSalidaO(mapa.get(nombre));    
                break;
        } 
    }
    
    /**
     * Este método saca la ruta en la que se encuentra el
     * mapa que se quiere cargar.
     * 
     * @param rutaAbsoluta La ruta absoluta del mapa.
     * @return La ruta en la que se encuentra el archivo.
     */
    private String sacarRutaArchivo(String rutaAbsoluta){
        int contador = rutaAbsoluta.length() - 1;
        
        while (contador > -1) {
            if (rutaAbsoluta.charAt(contador) == '/' || rutaAbsoluta.charAt(contador) == '\\'){
                break;
            }
            contador --;
        }
        
        return rutaAbsoluta.substring(0, contador + 1);
    }
}
