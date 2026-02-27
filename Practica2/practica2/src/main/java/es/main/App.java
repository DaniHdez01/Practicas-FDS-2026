package es.main;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class App extends JFrame{
    private final Font mainFont = new Font("Segoe UI", Font.BOLD, 18);
    JTextField arrayNums = new JTextField(); 
    public void inizialize(){
    setTitle("Aplicación de comparacion de tiempos de algoritmos"); 
    JLabel label = new JLabel("Insertar los elementos del array que deseas ordenar"); 
    JButton ejecutar = new JButton("Ejecutar"); 
    label.setFont(mainFont); 
    arrayNums.setFont(mainFont); 
    ejecutar.setFont(mainFont);

    JPanel panel = new JPanel(); 
    panel.setLayout(new GridLayout(4,1,5,5));
    panel.add(label); 
    panel.add(arrayNums); 
    panel.add(ejecutar); 
    add(panel); 
    setSize(500, 500); 
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    setVisible(true); 
    }
}
