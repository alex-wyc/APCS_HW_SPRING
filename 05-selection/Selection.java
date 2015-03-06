import java.util.ArrayList;
import java.util.Arrays;

public class Selection {
    /* Failed attempt... Rearrangement is a lot easier in arrays.
    public static select(int k, ArrayList<Integer> data) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        int pivot = data.get(0);
        result.add(pivot);
        for (int i = 1 ; i < data.size() ; i++) {
            if (data.get(i) < pivot) {
                result.add(data.get(i), 0);
            }
            else {
                result.add(data.get(i));
            }
        }
        
        for (int i = 0 ; i < data.size() ; i++) {
            if (result.get(i) == k) {
                return select(k, )
            }
            if (result.get(i) == pivot) {
                return select(k)
            }
        }

    }
    */

    public static int select(int k, int[] data) {
        int lastPos = data.length - 1;
        int pivot = data[0];
        int pivotPos = 0;
        for (int pos = 1 ; pos < data.length ; pos++) {
            if (data[pos] < pivot) {
                int temp = data[pos];
                for (int i = pos ; i > pivotPos ; i--) {
                    data[i] = data[i - 1];
                }
                data[pivotPos] = temp;
                pivotPos = pivotPos + 1;
            }
        }

        if (k == pivotPos) {
            return pivot;
        }

        else if (k < pivotPos) {
            return select(k, Arrays.copyOfRange(data, 0, pivotPos));
        }

        else {
            return select(k - pivotPos - 1, Arrays.copyOfRange(data, pivotPos + 1, data.length));
        }
    }

    public static int select2(int k, int[] n) {
        int[] a = MergeSort.mergeSort(n);
        return a[k - 1];
    }

    public static void main(String[] args) {
        int[] test = new int[]{4, 5, 1, 2, 3, 7, 6, 9, 8, 0};

        for (int i = 0 ; i < 10 ; i++)
            System.out.println(select(i, test));
    }
}
