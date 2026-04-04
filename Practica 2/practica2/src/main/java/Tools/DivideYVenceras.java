package Tools;

public class DivideYVenceras {
      public static int contarInversiones(int[] vector) {
        return contar(vector, 0, vector.length - 1);
    }

    private static int contar(int[] array, int izquierda, int derecha) {

        if (izquierda >= derecha) {
            return 0;
        }

        int medio = (izquierda + derecha) / 2;

        int inversiones = 0;

        inversiones += contar(array, izquierda, medio);
        inversiones += contar(array, medio + 1, derecha);
        inversiones += merge(array, izquierda, medio, derecha);

        return inversiones;
    }

    private static int merge(int[] array, int izquierda, int medio, int derecha) {

        int[] auxiliar=new int[array.length];

        for (int i=izquierda; i<=derecha; i++) {
            auxiliar[i]=array[i];
        }

        int i=izquierda;
        int j=medio + 1;
        int k=izquierda;
        int inversiones=0;

        while (i<=medio && j<=derecha) {

            if (auxiliar[i]<=auxiliar[j]) {
                array[k]=auxiliar[i];
                k++;
                i++;
            } else {
                array[k]=auxiliar[j];
                k++;
                j++;
                inversiones=inversiones+(medio - i + 1);
            }
        }

        while (i<=medio) {
            array[k++]=auxiliar[i++];
        }

        return inversiones;
    }

}
