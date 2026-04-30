package com.example;

import java.io.IOException;
import java.io.Reader;

public class CriptoReader extends ReaderDecorator {

    public CriptoReader(Reader reader){
        this.reader = reader;
    }

    public void close() throws IOException {
        this.reader.close();
    }

    public int read(char[] cbuf, int off, int len) throws IOException {
        // Mientras el número de letras a leer aún no haya sido alcanzado
        // y no se haya llegado al final del stream
        int i = 0;
        int reader_result = 0;
        while ((i < len) && (reader_result != -1)) {
            // Obtener carácter
            reader_result = this.read();

            // Si no se ha llegado al final del stream
            if(reader_result != -1){
                // Añadimos el carácter
                cbuf[off + i] = (char) reader_result;

                // Incrementamos el contador
                i++;
            }
        }

        // Devolvemos -1 si no se ha leído ningún carácter
        // Devolvemos el número de caracteres si es que alguno ha sido leído
        return i == 0 ? -1 : i;
    }

    public int read() throws IOException {
        // Obtener el carácter cifrado desde el stream
        int caracter_devuelto = this.reader.read();

        // Si el valor del carácter es -1 significa que hemos
        // llegado al final del stream
        if(caracter_devuelto == -1){
            return -1;
        }

        // Obtener el carácter cifrado desde el stream suponiendo que es un
        // carácter ASCII y descartando los 3 bytes más significativos
        char caracter = (char) caracter_devuelto;

        // Desciframos el carácter restando uno a su valor y hacemos el módulo
        // de 256 para asegurar que se devuelve una letra válida incluso
        // cuando el valor del carácter es 0.
        caracter = (char)(((int) caracter - 1) % 256);

        // Devolvemos el carácter descifrado
        return (int) caracter;
    }
    
}
