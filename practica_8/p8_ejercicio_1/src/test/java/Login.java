

public class Login {

    Almacen<Forero> foreros = new AlmacenForeros();
    Almacen<Moderador> moderadores = new AlmacenModeradores();
    Almacen<Colaborador> colabs = new AlmacenColaboradores();
    Almacen<Admin> admins = new AlmacenAdmins();

    private static final int MAX_CICLOS_CONTINUO = 5;

    public void añade() {
        añadeForero(new Forero("forero1"));
        añadeForero(new Forero("forero2"));
        añadeForero(new Forero("forero3"));
        añadeForero(new Forero("forero4"));
        añadeModerador(new Moderador("moderador1"));
        añadeModerador(new Moderador("moderador2"));
        añadeModerador(new Moderador("moderador3"));
        añadeModerador(new Moderador("moderador4"));
        añadeColaborador(new Colaborador("colaborador1"));
        añadeColaborador(new Colaborador("colaborador2"));
        añadeColaborador(new Colaborador("colaborador3"));
        añadeColaborador(new Colaborador("colaborador4"));
        añadeAdmin(new Admin("admin1"));
        añadeAdmin(new Admin("admin2"));
        añadeAdmin(new Admin("admin3"));
        añadeAdmin(new Admin("admin4")); 
    }

    public void añadeForero(Forero f) {
        foreros.añade(f);
    }

    public void añadeModerador(Moderador m) {
        moderadores.añade(m);
    }

    public void añadeColaborador(Colaborador c) {
        colabs.añade(c);
    }

    public void añadeAdmin(Admin a) {
        admins.añade(a);
    }

    
    public void imprime(int n) {

        Iterator<Forero> it1 = foreros.createIterator();
        Iterator<Moderador> it2 = moderadores.createIterator();
        Iterator<Colaborador> it3 = colabs.createIterator();
        Iterator<Admin> it4 = admins.createIterator();

        
        

        int ciclos;

        if (n > 0)
            ciclos = n;
        else
            ciclos = MAX_CICLOS_CONTINUO;

        for (int vuelta = 0; vuelta < ciclos; vuelta++) {

            System.out.println("FOREROS");
            for (int i = 0; i < it1.getSize(); i++)
                System.out.println(it1.next());

            System.out.println("MODERADORES");
            for (int i = 0; i < it2.getSize(); i++)
                System.out.println(it2.next());

            System.out.println("COLABORADORES");
            for (int i = 0; i < it3.getSize(); i++)
                System.out.println(it3.next());

            System.out.println("ADMINS");
            for (int i = 0; i < it4.getSize(); i++)
                System.out.println(it4.next());
        }
    }

    public void borra() {
        Iterator<Forero> it1 = foreros.createIterator();
        Iterator<Moderador> it2 = moderadores.createIterator();
        Iterator<Colaborador> it3 = colabs.createIterator();
        Iterator<Admin> it4 = admins.createIterator();
        it1.next();
        it1.remove();
        it2.next();
        it2.remove();
        it3.next();
        it3.remove();
        it4.next();
        it4.remove();
    }

    public static void main(String[] args) {
        Login l = new Login();
        l.añade();
        l.imprime(1);
        l.borra();
        l.imprime(1);
    }
}
