package com.aventuragrafica.ventanas;

import com.aventuragrafica.objetos.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * Esta ventana se encarga de visualizar el juego.
 * 
 * @author javier
 */
public class PantallaJuego extends JFrame implements ActionListener{
    private JPanel jPaImagen, jPaObjetos, jPaMovimiento, jPaAcciones, jPaLlaves, jPaOrdenadores;
    private JLabel jImagen, jLlaveVerde, jLlaveAzul, jLlaveRoja, jLlaveAmarilla, jOrdenador, jContadorOrdenadores;
    private JButton jVolver, jPuerta1, jPuerta2, jPuerta3, jUsar, jCoger, jSalir, jAyuda;
    private int cantOrdenadores, ordenadoresResueltos;
    private Sala salaMostrar, salaFinal;
    private Character posicion;

    /**
     * Constructor de la clase, la posición inicial es el norte.
     * 
     * @param salaInicial Sala en la que empieza el juego.
     * @param salaFinal Sala en la que acaba el juego.
     * @param cantOrdenadores Cantidad de ordenadores.
     */
    public PantallaJuego(Sala salaInicial, Sala salaFinal, int cantOrdenadores){
        this.salaMostrar = salaInicial;
        this.salaFinal = salaFinal;
        this.posicion = 'N';
        this.cantOrdenadores = cantOrdenadores;
        ordenadoresResueltos = 0;
        
        inicializar();
        aniadirAccion();
        estilos();
        posicionar();
        
        jPaObjetos.setLayout(new BoxLayout(jPaObjetos, BoxLayout.Y_AXIS));
    }

    public Character getPosicion() {
        return posicion;
    }

    public Sala getSalaMostrar() {
        return salaMostrar;
    }

    public JLabel getjImagen() {
        return jImagen;
    }
    
    public JLabel getjLlaveAmarilla() {
        return jLlaveAmarilla;
    }

    public JLabel getjLlaveAzul() {
        return jLlaveAzul;
    }

    public JLabel getjLlaveVerde() {
        return jLlaveVerde;
    }

    public JLabel getjLlaveRoja() {
        return jLlaveRoja;
    }

    public void setPosicion(Character posicion) {
        this.posicion = posicion;
    }

    public void setSalaMostrar(Sala salaMostrar) {
        this.salaMostrar = salaMostrar;
    }

    public void setCantOrdenadores(int cantOrdenadores) {
        this.cantOrdenadores = cantOrdenadores;
    }
    
    /**
     * Aumenta la cantidad de ordenadores resueltos.
     */
    public void aumentarContadorOrdenadores(){
        ordenadoresResueltos++;
        jContadorOrdenadores.setText(ordenadoresResueltos + "/" + cantOrdenadores);
    }
    
    private void inicializar(){
        this.jPaImagen = new JPanel(new FlowLayout());
        this.jPaObjetos = new JPanel();
        this.jPaMovimiento = new JPanel(new BorderLayout(20, 20));
        this.jPaAcciones = new JPanel(new GridLayout(2, 2, 10, 10));
        this.jPaLlaves = new JPanel(new FlowLayout(FlowLayout.CENTER));
        this.jPaOrdenadores = new JPanel(new FlowLayout());
        this.jImagen = new JLabel(new ImageIcon(getClass().getResource(salaMostrar.getPerspectivaN())));
        this.jLlaveVerde = new JLabel(new ImageIcon(getClass().getResource("/objetos/cuadrado.png")));
        this.jLlaveAzul = new JLabel(new ImageIcon(getClass().getResource("/objetos/cuadrado.png")));
        this.jLlaveRoja = new JLabel(new ImageIcon(getClass().getResource("/objetos/cuadrado.png")));
        this.jLlaveAmarilla = new JLabel(new ImageIcon(getClass().getResource("/objetos/cuadrado.png")));
        this.jOrdenador = new JLabel(new ImageIcon(getClass().getResource("/objetos/ordenador.png")));
        this.jContadorOrdenadores = new JLabel(ordenadoresResueltos + "/" + cantOrdenadores);
        this.jVolver = new JButton(new ImageIcon(getClass().getResource("/iconos/volver.png")));
        this.jPuerta1 = new JButton(new ImageIcon(getClass().getResource("/iconos/puerta1.png")));
        this.jPuerta2 = new JButton(new ImageIcon(getClass().getResource("/iconos/puerta2.png")));
        this.jPuerta3 = new JButton(new ImageIcon(getClass().getResource("/iconos/puerta3.png")));
        this.jUsar = new JButton(new ImageIcon(getClass().getResource("/iconos/usar.png")));
        this.jCoger = new JButton(new ImageIcon(getClass().getResource("/iconos/coger.png")));
        this.jSalir = new JButton(new ImageIcon(getClass().getResource("/iconos/salir.png")));
        this.jAyuda = new JButton(new ImageIcon(getClass().getResource("/iconos/ayuda.png")));
    }
    
