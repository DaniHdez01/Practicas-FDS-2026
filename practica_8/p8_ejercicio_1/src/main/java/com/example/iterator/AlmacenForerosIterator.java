package com.example.iterator;

import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.usuario.Forero;

public class AlmacenForerosIterator implements Iterator {
    private Forero[] lista;
    private AtomicInteger numero_usuarios;
    private Forero ultimo_usuario_devuelto = null;
    private int ultimo_indice_devuelto = -1;

    public AlmacenForerosIterator(Forero[] lista, AtomicInteger numero_usuarios){
        this.lista = lista;
        this.numero_usuarios = numero_usuarios;
    }

    @Override
    public int getSize(){
        return this.numero_usuarios.get();
    }

    @Override
    public boolean hasNext(){
        return this.ultimo_indice_devuelto + 1 < this.numero_usuarios.get();
    }

    @Override
    public Forero next(){
        if(this.hasNext()){
            // Incrementar índice
            this.ultimo_indice_devuelto++;

            // Obtenemos el usuario a devolver
            this.ultimo_usuario_devuelto = this.lista[this.ultimo_indice_devuelto];

            // Devolvemos el usuario
            return this.ultimo_usuario_devuelto;
        } else {
            throw new NoSuchElementException();
        }
    };

    @Override
    public void remove(){
        if (this.ultimo_usuario_devuelto != null){
            // Si el usuario está en la lista
            if(this.lista[this.ultimo_indice_devuelto] == this.ultimo_usuario_devuelto){
                // Borramos al usuario de la lista
                this.lista[this.ultimo_indice_devuelto] = null;

                // Si el usuario no era el último de la lista, desplazamos los elementos
                // que quedan a su derecha
                if (this.ultimo_indice_devuelto + 1 != this.numero_usuarios.get()){
                    // Por cada elemento que queda después del borrado
                    for(int i = this.ultimo_indice_devuelto + 1; i < this.numero_usuarios.get(); i++){
                        // Desplazamos la referencia un elemento hacia atrás
                        this.lista[i - 1] = this.lista[i];
                    }

                    // Limpiamos la referencia duplicada del último elemento
                    this.lista[this.numero_usuarios.get() - 1] = null;
                }

                // Reducimos el número de usuarios
                this.numero_usuarios.decrementAndGet();

                // Reiniciamos la referencia al último usuario devuelto
                this.ultimo_usuario_devuelto = null;

                // Reducimos el último índice devuelto para poder devolver el nuevo elemento
                // que se sitúa en lo que antes era la posición del anterior elemento
                this.ultimo_indice_devuelto--;
            }
        } else {
            throw new IllegalStateException();
        }
    }
}
