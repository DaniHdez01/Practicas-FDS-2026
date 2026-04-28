package com.example;
import java.time.*;
public class Log {
    private LocalDateTime marca; 
    private String message;
    private Nivel level; 

    public Log(LocalDateTime marca, String message, Nivel level) {
        this.marca = marca;
        this.message = message;
        this.level = level; 
    }

    public LocalDateTime getTimestamp() {
        return marca;
    }

    public String getMessage() {
        return message;
    }

    public Nivel getNivel() {
        return level;
    } 

    
    
}
