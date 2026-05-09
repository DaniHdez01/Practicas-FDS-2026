
public class AlmacenColaboradores implements Almacen<Colaborador> {

    private LinkedList_1<Colaborador> lista = new LinkedList_1<>();

    @Override
    public void añade(Colaborador c) {
        lista.addLast(c);
    }

    @Override
    public Iterator<Colaborador> createIterator() {
        return new IteratorColaboradores(lista);
    }

    
}
