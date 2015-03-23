import java.io.*;
import java.util.*;

public class Driver {
    public static void main(String[] args) {
        Llist<Integer> l = new Llist<Integer>();
        for (int i = 0 ; i < 10 ; i++) {
            l.add(i);
        }
        System.out.println(l);

        Iterator<Integer> iter = l.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
            iter.remove();
        }

        System.out.println(l);
    }
}
