package com.example.iterator;

import java.util.ArrayList;

import com.example.usuario.Moderador;

public class AlmacenModeradoresIterator implements com.example.iterator.Iterator {
    private ArrayList<Moderador> lista;
    private java.util.Iterator<Moderador> iterador;

    public AlmacenModeradoresIterator(ArrayList<Moderador> lista){
        this.lista = lista;
        this.iterador = lista.iterator();
    }

    @Override
    public int getSize(){
        return this.lista.size();
    }

    @Override
    public boolean hasNext(){
        return this.iterador.hasNext();
    }

    @Override
    public Moderador next(){
        return this.iterador.next();
    };

    @Override
    public void remove(){
        this.iterador.remove();
    }
}
