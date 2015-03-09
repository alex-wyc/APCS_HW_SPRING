import java.util.Arrays;
public class QuickSelect {
    public int quickSelect(int[] a, int k, int low, int high) {
        // Note: we select k - 1 because the parameter k represents a position, rather than an index
        if (low >= high) {
            return a[k-1];
        }
        int[] newArr = new int[a.length];
        int pivotIndex = (int) ((high - low + 1) * Math.random() + low);
        int pivot = a[pivotIndex];
        int lowIndex = low, highIndex = high;
        for (int i = low;i <= high;i++) {
            if (a[i] == pivot)
                continue;
            if (a[i] > pivot) {
                newArr[highIndex--] = a[i];
            }
            else {
                newArr[lowIndex++] = a[i];
            }
        }
        pivotIndex = lowIndex; // Update the new index of the pivot
        while (lowIndex <= highIndex) { // Fill up duplicates of the pivot value
            newArr[lowIndex++] = pivot;
        }
        if (newArr[k-1] == pivot) // We know the pivot is in the final position, so we can check if the pivot is our answer
            return pivot;
        else {
            if (pivotIndex > k-1){ // Make bounds approach k - 1
                return quickSelect(newArr, k, low, pivotIndex - 1);
            }
            else{
                return quickSelect(newArr, k, pivotIndex + 1, high);
            }
        }
    }

    public static void main(String[] args) {
        int[] a = new int[] {1, 5, 3, 2, 10, 15, 8, 3, 6};
        QuickSelect qs = new QuickSelect();
        int result = qs.quickSelect(a, 3, 0, a.length - 1);
        System.out.println("QuickSelect on a: " + result);
        if (result != 3) {
            System.out.println("FAILED!");
        }               
        else {         
            System.out.println("SUCCESS!");
        }
        
    }
}
