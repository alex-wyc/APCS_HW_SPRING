import java.util.Arrays;

public class Driver {
    public static void main(String[] args) {
        int[] Arr = new int[]{13,5,7,1,8,10,14};
        System.out.println(Arrays.toString(Arr));

        Heap heap1 = new Heap(Arr);
        int[] sortedArr = heap1.heapSort();
        System.out.println(Arrays.toString(sortedArr));
    }
}
