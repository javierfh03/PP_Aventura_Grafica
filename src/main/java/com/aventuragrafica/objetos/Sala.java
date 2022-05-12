package com.aventuragrafica.objetos;

import java.util.Objects;

/**
 * Esta clase representa una sala con sus puertas, 
 * objetos, perspectivas y descripción.
 * 
 * @author javier
 */
public class Sala {
    private String nombre;
    private Objeto objetoSala;
    private int puertaN, puertaS, puertaE, puertaO;
    private Sala salidaN, salidaS, salidaE, salidaO;
    private String perspectivaN, perspectivaS, perspectivaE, perspectivaO;
    private String descripcion;

    public Sala() {
        this("C?");
    }

    public Sala(String nombre) {
        this.nombre = nombre;
        this.puertaN = 0;
        this.puertaS = 0;
        this.puertaE = 0;
        this.puertaO = 0;
        this.descripcion = "No hay nada.";
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setObjetoSala(Objeto objetoSala) {
        this.objetoSala = objetoSala;
    }

    public void setPerspectivaE(String perspectivaE) {
        this.perspectivaE = perspectivaE;
    }

    public void setPerspectivaN(String perspectivaN) {
        this.perspectivaN = perspectivaN;
    }

    public void setPerspectivaO(String perspectivaO) {
        this.perspectivaO = perspectivaO;
    }

    public void setPerspectivaS(String perspectivaS) {
        this.perspectivaS = perspectivaS;
    }

    public void setPuertaE(int puertaE) {
        this.puertaE = puertaE;
    }

    public void setPuertaN(int puertaN) {
        this.puertaN = puertaN;
    }

    public void setPuertaO(int puertaO) {
        this.puertaO = puertaO;
    }

    public void setPuertaS(int puertaS) {
        this.puertaS = puertaS;
    }

    public void setSalidaO(Sala salidaO) {
        this.salidaO = salidaO;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSalidaE(Sala salidaE) {
        this.salidaE = salidaE;
    }

    public void setSalidaN(Sala salidaN) {
        this.salidaN = salidaN;
    }

    public void setSalidaS(Sala salidaS) {
        this.salidaS = salidaS;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Objeto getObjetoSala() {
        return objetoSala;
    }

    public String getPerspectivaE() {
        return perspectivaE;
    }

    public String getPerspectivaN() {
        return perspectivaN;
    }

    public String getPerspectivaO() {
        return perspectivaO;
    }

    public String getPerspectivaS() {
        return perspectivaS;
    }

    public Sala getSalidaO() {
        return salidaO;
    }

    public Sala getSalidaE() {
        return salidaE;
    }

    public Sala getSalidaN() {
        return salidaN;
    }

    public Sala getSalidaS() {
        return salidaS;
    }
    
    /**
     * Retorna el estado de la puerta este:
     * - 0 : No hay puerta.
     * - 1 : Hay una puerta.
     * - 2 : Hay una puerta bloqueada.
     * 
     * @return El número con el estado.
     */
    public int getPuertaE() {
        return puertaE;
    }
    
    /**
     * Retorna el estado de la puerta norte:
     * - 0 : No hay puerta.
     * - 1 : Hay una puerta.
     * - 2 : Hay una puerta bloqueada.
     * 
     * @return El número con el estado.
     */
    public int getPuertaN() {
        return puertaN;
    }
    
    /**
     * Retorna el estado de la puerta oeste:
     * - 0 : No hay puerta.
     * - 1 : Hay una puerta.
     * - 2 : Hay una puerta bloqueada.
     * 
     * @return El número con el estado.
     */
    public int getPuertaO() {
        return puertaO;
    }
    
    /**
     * Retorna el estado de la puerta sur:
     * - 0 : No hay puerta.
     * - 1 : Hay una puerta.
     * - 2 : Hay una puerta bloqueada.
     * 
     * @return El número con el estado.
     */
    public int getPuertaS() {
        return puertaS;
    }
    
    /**
     * Este método devuelve si la sala tiene objeto:
     * - O : No tiene objeto.
     * - L : Tiene una llave.
     * - M : Tiene un ordenador.
     * 
     * @return El caracter del objeto que contiene.
     */
    public char tieneObjeto(){
        if (objetoSala == null){
            return '0';
        }else if (objetoSala.getClass() == Llave.class){
            return 'L';
        }else{
            return 'M';
        }
    }   
    
    /**
     * Este método devuelve una de las salas que tenga al
     * rededor esta sala en base a la posición del usuario y
     * la puerta a la que va a acceder.
     * 
     * @param posicion La posición del usuario.
     * @param puertaAcceder La puerta a la que accedió.
     * @return La sala a la que cambiará el usuario.
     */
    public Sala cambiarSala(char posicion, char puertaAcceder){
        switch(puertaAcceder){
            case '1':
                switch (posicion) {
                    case 'E':
                        return getSalidaS();
                    case 'S':
                        return getSalidaO();
                    case 'O':
                        return getSalidaN();
                    case 'N':
                        return getSalidaE();
                }
                break;
            case '2':
                switch (posicion) {
                    case 'E':
                        return getSalidaO();
                    case 'S':
                        return getSalidaN();
                    case 'O':
                        return getSalidaE();
                    case 'N':
                        return getSalidaS();
                }
                break;

            case '3':
                switch (posicion) {
                    case 'E':
                        return getSalidaN();
                    case 'S':
                        return getSalidaE();
                    case 'O':
                        return getSalidaS();
                    case 'N':
                        return getSalidaO();
                }
                break;
            case 'V':
                switch (posicion) {
                    case 'E':
                        return getSalidaE();
                    case 'S':
                        return getSalidaS();
                    case 'O':
                        return getSalidaO();
                    case 'N':
                        return getSalidaN();
                }
                break;
        }
        
        return null;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.nombre);
        hash = 89 * hash + Objects.hashCode(this.descripcion);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        Sala other = (Sala) obj;
        
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        return true;
    }
}

