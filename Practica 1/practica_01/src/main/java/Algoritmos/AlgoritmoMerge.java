package Algoritmos; 
public class AlgoritmoMerge {
 
    private static void sort(int arr[], int l, int r){
        if(l<r){
            int m=l+(r-l)/2; 
            sort(arr, l, m); 
            sort(arr, m+1,r); 

            merge(arr, l, m, r);
        }
    }

   private static void merge(int arr[], int l, int m, int r) {
    // Encontrar el tamaño de los dos subarreglos para ser mezclados
    int n1 = m - l + 1;
    int n2 = r - m;

    /* Crear arreglos temporales */
    int L[] = new int[n1];
    int R[] = new int[n2];

    /* Copiar datos a los arreglos temporales */
    for (int i = 0; i < n1; ++i) {
        L[i] = arr[l + i];
    }
    for (int j = 0; j < n2; ++j) {
        R[j] = arr[m + 1 + j];
    }

    /* Mezclar los arreglos temporales */

    // Índices iniciales del primer y segundo subarreglo
    int i = 0, j = 0;

    // Índice inicial del arreglo mezclado
    int k = l;
    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) {
            arr[k] = L[i];
            i++;
        } else {
            arr[k] = R[j];
            j++;
        }
        k++;
    }

    /* Copiar los elementos restantes de L[] si queda alguno */
    while (i < n1) {
        arr[k] = L[i];
        i++;
        k++;
    }

    /* Copiar los elementos restantes de R[] si queda alguno */
    while (j < n2) {
        arr[k] = R[j];
        j++;
        k++;
    }
}

    public static void mergeSort(int[] v){
        sort(v, 0, v.length-1); 
    }
}
