package com.example.iterator;

import java.util.HashSet;

import com.example.usuario.Admin;

public class AlmacenAdminsIterator implements com.example.iterator.Iterator {
    private HashSet<Admin> lista;
    private java.util.Iterator<Admin> iterador;

    public AlmacenAdminsIterator(HashSet<Admin> lista){
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
    public Admin next(){
        return this.iterador.next();
    };

    @Override
    public void remove(){
        this.iterador.remove();
    }
}
