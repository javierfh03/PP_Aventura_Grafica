package com.aventuragrafica.ventanas;

import com.aventuragrafica.objetos.*;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * Esta ventana muestra una pantalla de un ordenador en la que 
 * se cargará la pregunta de este y el usuario meterá la respuesta 
 * a la pregunta.
 * 
 * @author javier
 */
public class PantallaOrdenador extends JDialog implements ActionListener, MouseListener, KeyListener{
    private Ordenador ordenadorMostrar;
    private JTextArea jPregunta;
    private JTextField jTextoEnviar;
    private JPanel jConsola;
    private JButton jSalir;
    private boolean noEstaResuelto;
    private PantallaJuego padre;

    /**
     * Constructor de la clase.
     * 
     * @param parent La ventana de juego padre.
     * @param modal Bloquear la pantalla padre.
     * @param ordenador El ordenador que se cargará.
     */
    public PantallaOrdenador(Frame parent, boolean modal, Ordenador ordenador){
        super(parent, modal);
        
        inicializar(ordenador, parent);
        posicionar();
        estilos();
        
        setLayout(null);
        setSize(510, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setTitle("Ordenador");
    }

    private void inicializar(Ordenador ordenador, Frame parent) {
        this.padre = (PantallaJuego) parent;
        this.ordenadorMostrar = ordenador;
        this.jPregunta = new JTextArea(ordenadorMostrar.getPregunta());
        this.jTextoEnviar = new JTextField("Escriba aquí.");
        this.jConsola = new JPanel();
        this.jSalir = new JButton("x");
        noEstaResuelto = false;
        
        jTextoEnviar.addKeyListener(this);
        jTextoEnviar.addMouseListener(this);
        jSalir.addActionListener(this);
    }
    
    public boolean noEstaResuelto(){
        return noEstaResuelto;
    }
    
    private void posicionar(){
        int alturaPregunta, posicionRespuesta;
        
        this.add(jConsola);
        this.add(jSalir);
        jConsola.add(jPregunta);
        jConsola.add(jTextoEnviar);
        
        if (ordenadorMostrar.getPregunta().length() > 92){
            posicionRespuesta = 63;
            alturaPregunta = 55;
        }else if (ordenadorMostrar.getPregunta().length() > 46){
            posicionRespuesta = 43;
            alturaPregunta = 35;
        }else{
            posicionRespuesta = 33;
            alturaPregunta = 15;
        }
        
        jPregunta.setSize(370, alturaPregunta);
        jPregunta.setLocation(10, 10);
        jTextoEnviar.setSize(370, 20);
        jTextoEnviar.setLocation(10, posicionRespuesta);
        
        jConsola.setSize(400, 280);
        jConsola.setLocation(50, 30);
        jSalir.setSize(35, 35);
        jSalir.setLocation(455, 320);
    }

    private void estilos() {
        jConsola.setLayout(null);
        jConsola.setBackground(Color.BLACK);
        
        jPregunta.setEditable(false);
        jPregunta.setBackground(Color.BLACK);
        jPregunta.setForeground(Color.GREEN);
        
        jTextoEnviar.setBackground(Color.BLACK);
        jTextoEnviar.setForeground(Color.GREEN);
        jTextoEnviar.setBorder(new LineBorder(Color.BLACK));
        
        jSalir.setBackground(Color.red);
        jSalir.setBorder(new LineBorder(Color.BLACK));
    }
    
    @Override
    public void actionPerformed(ActionEvent arg0) {
        setVisible(false);
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
        jTextoEnviar.setText("");
    }
    
    @Override
    public void keyTyped(KeyEvent arg0) {
        String respuesta;
        
        if (arg0.getKeyChar() == KeyEvent.VK_ENTER){
            respuesta = jTextoEnviar.getText();
            if (respuesta.toLowerCase().equals(ordenadorMostrar.getRespuesta().toLowerCase())){
                noEstaResuelto = false;
                JOptionPane.showMessageDialog(null, "Respuesta correcta", "Resultado", JOptionPane.INFORMATION_MESSAGE);
                ordenadorMostrar.desbloquearOrdenador(padre);
                setVisible(false);
            }else{
                JOptionPane.showMessageDialog(null, "Respuesta incorrecta", "Resultado", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
    }
}