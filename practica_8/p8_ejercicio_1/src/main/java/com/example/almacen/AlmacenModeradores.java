package com.example.almacen;

import java.util.ArrayList;

import com.example.usuario.Moderador;
import com.example.iterator.Iterator;
import com.example.iterator.AlmacenModeradoresIterator;

public class AlmacenModeradores implements Almacen<Moderador>{
    private ArrayList<Moderador> lista = new ArrayList<Moderador>();

    @Override
    public void anyade(Moderador usuario){
        this.lista.add(usuario);
    }

    @Override
    public Iterator createIterator() {
        return new AlmacenModeradoresIterator(this.lista);
    }
}
