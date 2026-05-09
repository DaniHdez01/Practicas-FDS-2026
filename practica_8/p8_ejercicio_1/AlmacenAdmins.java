package p8_ejercicio_1;

import java.util.HashSet;


public class AlmacenAdmins implements Almacen<Admin> {

    private HashSet<Admin> set = new HashSet<>();

    @Override
    public void añade(Admin a) {
        set.add(a);
    }

    @Override
    public Iterator<Admin> createIterator() {
        return new IteratorAdmins(set);
    }

    
}
