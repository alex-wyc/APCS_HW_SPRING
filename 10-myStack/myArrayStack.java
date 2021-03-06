import java.util.*;

public class myArrayStack<E> {
    private E[] data;

    public myArrayStack() {
        data = (E[])new Object[0];
    }

    public void push(E s) {
        E[] data2 = (E[])new Object[data.length + 1];
        for (int i = 0 ; i < data.length ; i++) {
            data2[i + 1] = data[i];
        }
        data2[0] = s;
        data = data2;
    }

    public E top() {
        if (empty()) {
            throw new EmptyStackException();
        }
        return data[0];
    }

    public E pop() {
        if (empty()) {
            throw new EmptyStackException();
        }
        E[] data2 = (E[])new Object[data.length - 1];
        E retVal = data[0];
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
        for (E i : data) {
            s = s + i + "\n";
        }
        return s;
    }
}
