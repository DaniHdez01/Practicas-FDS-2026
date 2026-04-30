package com.example;

import java.io.IOException;
import java.io.Writer;

public class CriptoWriter extends WriterDecorator {

    public CriptoWriter(Writer writer){
        this.writer = writer;
    }

    public void close() throws IOException {
        this.writer.close();
    }

    public void flush() throws IOException {
        this.writer.flush();
    }

    public void write(char[] cbuf, int off, int len) throws IOException {
        for(int i = 0; i < len; i++){
            this.writer.write(cbuf[off + i]);
        }
    }

    public void write(int c) throws IOException {
        // Suponiendo que es un carácter ASCII, lo convertimos en un char
        // descartando los 3 bytes más significativos
        char caracter = (char) c;

        // Ciframos el carácter sumando uno a su valor y hacemos el módulo
        // de 256 para asegurar que se devuelve una letra válida incluso
        // cuando el valor del carácter es 255.
        caracter = (char)(((int) caracter + 1) % 256);

        // Escribimos el carácter cifrado
        this.writer.write((int) caracter);
    }

    public void write(String str) throws IOException {
        // Por cada carácter en la cadena de caracteres
        for(char caracter: str.toCharArray()){
            // Escribimos el carácter
            this.write(caracter);
        }
    }
}
