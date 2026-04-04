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

import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;

import Tools.*; 

public class App extends JFrame{
    private final Font mainFont = new Font("Segoe UI", Font.BOLD, 18);
    private final Font smallFont = new Font("Segoe UI", Font.PLAIN, 14); // Fuente más pequeña
    JTextField arrayNums = new JTextField(); 

    public void inizialize(){

        //INICIALIZACIÓN DE OBJETOS DE LA INTERFAZ
    setTitle("Aplicación de comparacion de tiempos de algoritmos"); 
    JLabel label = new JLabel("Insertar los elementos del array que deseas ordenar"); 
    JLabel labelSmall = new JLabel("<html>Aplicación para comparación de tiempos entre el algoritmo de fuerza bruta y Divide y Venceras.<br>Introduce un array deseado en la entrada de texto, SIN ESPACIOS Y LOS NÚMEROS SEPARADOS POR COMAS para que ambos algoritmos lo ordenen.<br>El programa devolverá los tiempos de ejecución de cada uno. <br> En caso de hacer una ejecución masiva para generar el csv de tiempos en el texto de entrada pon los tamaños deseados de cada array que quieres que se ejecute. <br> ADVERTENCIA: La ejecución masiva admite un máximo de 5 tamaños distintos</html>");
    JButton ejecutarBruta = new JButton("Fuerza Bruta");
    JTextArea resultados = new JTextArea(10,40);  
    JButton ejecutarDYV = new JButton("Divide y vencerás"); 
    JButton ejecucionMasiva = new JButton("Ejecución masiva de ambos algoritmos"); 

    //Establecer la fuente para cada objeto
    label.setFont(mainFont);
    labelSmall.setFont(smallFont);  
    arrayNums.setFont(mainFont); 
    ejecutarBruta.setFont(mainFont);
    resultados.setFont(smallFont); 
    ejecutarDYV.setFont(mainFont); 
    ejecucionMasiva.setFont(mainFont); 

    //AJUSTE DE TAMAÑOS
    arrayNums.setColumns(20); // Establece el ancho preferido en base a 20 caracteres
    ejecutarBruta.setPreferredSize(new Dimension(150, 40)); // Establece un tamaño fijo para el botón
    ejecutarDYV.setPreferredSize(new Dimension(150, 40));
    ejecucionMasiva.setPreferredSize(new Dimension(150, 40));


    JPanel panel = new JPanel();
    panel.setBorder(new EmptyBorder(10, 20, 10, 20)); // Añade un margen de 10 píxeles en todos los lados
    panel.setLayout(new GridLayout(5,1,5,5));
    panel.add(label);
    panel.add(labelSmall);

    // Envolver el array de entrada y los botones 
    JPanel arrayNumsWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
    arrayNumsWrapper.add(arrayNums);
    panel.add(arrayNumsWrapper);
    JPanel ejecutarWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));

    //Agregar al panel los botones y el array de entrada
    ejecutarWrapper.add(ejecutarBruta);
    ejecutarWrapper.add(ejecutarDYV); 
    ejecutarWrapper.add(ejecucionMasiva); 
    panel.add(ejecutarWrapper);

    //Panel de salida 
    JScrollPane resultsPane = new JScrollPane(resultados); 
    panel.add(resultsPane); 

    /*Action listener le da función al botón ejecutarBruta. Aquí se ejecuta
    el algoritmo de fuerza bruta
    */
   ejecutarBruta.addActionListener(new ActionListener() {
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
        

                //SACAR RESULTADOS POR LA INTERFAZ
                resultados.append("Fuerza bruta:\n");
                resultados.append("  Tiempo de ejecución: " + tiempoBruta + " nanosegundos\n");

            }
            catch (NumberFormatException ex) {
                resultados.append("Error: Entrada inválida. Asegúrate de introducir solo números enteros separados por comas.\n");
            }
    }});

        /*Action listener le da función al botón ejecutarDYV. Aquí se ejecuta
    el algoritmo de divide y vencerás
    */
    ejecutarDYV.addActionListener(new ActionListener(){
        @Override 
        public void actionPerformed(ActionEvent e){
            resultados.setText(""); 
            String input = arrayNums.getText(); 
            if (input == null){
                resultados.append("No has puesto nada de entrada"); 
                return; 
            }
            //PROCESAMIENTO DE DATOS PARA DIVIDE Y VENCERÁS
            try {
                String [] arrayNumsEnStr = input.split(","); 
                int len = arrayNumsEnStr.length; 
                int [] arrayOriginal = new int [len]; 
                for(int i = 0; i< arrayNumsEnStr.length; i++){
                    arrayOriginal[i] = Integer.parseInt(arrayNumsEnStr[i]); 
                }

                int [] arrayDYV = java.util.Arrays.copyOf(arrayOriginal, len);
                Ejecucion ejecucionDYV = new Ejecucion();  
                long tiempoDYV = ejecucionDYV.ejecucionDivideYVencerasUnitaria(arrayDYV); 

                //SACAR RESULTADOS POR LA INTERFAZ
                resultados.append("Divide y vencerás:\n");
                resultados.append("  Tiempo de ejecución: " + tiempoDYV + " nanosegundos\n");
                resultados.append("  Array ordenado: " + java.util.Arrays.toString(arrayDYV) + "\n\n");

            }catch (NumberFormatException ex) {
                resultados.append("Error: Entrada inválida. Asegúrate de introducir solo números enteros separados por comas.\n");
            }

}});
    ejecucionMasiva.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e){
        resultados.setText(""); 
            String input = arrayNums.getText(); 
            if (input == null){
                resultados.append("No has puesto nada de entrada"); 
                return; 
            }
            //PROCESAMIENTO DE DATOS PARA DIVIDE Y VENCERÁS
            try {
                String [] arrayNumsEnStr = input.split(","); 
                int len = arrayNumsEnStr.length; 
                int [] arrayOriginal = new int [len]; 
                for(int i = 0; i< arrayNumsEnStr.length; i++){
                    arrayOriginal[i] = Integer.parseInt(arrayNumsEnStr[i]); 
                }

                int [] arraySizes = java.util.Arrays.copyOf(arrayOriginal, len);
                Ejecucion ejecucionMasiva = new Ejecucion();  
                ejecucionMasiva.ejecucionMasiva(arraySizes); 
                resultados.append("Generado con exito archivo csv con tiempos de ejecución de los siguientes tamaños: ");
                resultados.append("Fuerza bruta: "+ java.util.Arrays.toString(ejecucionMasiva.getTiemposBruta())); 
                resultados.append("Divide y venceras: "+java.util.Arrays.toString(ejecucionMasiva.getTiemposDYV())); 

            }catch (NumberFormatException ex) {
                resultados.append("Error: Entrada inválida. Asegúrate de introducir solo números enteros separados por comas.\n");
            }
    }
    });

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
    2. Arreglar bug: Fuerza bruta no ordena*/