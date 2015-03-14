import java.util.Random;

public class Driver {

    public static void main(String[] args) {
        Random randgen = new Random();
        Llist l = new Llist();
        for (int i = 0 ; i < 10 ; i++) {
            l.add("" + i);
        }

        System.out.println(l);

        System.out.println(l.find(5) + "\n" + l.find(0) + "\n" + l.find(12));

        l.insert(3, "hello");
        System.out.println("done");
        l.insert(0, "hi");
        System.out.println("done");
        System.out.println(l);
        l.insert(15, "bhsjdl");
    }
}
