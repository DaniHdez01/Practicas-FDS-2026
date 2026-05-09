import java.util.ArrayList;
import java.util.HashSet;

public class IteratorAdmins implements Iterator<Admin> {

        // lista del HashSet como lista para permitir acceso por índice y borrado
        private ArrayList<Admin> lista;
        private int posActual = 0;
        private int ultDevuelto = -1;
        private HashSet<Admin> set;

        public IteratorAdmins(HashSet<Admin> set) {
            this.set = set;
            this.lista = new ArrayList<>(set);
        }

        @Override
        public Admin next() {
            if (lista.isEmpty())
                return null;
            Admin a = lista.get(posActual);
            ultDevuelto = posActual;
            posActual = (posActual + 1) % lista.size();
            return a;
        }

        @Override
        public boolean hasNext() {
            return !lista.isEmpty();
        }

        @Override
        public int getSize() {
            return lista.size();
        }

        @Override
        public void remove() {
            if (ultDevuelto < 0 || lista.isEmpty())
                return;
            Admin a = lista.remove(ultDevuelto);
            set.remove(a);
            if (!lista.isEmpty()) {
                if (posActual > ultDevuelto)
                    posActual--;
                posActual = posActual % lista.size();
            } else {
                posActual = 0;
            }
            ultDevuelto = -1;
        }
    }
