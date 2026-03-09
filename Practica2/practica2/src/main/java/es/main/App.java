package es.main;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;

import java.awt.event.ActionEvent;

import Tools.*; 

public class App extends JFrame{
    private final Font mainFont = new Font("Segoe UI", Font.BOLD, 18);
    private final Font smallFont = new Font("Segoe UI", Font.PLAIN, 14); // Fuente más pequeña
    JTextField arrayNums = new JTextField(); 
    public void inizialize(){
    setTitle("Aplicación de comparacion de tiempos de algoritmos"); 
    JLabel label = new JLabel("Insertar los elementos del array que deseas ordenar"); 
    JLabel labelSmall = new JLabel("<html>Aplicación para comparación de tiempos entre el algoritmo de fuerza bruta y Divide y Venceras.<br>Introduce un array deseado en la entrada de texto, SIN ESPACIOS Y LOS NÚMEROS SEPARADOS POR COMAS para que ambos algoritmos lo ordenen.<br>El programa devolverá los tiempos de ejecución de cada uno.</html>");
    JButton ejecutar = new JButton("Ejecutar");
    JTextArea resultados = new JTextArea(10,40);  

    label.setFont(mainFont);
    labelSmall.setFont(smallFont);  // Aplicar la fuente más pequeña a la etiqueta
    arrayNums.setFont(mainFont); 
    ejecutar.setFont(mainFont);
    resultados.setFont(smallFont); 

    // Limitar el tamaño del JTextField
    arrayNums.setColumns(20); // Establece el ancho preferido en base a 20 caracteres

    // Limitar el tamaño del JButton
    ejecutar.setPreferredSize(new Dimension(150, 40)); // Establece un tamaño fijo para el botón

    JPanel panel = new JPanel();
    panel.setLayout(new GridLayout(5,1,5,5));
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

    JScrollPane resultsPane = new JScrollPane(resultados); 
    panel.add(resultsPane); 

    /*Action listener le da función al botón ejecutar. En el haremos la ejecucion del array
        Tanto para el algoritmo de divide y vencerás como para el algoritmo de fuerza burta
    */
   ejecutar.addActionListener(new ActionListener() {
        @Override 
        public void actionPerformed(ActionEvent e){
            resultados.setText(""); 
            String input = arrayNums.getText(); 
            if (input == null){
                resultados.append("No has puesto nada de entrada"); 
                return; 
            }
            //PROCESAMIENTO DE DATOS 
            try {
                String [] arrayNumsEnStr = input.split(","); 
                int len = arrayNumsEnStr.length; 
                int [] arrayOriginal = new int [len]; 
                for(int i = 0; i< arrayNumsEnStr.length; i++){
                    arrayOriginal[i] = Integer.parseInt(arrayNumsEnStr[i]); 
                }

                //EJECUCIÓN DE ALGORITMOS 
                int [] arrayBruta = java.util.Arrays.copyOf(arrayOriginal, len); 
                Ejecucion ejecucion = new Ejecucion(); 
                long tiempoBruta = ejecucion.ejecucionBrutaUnitaria(arrayBruta);
                
                int [] arrayDYV = java.util.Arrays.copyOf(arrayOriginal, len); 
                long tiempoDYV = ejecucion.ejecucionDivideYVencerasUnitaria(arrayDYV); 

                //SACAR RESULTADOS POR LA INTERFAZ
                resultados.append("Fuerza bruta:\n");
                resultados.append("  Tiempo de ejecución: " + tiempoBruta + " nanosegundos\n");
                resultados.append("  Array ordenado: " + java.util.Arrays.toString(arrayBruta) + "\n\n");
                
                resultados.append("Divide Y Vencerás:\n");
                resultados.append("  Tiempo de ejecución: " + tiempoDYV + " nanosegundos\n");
                resultados.append("  Array ordenado: " + java.util.Arrays.toString(arrayDYV) + "\n\n");

            }
            catch (NumberFormatException ex) {
                resultados.append("Error: Entrada inválida. Asegúrate de introducir solo números enteros separados por comas.\n");
            }
    }});

    // Añadir un panel vacío para ocupar la cuarta fila del GridLayout
    panel.add(new JPanel()); // Este panel vacío es para mantener el GridLayout de 4 filas
    add(panel);
    setSize(1000, 500);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    }
}

/*
    FALTA: 
    1. Meter la ejecución masiva en la interfaz
    2. Arreglar bug: Fuerza bruta no ordena */