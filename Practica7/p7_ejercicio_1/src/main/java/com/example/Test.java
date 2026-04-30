package com.example;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class Test 
{
    public static void main( String[] args )
    {
        String texto_original = "Texto de prueba";
        System.out.println("Texto original: " + texto_original);

        File archivo = new File("archivo.txt");

        // Crear archivo
        try {
            archivo.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException("Ha ocurrido un error.");
        }

        // Escritor del archivo
        Writer escritor = null;
        try {
            escritor = new FileWriter(archivo);

            // Decorar el escritor de archivo
            escritor = new CriptoWriter(escritor);
        } catch (IOException e) {
            throw new RuntimeException("Ha ocurrido un error.");
        }

        // Escribir texto cifrado
        try {
            escritor.write(texto_original);

            // Asegurar que cualquier escritura pendiente se realiza
            escritor.flush();
        } catch (IOException e) {
            throw new RuntimeException("Ha ocurrido un error.");
        }

        // Lectores del archivo
        Reader lector = null;
        Reader lector_descifrador = null;
        try {
            lector = new FileReader(archivo);
            lector_descifrador = new FileReader(archivo);

            // Decorar el escritor de archivo
            lector_descifrador = new CriptoReader(lector_descifrador);
        } catch (IOException e) {
            throw new RuntimeException("Ha ocurrido un error.");
        }

        // Leemos el archivo
        char[] texto_encriptado = new char[1024];
        char[] texto_recuperado = new char[1024];
        try {
            lector.read(texto_encriptado, 0, 1024);
            lector_descifrador.read(texto_recuperado, 0, 1024);
        } catch (IOException e) {
            throw new RuntimeException("Ha ocurrido un error.");
        }

        // Imprimimos el texto extraído del archivo
        String string_encriptado = new String(texto_encriptado);
        String string_recuperado = new String(texto_recuperado);
        System.out.println("Texto cifrado: " + string_encriptado);
        System.out.println("Texto recuperado: " + string_recuperado);

        // Cerrar escritor
        try {
            escritor.close();
        } catch (IOException e) {
            throw new RuntimeException("Ha ocurrido un error.");
        }

        // Cerrar lectores
        try {
            lector.close();
            lector_descifrador.close();
        } catch (IOException e) {
            throw new RuntimeException("Ha ocurrido un error.");
        }
    }
}
