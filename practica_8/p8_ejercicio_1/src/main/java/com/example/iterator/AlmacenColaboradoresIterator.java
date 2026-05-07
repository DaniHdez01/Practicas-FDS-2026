package com.example.iterator;

import java.util.NoSuchElementException;

import com.example.usuario.Colaborador;
import com.example.lista_enlazada.LinkedList_1;

public class AlmacenColaboradoresIterator implements com.example.iterator.Iterator {
    private LinkedList_1<Colaborador> lista;
    private Colaborador ultimo_usuario_devuelto = null;
    private int ultimo_indice_devuelto = -1;

    public AlmacenColaboradoresIterator(LinkedList_1<Colaborador> lista){
        this.lista = lista;
    }

    @Override
    public int getSize(){
        return this.lista.getSize();
    }

    @Override
    public boolean hasNext(){
        return this.ultimo_indice_devuelto + 1 < this.lista.getSize();
    }

    @Override
    public Colaborador next(){
        if(this.hasNext()){
            // Incrementar índice
            this.ultimo_indice_devuelto++;

            // Obtenemos el usuario a devolver
            this.ultimo_usuario_devuelto = this.lista.get(this.ultimo_indice_devuelto);

            // Devolvemos el usuario
            return this.ultimo_usuario_devuelto;
        } else {
            throw new NoSuchElementException();
        }
    };

    @Override
    public void remove() {
        try {
            boolean usuario_presente_en_lista = false;
            usuario_presente_en_lista = this.lista.contains(this.ultimo_usuario_devuelto);
            // Si el usuario está en la lista
            if (usuario_presente_en_lista) {
                // Borramos al usuario de la lista
                this.lista.remove(this.ultimo_usuario_devuelto);
            }
        } catch (Exception e) {
            new RuntimeException("Ha ocurrido un error.");
        }
    }
}
