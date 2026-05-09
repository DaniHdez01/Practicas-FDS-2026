package ej1;

public class AlmacenForeros implements Almacen<Forero> {

    private static final int MAX = 100;
    private Forero[] array = new Forero[MAX];
    private int tamaño = 0;

    @Override
    public void añade(Forero f) {
        if (tamaño < MAX) {
            array[tamaño++] = f;
        }
    }

    @Override
    public Iterator<Forero> createIterator() {
        return new IteratorForeros(tamaño, array);
    }

    
    
}
