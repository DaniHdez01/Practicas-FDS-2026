package Tools;

public class Bruta {
     public static int inversiones(int[] array) {
        int inversiones = 0;

        for (int i = 0; i < array.length; i++) {

            for (int j = i + 1; j < array.length; j++) {

                if (array[i] > array[j]) {
                    inversiones++;
                }
            }
        }

        return inversiones;
    }

}
