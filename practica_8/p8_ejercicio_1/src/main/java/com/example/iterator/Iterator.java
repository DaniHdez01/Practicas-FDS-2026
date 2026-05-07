package com.example.iterator;

import com.example.usuario.Usuario;

public interface Iterator {
    public Usuario next();
    public boolean hasNext();
    public int getSize();
    public void remove();
}
