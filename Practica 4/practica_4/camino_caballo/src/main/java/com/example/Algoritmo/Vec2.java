package com.example.Algoritmo;

/**
 * Vector de dos dimensiones
 */
public class Vec2 {
    public int x = 0;
    public int y = 0;

    public Vec2(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Suma el vector B al vector
     * 
     * @param b Vector a sumar
     */
    public void add(Vec2 b) {
        this.x += b.x;
        this.y += b.y;
    }

    /**
     * Suma dos vectores y devuelve un tercer vector resultante de la suma
     * 
     * @param a Vector a sumar
     * @param b Vector a sumar
     * @return Vector resultante de la suma
     */
    public static Vec2 add(Vec2 a, Vec2 b) {
        return new Vec2(a.x + b.x, a.y + b.y);
    }

    @Override
    public boolean equals(Object obj) {
        Vec2 vector_a_comparar = (Vec2) obj;
        return this.x == vector_a_comparar.x && this.y == vector_a_comparar.y;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("(");
        sb.append(this.x);
        sb.append(", ");
        sb.append(this.y);
        sb.append(")");

        return sb.toString();
    }
}
