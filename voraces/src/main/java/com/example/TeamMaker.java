package com.example;
import java.util.Comparator;
import java.util.ArrayList;

public class TeamMaker {
    private int numberOfTeams;
    private int maxValue;
    private Players players;
    private ArrayList<Team> teams;

    public int getMaxValue() {
        return maxValue;
    }

    public int getNumberOfTeams() {
        return numberOfTeams;
    }

    public Players getPlayers() {
        return players;
    }

    public TeamMaker(int numberOfTeams, int maxValue, Players players) {  
        this.numberOfTeams = numberOfTeams;
        this.maxValue = maxValue;
        this.players = players;

        this.teams = new ArrayList<Team>();

        for (int idx = 1; idx <= this.getNumberOfTeams(); idx++) {
            Team team = new Team("Equipo número " + idx, this.getMaxValue());
            this.teams.add(team);
        }
    }

    public void print()
    {
        for (Team team : this.teams) {
            team.print();
        }
    }

    public void makeTeamsMode1(){

    for (Team team : this.teams) {

        ArrayList<Player> candidatos = new ArrayList<>(this.players.getPlayersList());

        // Ordenar por ratio puntos/valor (descendente)
        candidatos.sort((a, b) -> Double.compare(
                (double)b.getPoints() / b.getValue(),
                (double)a.getPoints() / a.getValue()
        ));

        for (Player p : candidatos) {
            if (p.getValue() <= team.getBudget()) {
                team.addPlayer(p);
                team.setBudget(team.getBudget() - p.getValue());
            }
        }
    }
}

    public void makeTeamsMode2(int minNumberOfPlayersPerTeam) {

    for (Team team : this.teams) {

        ArrayList<Player> candidatos = new ArrayList<>(this.players.getPlayersList());

        // 1. Ordenar por precio (más baratos primero)
        candidatos.sort((a, b) -> Integer.compare(a.getValue(), b.getValue()));

        // FASE 1: asegurar mínimo jugadores
        for (Player p : candidatos) {
            if (team.getPlayers().getNumberOfPlayers() < minNumberOfPlayersPerTeam
                    && p.getValue() <= team.getBudget()) {

                team.addPlayer(p);
                team.setBudget(team.getBudget() - p.getValue());
            }
        }

        // 2. Ordenar por ratio
        candidatos.sort((a, b) -> Double.compare(
                (double)b.getPoints() / b.getValue(),
                (double)a.getPoints() / a.getValue()
        ));

        // FASE 2: mejorar equipo
        for (Player p : candidatos) {
            if (!team.getPlayers().equals(p) && p.getValue() <= team.getBudget()) {
                team.addPlayer(p);
                team.setBudget(team.getBudget() - p.getValue());
            }
        }
    }
}

    public void makeTeamsMode3() {

    for (Team team : this.teams) {

        ArrayList<Player> candidatos = new ArrayList<>(this.players.getPlayersList());

        // Ordenar por precio (ascendente)
        candidatos.sort((a, b) -> Integer.compare(a.getValue(), b.getValue()));

        for (Player p : candidatos) {
            if (p.getValue() <= team.getBudget()) {
                team.addPlayer(p);
                team.setBudget(team.getBudget() - p.getValue());
            }
        }
    }
}
}

