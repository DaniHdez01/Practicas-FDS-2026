package com.example.almacen;

import java.util.concurrent.atomic.AtomicInteger;

import com.example.usuario.Forero;
import com.example.iterator.Iterator;
import com.example.iterator.AlmacenForerosIterator;


public class AlmacenForeros implements Almacen<Forero>{
    private Forero[] lista = new Forero[1024];
    private AtomicInteger numero_usuarios = new AtomicInteger();

    @Override
    public void anyade(Forero usuario) {
        this.lista[numero_usuarios.get()] = usuario;
        this.numero_usuarios.incrementAndGet();
    }

    @Override
    public Iterator createIterator() {
        return new AlmacenForerosIterator(this.lista, this.numero_usuarios);
    }
}
