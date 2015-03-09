public class QuickSort {
    public static int quickSort(int[] A, int l, int h) {
        // Base case:
        if (l >= h) {
            return A[];
        }

        int[] newArr = new int[A.length];
        int pivotIndex = (l + h) / 2;
        int pivot = A[pivotIndex];
        int low = l, high = h;

        for (int i = l ; i <= h ; i++) {
            if (A[i] == pivot)
                continue;

            else if (A[i] > pivot) {
                newArr[high--] = A[i];
            }
            else {
                newArr[low++] = A[i];
            }
        }

        pivotIndex = low;

        while (low <= high) {
            newArr[low++] = pivot; // Now the empty spot should be the pivot
        }

        int[] result = new int[A.length];

        int i = 0;

        for (i = 0 ; i < pivotIndex ; i++) {
            result[i] = 
        }

        if (newArr[k - 1] == pivot) {
            return pivot;
        }

        else if (pivotIndex > k - 1) {
            return select(k, newArr, l, pivotIndex - 1);
        }

        else {
            return select(k, newArr, pivotIndex + 1, h);
        }
    }

}
