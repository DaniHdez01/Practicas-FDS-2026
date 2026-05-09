package p8_ejercicio_1;
import java.util.ArrayList;
public class IteratorModeradores implements Iterator<Moderador> {

        private int posActual = 0;
        private int ultDevuelto = -1;
        private ArrayList<Moderador> lista;

        public IteratorModeradores(ArrayList<Moderador> lista) {
            this.lista = lista;
        }

        @Override
        public Moderador next() {
            if (lista.isEmpty())
                return null;
            Moderador m = lista.get(posActual);
            ultDevuelto = posActual;
            posActual = (posActual + 1) % lista.size();
            return m;
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
            lista.remove(ultDevuelto);
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
