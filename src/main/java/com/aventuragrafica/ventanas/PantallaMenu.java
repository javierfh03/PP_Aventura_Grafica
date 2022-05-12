package com.aventuragrafica.ventanas;

import com.aventuragrafica.cargarmapa.Jugar;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.LineBorder;

/**
 * Esta ventana muestra un menú para iniciar el juego.
 * 
 * @author javier
 */
public class PantallaMenu extends JFrame implements ActionListener{
    private JLabel jTitulo;
    private JButton jJuego, jCargarMapa, jSalir;

    public PantallaMenu() throws HeadlessException {
        inicializar();
        estilos();
        
        add(jTitulo);
        add(jJuego);
        add(jCargarMapa);
        add(jSalir);
    }

    private void inicializar() {
        this.jTitulo = new JLabel("ComerLab", SwingConstants.CENTER);
        this.jJuego = new JButton("Jugar");
        this.jCargarMapa = new JButton("Cargar mapa");
        this.jSalir = new JButton("Salir");
        
        jJuego.addActionListener((ActionListener) this);
        jCargarMapa.addActionListener(this);
        jSalir.addActionListener(this);
    }
    
    private void estilos() {
        setSize(300, 300);
        setResizable(false);
        getContentPane().setBackground(new Color(0, 0, 255));
        setLocationRelativeTo(null);
        setTitle("ComerLab");
        setLayout(new GridLayout(4, 1, 5, 5));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        jTitulo.setFont(new Font("sansserif", 0, 30));
        jTitulo.setForeground(new Color(51, 255, 255));
        
        jTitulo.setBorder(new LineBorder(new Color(0, 0, 255), 15));
        jJuego.setBorder(new LineBorder(new Color(0, 0, 255), 20));
        jCargarMapa.setBorder(new LineBorder(new Color(0, 0, 255), 20));
        jSalir.setBorder(new LineBorder(new Color(0, 0, 255), 20));
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        int opcion;
        JFileChooser fc;
        Jugar jugar = new Jugar();
        
        if (arg0.getSource().equals(jJuego)){
            jugar.juego();
            setVisible(false);
        }else if (arg0.getSource().equals(jCargarMapa)){
            fc = new JFileChooser();
            fc.setDialogTitle("Seleccione un archivo");
            fc.showOpenDialog(jCargarMapa);
            
            if (fc.getSelectedFile() != null){
                if (jugar.juego(fc.getSelectedFile())){
                    setVisible(false);
                }
            }
        }else{
            opcion = JOptionPane.showConfirmDialog(null, "Quieres salir", "Salir", 0);
            
            if (opcion == 0){
                System.exit(0);
            }
        }
    }
}
