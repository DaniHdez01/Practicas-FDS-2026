package com.example;

import com.example.usuario.*;
import com.example.almacen.*;
import com.example.iterator.Iterator;

public class Login {
    Almacen<Forero> foreros = new AlmacenForeros();
    Almacen<Moderador> moderadores = new AlmacenModeradores();
    Almacen<Colaborador> colabs = new AlmacenColaboradores();
    Almacen<Admin> admins = new AlmacenAdmins();

    public void anyade() {
        anyadeForero(new Forero("forero1"));
        anyadeForero(new Forero("forero2"));
        anyadeForero(new Forero("forero3"));
        anyadeForero(new Forero("forero4"));
        anyadeModerador(new Moderador("moderador1"));
        anyadeModerador(new Moderador("moderador2"));
        anyadeModerador(new Moderador("moderador3"));
        anyadeModerador(new Moderador("moderador4"));
        anyadeColaborador(new Colaborador("colaborador1"));
        anyadeColaborador(new Colaborador("colaborador2"));
        anyadeColaborador(new Colaborador("colaborador3"));
        anyadeColaborador(new Colaborador("colaborador4"));
        anyadeAdmin(new Admin("admin1"));
        anyadeAdmin(new Admin("admin2"));
        anyadeAdmin(new Admin("admin3"));
        anyadeAdmin(new Admin("admin4"));
    }

    public void anyadeForero(Forero f) {

        foreros.anyade(f);

    }

    public void anyadeModerador(Moderador m) {

        moderadores.anyade(m);

    }

    public void anyadeColaborador(Colaborador c) {

        colabs.anyade(c);

    }

    public void anyadeAdmin(Admin a) {

        admins.anyade(a);

    }

    public void imprime(int n) {

        if (n >= 1) {
            // Si el número es válido, imprimimos las estructuras el
            // número de veces especificado
            for(int i = 0; i < n; i++){
                Iterator iterador = this.foreros.createIterator();
                while(iterador.hasNext()){
                    System.out.println(iterador.next().toString());
                }
                System.out.println("");

                iterador = this.moderadores.createIterator();
                while(iterador.hasNext()){
                    System.out.println(iterador.next().toString());
                }
                System.out.println("");

                iterador = this.colabs.createIterator();
                while(iterador.hasNext()){
                    System.out.println(iterador.next().toString());
                }
                System.out.println("");

                iterador = this.admins.createIterator();
                while(iterador.hasNext()){
                    System.out.println(iterador.next().toString());
                }
                System.out.println("");
            }
        }

    }

    public void borra() {

        Iterator it1 = foreros.createIterator();
        Iterator it2 = moderadores.createIterator();
        Iterator it3 = colabs.createIterator();
        Iterator it4 = admins.createIterator();
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
        l.anyade();
        l.imprime(1);
        l.borra();
        l.imprime(1);

    }

}
