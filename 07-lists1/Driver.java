import java.util.Random;

public class Driver {

    public static void main(String[] args) {
        Random randgen = new Random();
        Llist l = new Llist();
        for (int i = 0 ; i < 10 ; i++) {
            l.add("" + randgen.nextInt(100));
        }

        System.out.println(l);

        System.out.println(l.get(5) + "\n" + l.get(0) + "\n" + l.get(12));

        l.insert(3, "hello");
        System.out.println("done");
        l.insert(0, "hi");
        System.out.println("done");
        System.out.println(l);
        System.out.println(l.length());
        l.insert(12, "end");
        System.out.println(l);
        System.out.println(l.get(12));
        l.insert(15, "bhsjdl");
    }
}
