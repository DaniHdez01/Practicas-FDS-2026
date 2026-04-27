package com.example;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;
import java.time.format.DateTimeParseException;

public class AuditLogger {
    private static AuditLogger instance = null; 
    private List <Log> listaLogs; 

    private AuditLogger(){
        this.listaLogs = new ArrayList<>();
    }

    public static AuditLogger getInstance(){
        if (instance == null){
            instance = new AuditLogger(); 
        }
        return instance; 
    }

    public void log(Nivel nivel, String message){
        LocalDateTime now = LocalDateTime.now(); 
        Log nuevo = new Log(now, message, nivel); 
        listaLogs.add(nuevo); 
    }

    public void persistir(String nombreArchivo) {
        String [] titulos = {"marca ", "mensaje", "nivel"}; 
                nombreArchivo = nombreArchivo + ".csv"; 
        
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(nombreArchivo));


            for(int i = 0; i< titulos.length; i++){
                bw.write(titulos[i] + ";");
            }
            bw.newLine();
            for(Log log : listaLogs){
                bw.write(log.getTimestamp().toString() + ";" + log.getMessage() + ";" + log.getNivel().toString()) ;
                bw.newLine(); 
            }
            System.out.println("Archivo .csv cargado correctamente"); 
            bw.close(); 
        } catch (IOException e){
            System.err.println("Error al cargar el archivo");
        }
    }

    public void cargar(String nombreArchivo) {
        String line;
        String csvFile = nombreArchivo + ".csv";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Skip the header line
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(";");
                if (data.length == 3) {
                    try {
                        LocalDateTime timestamp = LocalDateTime.parse(data[0]);
                        String message = data[1];
                        Nivel level = parseNivel(data[2]);

                        Log newLog = new Log(timestamp, message, level);
                        listaLogs.add(newLog);
                    } catch (DateTimeParseException e) {
                        System.err.println("Error parsing date-time in line: " + line + " - " + e.getMessage());
                    } catch (IllegalArgumentException e) {
                        System.err.println("Error parsing Nivel in line: " + line + " - " + e.getMessage());
                    }
                } else {
                    System.err.println("Skipping malformed line: " + line);
                }
            }
            System.out.println("Archivo .csv importado correctamente");
        } catch (IOException e) {
            System.err.println("Error al importar el archivo: " + e.getMessage());
        }
    }

    private Nivel parseNivel(String nivelString) {
        return Nivel.valueOf(nivelString.trim().toUpperCase());
    }
}
