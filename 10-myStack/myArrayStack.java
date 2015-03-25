import java.util.*;

public class myArrayStack {
    private int[] data;

    public myArrayStack() {
        data = new int[0];
    }

    public void push(int s) {
        int[] data2 = new int[data.length + 1];
        for (int i = 0 ; i < data.length ; i++) {
            data2[i + 1] = data[i];
        }
        data2[0] = s;
        data = data2;
    }

    public int top() {
        if (empty()) {
            throw new EmptyStackException();
        }
        return data[0];
    }

    public int pop() {
        if (empty()) {
            throw new EmptyStackException();
        }
        int[] data2 = new int[data.length - 1];
        int retVal = data[0];
        for (int i = 0 ; i < data.length - 1 ; i++) {
            data2[i] = data[i + 1];
        }
        data = data2;
        return retVal;
    }

    public boolean empty() {
        return (data.length == 0);
    }

    public String toString() {
        String s = "";
        for (int i : data) {
            s = s + i + "\n";
        }
        return s;
    }
}
