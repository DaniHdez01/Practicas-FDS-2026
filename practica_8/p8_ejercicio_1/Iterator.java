package p8_ejercicio_1;

public interface Iterator<T> {
    public T next();
    public boolean hasNext();
    public int getSize();
    public void remove();
}
