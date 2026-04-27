package com.example;

public class Loggin {

    public void loadLogs(AuditLogger auditoria){
        auditoria.log(Nivel.INFO, "Alta de usuario correcto");
        auditoria.log(Nivel.INFO, "Inicio de sesión correcto"); 
        auditoria.log(Nivel.WARNING, "Inicio de sesión con un usuario inexistente"); 
        auditoria.log(Nivel.ERROR, "Contraseña incorrecta"); 
    } 
}
