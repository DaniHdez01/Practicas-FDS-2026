package p8_ejercicio_1;
public class IteratorColaboradores implements Iterator<Colaborador> {

        private int posActual = 0;
        private int ultDevuelto = -1;
        private LinkedList_1<Colaborador> lista;

        public IteratorColaboradores(LinkedList_1<Colaborador> lista) {
            this.lista = lista;
           
        }

        @Override
        public Colaborador next() {
            if (lista.isEmpty())
                return null;
            Colaborador c = lista.get(posActual);
            ultDevuelto = posActual;
            posActual = (posActual + 1) % lista.getSize();
            return c;
        }

        @Override
        public boolean hasNext() {
            return !lista.isEmpty();
        }

        @Override
        public int getSize() {
            return lista.getSize();
        }

        @Override
        public void remove() {
            if (ultDevuelto < 0 || lista.isEmpty())
                return;
            try {
                Colaborador c = lista.get(ultDevuelto);
                lista.remove(c);
                if (!lista.isEmpty()) {
                    if (posActual > ultDevuelto)
                        posActual--;
                    posActual = posActual % lista.getSize();
                } else {
                    posActual = 0;
                }
            } catch (Exception e) {
            }
            ultDevuelto = -1;
        }
    }