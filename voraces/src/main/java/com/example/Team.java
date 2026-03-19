package com.example;

import java.util.ArrayList;

public class Team {
    private String name;
    private int budget;
    private Players players;

    public String getName() { return this.name; }

    public int getBudget() { return budget; }

    public void setBudget(int budget) { this.budget = budget; }

    public Players getPlayers() { return this.players; }


    public Team(String name, int initialBudget) {
        this.name = name;
        this.budget = initialBudget;
        this.players = new Players();
    }

    public void addPlayer(Player player){
        this.players.addPlayer(player);
    }

    public void removePlayer(Player player) { this.players.removePlayer(player); }

    public void print() {
        System.out.println("########################################");
        System.out.println("-- EQUIPO: " + this.getName());
        this.players.print();
        System.out.println("------------------------------");
        System.out.println("Presupuesto restante: " + this.getBudget());
        System.out.println("------------------------------");
        System.out.println("########################################");
    }


}

