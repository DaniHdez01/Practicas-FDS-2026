
import java.util.ArrayList;

public class AlmacenModeradores implements Almacen<Moderador> {

    private ArrayList<Moderador> lista = new ArrayList<>();

    @Override
    public void añade(Moderador m) {
        lista.add(m);
    }

    @Override
    public Iterator<Moderador> createIterator() {
        return new IteratorModeradores(lista);
    }

    
}
