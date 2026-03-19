package com.example;

public class Main {

    public static void main(String[] args) {

        Players allPlayers = new Players();
        allPlayers.load("listado_jugadores_2026.csv");
        allPlayers.sortByValue();
        allPlayers.reverse();
        allPlayers.print();

        TeamMaker generator = new TeamMaker(3, 40000000, allPlayers);
        generator.makeTeamsMode1();
        generator.makeTeamsMode2(14);
        generator.makeTeamsMode3();
        generator.print();

    }
}