    private void aniadirAccion(){
        jPuerta1.addActionListener(this);
        jPuerta2.addActionListener(this);
        jPuerta3.addActionListener(this);
        jVolver.addActionListener(this);
        jUsar.addActionListener(this);
        jCoger.addActionListener(this);
        jSalir.addActionListener(this);
        jAyuda.addActionListener(this);
    }
    
    private void posicionar(){
        jPaImagen.add(jImagen);
        
        jPaMovimiento.add(jPuerta1, BorderLayout.WEST);
        jPaMovimiento.add(jPuerta2, BorderLayout.NORTH);
        jPaMovimiento.add(jPuerta3, BorderLayout.EAST);
        jPaMovimiento.add(jVolver, BorderLayout.CENTER);
        
        jPaAcciones.add(jUsar);
        jPaAcciones.add(jCoger);
        jPaAcciones.add(jAyuda);
        jPaAcciones.add(jSalir);
        
        jPaOrdenadores.add(jOrdenador);
        jPaOrdenadores.add(jContadorOrdenadores);
        
        jPaLlaves.add(jLlaveAmarilla);
        jPaLlaves.add(jLlaveAzul);
        jPaLlaves.add(jLlaveRoja);
        jPaLlaves.add(jLlaveVerde);
        
        jPaObjetos.add(jPaOrdenadores);
        jPaObjetos.add(jPaLlaves);
        
        this.add(jPaImagen, BorderLayout.NORTH);
        this.add(jPaObjetos, BorderLayout.WEST);
        this.add(jPaMovimiento, BorderLayout.CENTER);
        this.add(jPaAcciones, BorderLayout.EAST);
    }
    
    private void estilos(){
        setLayout(new BorderLayout(20, 20));
        setSize(700, 600);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("ComerLab");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        jPaAcciones.setBorder(new LineBorder(this.getBackground(), 10));
        jPaMovimiento.setBorder(new LineBorder(this.getBackground(), 10));
        
        jAyuda.setToolTipText("Ayuda");
        jCoger.setToolTipText("Coger");
        jUsar.setToolTipText("Usar");
        jSalir.setToolTipText("Salir");
        
        jPaLlaves.setToolTipText("Llaves recogidas");
        jPaOrdenadores.setToolTipText("Ordenadores resueltos");
        
        establecerToolTipSalidas();
        jVolver.setToolTipText("Volver atrás");
    }
    
    /**
     * Este método actualiza el tooltip de los botones de movimiento.
     */
    private void establecerToolTipSalidas() {
        switch(getPosicion()){
            case 'S':
                jPuerta3.setToolTipText("Salida este");
                jPuerta2.setToolTipText("Salida norte");
                jPuerta1.setToolTipText("Salida oeste");
                break;
            case 'N':
                jPuerta1.setToolTipText("Salida este");
                jPuerta2.setToolTipText("Salida sur");
                jPuerta3.setToolTipText("Salida oeste");
                break;
            case 'E':
                jPuerta1.setToolTipText("Salida sur");
                jPuerta3.setToolTipText("Salida norte");
                jPuerta2.setToolTipText("Salida oeste");
                break;
            case 'O':
                jPuerta3.setToolTipText("Salida sur");
                jPuerta1.setToolTipText("Salida norte");
                jPuerta2.setToolTipText("Salida este");
                break;
        }
    }
    
