package com.example;


public class Test {
    public static void main(String [] args){

        //Crear dos instancias de Audit logger y comprobar si son la misma
    AuditLogger instancia1 = AuditLogger.getInstance(); 
    AuditLogger instancia2 = AuditLogger.getInstance(); 

    System.out.println("MIsma instancia entre dos AuditLogger creados = " + (instancia1 == instancia2)); 
    
    //Cargar varios logs en una de las instancias 
    Loggin loggin = new Loggin(); 
    loggin.loadLogs(instancia1); 
    
    //Cargar la lista de logs en un csv 
    instancia1.persistir("LogsInstance1");

    //Crear otra instancia que importe los logs desde un csv
    AuditLogger instancia3 = AuditLogger.getInstance(); 
    instancia3.cargar("cargaDeLogs"); 
    }

}
