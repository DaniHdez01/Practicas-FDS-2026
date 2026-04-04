package Tools;

import java.util.Random;

public class Generador {
    
    public static int[] generarVector(int n) {

        int[] vector = new int[n];

        for (int i = 0; i < n; i++) {
            vector[i] = i + 1;
        }

        Random random = new Random();

        for (int i = n - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            int temp = vector[i];
            vector[i] = vector[j];
            vector[j] = temp;
        }
        return vector;
    } 

}
