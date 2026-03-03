package es.main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class App extends JFrame{
    private final Font mainFont = new Font("Segoe UI", Font.BOLD, 18);
    private final Font smallFont = new Font("Segoe UI", Font.PLAIN, 14); // Fuente más pequeña
    JTextField arrayNums = new JTextField();
    public void inizialize(){
    setTitle("Aplicación de comparacion de tiempos de algoritmos"); 
    JLabel label = new JLabel("Insertar los elementos del array que deseas ordenar"); 
    JLabel labelSmall = new JLabel("<html>Aplicación para comparación de tiempos entre el algoritmo de fuerza bruta y Divide y Venceras.<br>Introduce un array deseado en la entrada de texto para que ambos algoritmos lo ordenen.<br>El programa devolverá los tiempos de ejecución de cada uno.</html>");
    JButton ejecutar = new JButton("Ejecutar");

    label.setFont(mainFont);
    labelSmall.setFont(smallFont);  // Aplicar la fuente más pequeña a la etiqueta
    arrayNums.setFont(mainFont); 
    ejecutar.setFont(mainFont);

    // Limitar el tamaño del JTextField
    arrayNums.setColumns(20); // Establece el ancho preferido en base a 20 caracteres

    // Limitar el tamaño del JButton
    ejecutar.setPreferredSize(new Dimension(150, 40)); // Establece un tamaño fijo para el botón

    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(4,1,5,5));
    panel.add(label);
    panel.add(labelSmall);

    // Envolver arrayNums en un JPanel con FlowLayout para respetar su tamaño preferido
    JPanel arrayNumsWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
    arrayNumsWrapper.add(arrayNums);
    panel.add(arrayNumsWrapper);

    // Envolver ejecutar en un JPanel con FlowLayout para respetar su tamaño preferido
    JPanel ejecutarWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
    ejecutarWrapper.add(ejecutar);
    panel.add(ejecutarWrapper);

    // Añadir un panel vacío para ocupar la cuarta fila del GridLayout
    panel.add(new JPanel()); // Este panel vacío es para mantener el GridLayout de 4 filas
    add(panel);
    setSize(1000, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    }
}
