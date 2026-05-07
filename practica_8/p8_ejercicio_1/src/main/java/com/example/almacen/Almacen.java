package com.example.almacen;

import com.example.usuario.Usuario;
import com.example.iterator.Iterator;

public interface Almacen<T extends Usuario> {
    public void anyade(T usuario);
    public Iterator createIterator();
}
