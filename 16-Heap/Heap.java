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
        effectiveLength = size;
    }

    public boolean treeEnd(int index) {
        return 2 * index > effectiveLength;
    }

    public void pushDown(int index) {
        if (treeEnd(index)) {
            return;
        }

        if (heap[2 * index] < heap[index]) {
            int tmp = heap[2 * index];
            heap[2 * index] = heap[index];
            heap[index] = tmp;
        }
        else if (heap[2 * index + 1] < heap[index]) {
            int tmp = heap[2 * index + 1];
            heap[2 * index + 1] = heap[index];
            heap[index] = tmp;
        }

        pushDown(2 * index);
        pushDown(2 * index + 1);
    }

    public int removeMin() {
        int tmp = heap[effectiveLength - 1];
        heap[effectiveLength - 1] = heap[0];
        heap[0] = tmp;
        effectiveLength--;
        pushDown(0);
    }

    public void heapSort
}
