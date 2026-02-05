package Algoritmos; 
public class AlgoritmoBurbuja {
    // Algoritmo de burbuja 
    
    public static void burbuja(int v[]) {
        for (int i = 0; i < v.length - 1; i++) {
            for (int j = v.length - 1; j > i; j--) {
                if (v[j - 1] > v[j]) {
                    int temp = v[j - 1];
                    v[j - 1] = v[j];
                    v[j] = temp;
                }
            }
        }
    }
}
