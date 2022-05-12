package com.aventuragrafica.objetos;

/**
 * Esta clase almacena el objeto que hay en las salas.
 * Se encargan de desbloquear puertas.
 * 
 * @author javier
 */
public class Objeto {
    Sala referencia;
    char posPuerta;

    /**
     * Constructor del objeto.
     * 
     * @param referencia La sala a la que le desbloqueará una puerta.
     * @param posPuerta La posición de la puerta que desloquea (N, S, E, O).
     */
    public Objeto(Sala referencia, char posPuerta) {
        this.referencia = referencia;
        this.posPuerta = posPuerta;
    }

    public void setPosPuerta(char posPuerta) {
        this.posPuerta = posPuerta;
    }

    public void setReferencia(Sala referencia) {
        this.referencia = referencia;
    }

    public char getPosPuerta() {
        return posPuerta;
    }

    public Sala getReferencia() {
        return referencia;
    }
    
    /**
     * Desbloquea la puerta a la que el objeto hace referencia.
     */
    public void desbloquearPuerta(){
        switch (posPuerta){
            case 'N':
                referencia.setPuertaN(1);
                break;
            case 'S':
                referencia.setPuertaS(1);
                break;
            case 'O':
                referencia.setPuertaO(1);
                break;
            case 'E':
                referencia.setPuertaE(1);
                break;
        }
    }
}
