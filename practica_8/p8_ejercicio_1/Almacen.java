package ej1;

public interface Almacen<T> {
    public void añade(T element);

    public Iterator<T> createIterator();
}
