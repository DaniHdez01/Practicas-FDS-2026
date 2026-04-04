package com.example;

public class Player {
    private String position;
    private String name;
    private int points;
    private int value;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Player(String position, String name, int points, int value){
        this.position = position;
        this.name = name;
        this.points = points;
        this.value = value;
    }

    public void print(){
        System.out.println(this.getPosition() + " " + this.getName() + " " + this.getPoints() + " " + this.getValue());
    }

    public void printDetail(){
        System.out.println("Posición: " + this.getPosition());
        System.out.println("Nombre: " + this.getName());
        System.out.println("Puntos: " + this.getPoints());
        System.out.println("Valor: " + this.getValue());
    }

    

}

