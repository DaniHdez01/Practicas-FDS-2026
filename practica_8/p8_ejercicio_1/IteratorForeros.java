package p8_ejercicio_1;

public class IteratorForeros implements Iterator<Forero> {

    private int posActual = 0;
    private int ultDevuelto = -1;
    private int tamaño;
    private Forero[] array;

    public IteratorForeros(int tamaño, Forero[] array) {
        this.tamaño = tamaño;
        this.array = array;
    }

    @Override
    public Forero next() {
        if (tamaño == 0)
            return null;
        Forero f = array[posActual];
        ultDevuelto = posActual;
        posActual = (posActual + 1) % tamaño;
        return f;
    }

    @Override
    public boolean hasNext() {
        return tamaño > 0;
    }

    @Override
    public int getSize() {
        return tamaño;
    }

    @Override
    public void remove() {
        if (ultDevuelto < 0 || tamaño == 0)
            return;
        
        for (int i = ultDevuelto; i < tamaño - 1; i++) {
            array[i] = array[i + 1];
        }
        array[tamaño - 1] = null;
        tamaño--;
        
        if (posActual > ultDevuelto)
            posActual--;
        if (tamaño > 0)
            posActual = posActual % tamaño;
        ultDevuelto = -1;
    }
}
