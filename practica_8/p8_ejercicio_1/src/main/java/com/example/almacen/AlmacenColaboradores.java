package com.example.almacen;

import com.example.usuario.Colaborador;
import com.example.iterator.Iterator;
import com.example.iterator.AlmacenColaboradoresIterator;

import com.example.lista_enlazada.LinkedList_1;

public class AlmacenColaboradores implements Almacen<Colaborador>{
    private LinkedList_1<Colaborador> lista = new LinkedList_1<Colaborador>();

    @Override
    public void anyade(Colaborador usuario) {
        this.lista.addLast(usuario);
    }

    @Override
    public Iterator createIterator() {
        return new AlmacenColaboradoresIterator(this.lista);
    }
}