    /**
     * Este método se encarga de cambiar la imagen de la ventana.
     */
    public void cambiarImagen(){
        switch(getPosicion()){
            case 'N':
                try {
                    getjImagen().setIcon(new ImageIcon(getClass().getResource(getSalaMostrar().getPerspectivaN())));
                } catch (NullPointerException e) {
                    getjImagen().setIcon(new ImageIcon(getSalaMostrar().getPerspectivaN()));
                }
                break;
            case 'S':
                try {
                    getjImagen().setIcon(new ImageIcon(getClass().getResource(getSalaMostrar().getPerspectivaS())));
                } catch (NullPointerException e) {
                    getjImagen().setIcon(new ImageIcon(getSalaMostrar().getPerspectivaS()));
                }
                break;
            case 'O':
                try {
                    getjImagen().setIcon(new ImageIcon(getClass().getResource(getSalaMostrar().getPerspectivaO())));
                } catch (NullPointerException e) {
                    getjImagen().setIcon(new ImageIcon(getSalaMostrar().getPerspectivaO()));
                }
                break;
            case 'E':
                try {
                    getjImagen().setIcon(new ImageIcon(getClass().getResource(getSalaMostrar().getPerspectivaE())));
                } catch (NullPointerException e) {
                    getjImagen().setIcon(new ImageIcon(getSalaMostrar().getPerspectivaE()));
                }
                break;
        }
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == jPuerta1 || arg0.getSource() == jPuerta2 || arg0.getSource() == jPuerta3 || arg0.getSource() == jVolver){
            try {
                movimiento(arg0);
            } catch (InterruptedException ex) {
                setVisible(false);
            }
        }else{
            acciones(arg0);
        }
    }

    /**
     *  Este método realiza las accionos como coger, usar, ayuda...
     * 
     * @param arg0 El evento.
     */
    private void acciones(ActionEvent arg0) {
        int opcion;
        PantallaMenu paMenu;
        
        if (arg0.getSource() == jSalir){
            opcion = JOptionPane.showConfirmDialog(null, "Quieres salir", "Salir", 0);
            
            if (opcion == 0){
                setVisible(false);
                paMenu = new PantallaMenu();
                
                paMenu.setVisible(true);
            }
        }else if (arg0.getSource() == jAyuda){
            JOptionPane.showMessageDialog(null, salaMostrar.getDescripcion(), "Ayuda", JOptionPane.INFORMATION_MESSAGE);
        }else if (arg0.getSource() == jCoger){
            cogerObjeto();
        }else{
            usarObjeto();
        }
    }

    /**
     * Este método permite utilizar los ordenadores.
     */
    private void usarObjeto() {
        Ordenador or;
        
        if (salaMostrar.getObjetoSala() != null){
            if (salaMostrar.getObjetoSala().getClass() == Ordenador.class){
                or = (Ordenador) this.getSalaMostrar().getObjetoSala();
                or.usarOrdenador(this);
            }else{
                JOptionPane.showMessageDialog(null, "No puedes usar este objeto", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "No puedes usar nada", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Este método permite recoger las llaves.
     */
    private void cogerObjeto() {
        Llave ll;
        
        if (salaMostrar.getObjetoSala() != null){
            if (salaMostrar.getObjetoSala().getClass() == Llave.class){
                ll = (Llave) salaMostrar.getObjetoSala();
                ll.recogerLlave(this);
            }else{
                JOptionPane.showMessageDialog(null, "No puedes coger este objeto", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        }else{
            JOptionPane.showMessageDialog(null, "No puedes coger nada", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * Este método realiza los movimientos del jugador por las salas.
     * 
     * @param arg0 El evento.
     * @throws InterruptedException Salta un error si el usuario fuerza el hilo.
     */
    private void movimiento(ActionEvent arg0) throws InterruptedException {
        PantallaMenu pma;
        char puerta;
        
        if (arg0.getSource() == jPuerta1){
            puerta = '1';
        }else if (arg0.getSource() == jPuerta3){
            puerta = '3';
        }else if (arg0.getSource() == jPuerta2){
            puerta = '2';
        }else{
            puerta = 'V';
        }
        
        realizarMovimiento(puerta);
        cambiarImagen();
        
        if (salaMostrar.equals(salaFinal)){
            JOptionPane.showMessageDialog(null, salaMostrar.getDescripcion(), "Felicidades", JOptionPane.INFORMATION_MESSAGE);
            Thread.sleep(1500);
            setVisible(false);
            
            pma = new PantallaMenu();
            pma.setVisible(true);
        }
    }

    private void realizarMovimiento(char puerta) {
        int estado = puertaDisponible(puerta);
        
        switch (estado) {
            case 0:
                JOptionPane.showMessageDialog(null, "No hay salida.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                break;
            case 1:
                salaMostrar = salaMostrar.cambiarSala(posicion, puerta);
                cambiarPerspectiva(puerta);
                establecerToolTipSalidas();
                break;
            case 2:
                JOptionPane.showMessageDialog(null, "La salida está bloqueada.", "Advertencia", JOptionPane.WARNING_MESSAGE);
                break;
        }
    }
    
    private void cambiarPerspectiva(char puerta){
        switch(puerta){
            case '1':
            establecerPerspectivaPuerta1();
                break;
            case '3':
            establecerPerspectivaPuerta3();
                break;
            case 'V':
            establecerPerspectivaPuertaVolver();
                break;
        }
    }

    private void establecerPerspectivaPuertaVolver() {
        switch (getPosicion()) {
            case 'N':
                setPosicion('S');
                break;
            case 'S':
                setPosicion('N');
                break;
            case 'E':
                setPosicion('O');
                break;
            case 'O':
                setPosicion('E');
                break;
        }
    }

    private void establecerPerspectivaPuerta3() {
        switch (getPosicion()) {
            case 'E':
                setPosicion('S');
                break;
            case 'S':
                setPosicion('O');
                break;
            case 'O':
                setPosicion('N');
                break;
            case 'N':
                setPosicion('E');
                break;
        }
    }

    private void establecerPerspectivaPuerta1() {
        switch (getPosicion()) {
            case 'E':
                setPosicion('N');
                break;
            case 'S':
                setPosicion('E');
                break;
            case 'O':
                setPosicion('S');
                break;
            case 'N':
                setPosicion('O');
                break;
        }
    }
    
    private int puertaDisponible(char puerta){
        switch (getPosicion()){
            case 'S':
                return disponivilidadSur(puerta);
            case 'N':
                return disponivilidadNorte(puerta);
            case 'E':
                return disponivilidadEste(puerta);
            case 'O':
                return disponivilidadOeste(puerta);
        }
        return 0;
    }
    
    private int disponivilidadSur(char puerta){
        switch (puerta){
            case '1':
                return getSalaMostrar().getPuertaO();
            case '2':
                return getSalaMostrar().getPuertaN();
            case '3':
                return getSalaMostrar().getPuertaE();
            case 'V':
                return getSalaMostrar().getPuertaS();
        }
        return 0;
    }
    
    private int disponivilidadNorte(char puerta) {
        switch (puerta){
            case '1':
                return getSalaMostrar().getPuertaE();
            case '2':
                return getSalaMostrar().getPuertaS();
            case '3':
                return getSalaMostrar().getPuertaO();
            case 'V':
                return getSalaMostrar().getPuertaN();
        }
        
        return 0;
    }

    private int disponivilidadEste(char puerta) {
        switch (puerta){
            case '1':
                return getSalaMostrar().getPuertaS();
            case '2':
                return getSalaMostrar().getPuertaO();
            case '3':
                return getSalaMostrar().getPuertaN();
            case 'V':
                return getSalaMostrar().getPuertaE();
        }
        
        return 0;
    }

    private int disponivilidadOeste(char puerta) {
        switch (puerta){
            case '1':
                return getSalaMostrar().getPuertaN();
            case '2':
                return getSalaMostrar().getPuertaE();
            case '3':
                return getSalaMostrar().getPuertaS();
            case 'V':
                return getSalaMostrar().getPuertaO();     
        }
        
        return 0;
    }
}
