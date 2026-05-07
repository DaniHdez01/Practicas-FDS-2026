package com.example.almacen;

import java.util.HashSet;

import com.example.usuario.Admin;
import com.example.iterator.Iterator;
import com.example.iterator.AlmacenAdminsIterator;

public class AlmacenAdmins implements Almacen<Admin> {
    private HashSet<Admin> lista = new HashSet<Admin>();

    @Override
    public void anyade(Admin usuario){
        this.lista.add(usuario);
    }

    @Override
    public Iterator createIterator(){
        return new AlmacenAdminsIterator(this.lista);
    }
}
