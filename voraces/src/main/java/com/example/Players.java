package com.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

public class Players {
    private ArrayList<Player> players;

    public int getNumberOfDL() {
        return this.countPositions("DL");
    }

    public int getNumberOfMC() {
        return this.countPositions("MC");
    }

    public int getNumberOfDF() {
        return this.countPositions("DF");
    }

    public int getNumberOfPT() {
        return this.countPositions("PT");
    }

    public int getValue() {
        int totalValue = 0;
        for (Player player : this.players) {
            totalValue += player.getValue();
        }
        return totalValue;
    }

    public int getPoints() {
        int totalPoints = 0;
        for (Player player : this.players) {
            totalPoints += player.getPoints();
        }
        return totalPoints;
    }

    public double getValueAverage() {
        return ((double) this.getValue() / (double) this.players.size());
    }

    public double getPointsAverage() {
        return ((double) this.getPoints() / (double) this.players.size());
    }

    public Players() {
        this.players = new ArrayList<>();
    }

    public void load(String filename) {
        try (Scanner reader = new Scanner(new File(filename))) {
            while (reader.hasNextLine()) {
                String playerEntry = reader.nextLine();
                String[] fields = playerEntry.split(";");

                String position = fields[0];
                String name = fields[1];
                int points = Integer.parseInt(fields[2]);
                int value = Integer.parseInt(fields[3].trim());
                players.add(new Player(position, name, points, value));
            }
            reader.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int getNumberOfPlayers() {
        return this.players.size();
    }

    public void printPlayers() {
        for (Player player : this.players) {
            player.print();
        }
        ;
    }

    public void print() {
        String doubleFormat = "%.3f%n";
        System.out.println("------- JUGADORES ------------");
        //for (Player player : this.players) {
          //  player.print();
        //}
        System.out.println("------------------------------");
        System.out.println("Jugadores: " + this.players.size());
        System.out.println("    Porteros: " + this.getNumberOfPT());
        System.out.println("    Defensas: " + this.getNumberOfDF());
        System.out.println("    Centrocampistas: " + this.getNumberOfMC());
        System.out.println("    Delanteros: " + this.getNumberOfDL());
        System.out.println("------------------------------");
        System.out.println("Valor total: " + this.getValue());
        System.out.println("Valor medio: " + String.format(doubleFormat, this.getValueAverage()));
        System.out.println("Puntos totales: " + this.getPoints());
        System.out.println("Media de puntos: " + String.format(doubleFormat, this.getPointsAverage()));
    }

    public void sortByValue() {
        this.players.sort(new PlayerSorterByValue());
    }

    public void reverse() {
        Collections.reverse(this.players);
    }

    public void sortByName() {
        this.players.sort(new PlayerSorterByName());
    }

    public void sortByPoints() {
        this.players.sort(new PlayerSorterByPoints());
    }

    public void addPlayer(Player playerToAdd) {
        this.players.add(playerToAdd);
    }

    public void removePlayer(Player playerToRemove) {
        this.players.remove(playerToRemove);
    }

    public Player getFirstPlayer(int maxValue) {
        for (int idx = 0; idx < this.players.size(); idx++) {
            if (this.players.get(idx).getValue() <= maxValue) {
                return this.players.get(idx);
            }
        }
        return null;
    }

    public Player getFirstPlayer() {
        return this.getFirstPlayer(Integer.MAX_VALUE);
    }

    private int countPositions(String positionId) {
        int items = 0;
        for (Player player : this.players) {
            if (player.getPosition().toLowerCase().contains(positionId.toLowerCase()))
                items++;
        }
        return items;
    }

    public ArrayList<Player> getPlayersList() {
        return this.players;
    }
}
