import java.util.Arrays;

public class Heap {
    int[] heap;
    int effectiveLength;

    public Heap(int[] arr) {
        heap = arr;
        effectiveLength = arr.length;
        pushDown(0);
    }

    public Heap(int size) {
        heap = new int[size];
        effectiveLength = 0;
    }

    private boolean treeEnd(int index) {
        return 2 * index > effectiveLength;
    }

    private void pushDown(int index) {
        int length = index + 1;
        if (2 * length >= effectiveLength) {
            return;
        }

        while (heap[2 * length - 1] < heap[index]) {
            int tmp = heap[2 * length - 1];
            heap[2 * length - 1] = heap[index];
            heap[index] = tmp;
            pushDown(2 * length - 1);
        }

        while (heap[2 * length] < heap[index]) {
            int tmp = heap[2 * length];
            heap[2 * length] = heap[index];
            heap[index] = tmp;
            pushDown(2 * length);
        }
    }

    public int removeMin() {
        heap[0] = heap[effectiveLength - 1];
        heap[effectiveLength - 1] = 0;
        effectiveLength--;
        pushDown(0);
        return heap[effectiveLength];
    }

    public int[] heapSort() {
        while (effectiveLength > 0) {
            int tmp = heap[effectiveLength - 1];
            heap[effectiveLength - 1] = heap[0];
            heap[0] = tmp;
            effectiveLength--;
            pushDown(0);
        }
        return heap;
    }

    public int getSize() {
        return effectiveLength;
    }

    public boolean insert(int newVal) {
        if (effectiveLength == heap.length) {
            return false;
        }
        heap[effectiveLength] = newVal;
        siftUp(effectiveLength);
        effectiveLength++;
        return true;
    }

    private void siftUp(int index) {
        if (index == 0) {
            return;
        }
        int parentIndex = (index + 1) / 2 - 1;
        if (heap[index] < heap[parentIndex]) {
            int tmp = heap[parentIndex];
            heap[parentIndex] = heap[index];
            heap[index] = tmp;
            siftUp(parentIndex);
        }
        return;
    }

    public String toString() {
        String retVal = "[";
        for (int i = 0 ; i < effectiveLength ; i++) {
            retVal = retVal + heap[i] + ", ";
        }
        retVal = retVal.substring(0, retVal.length() - 2) + "]";
        return retVal;
    }
}
