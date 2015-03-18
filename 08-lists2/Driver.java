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

        l.add(3, "hello");
        System.out.println("done");
        l.add(0, "hi");
        System.out.println("done");
        System.out.println(l);
        System.out.println(l.length());
        l.add(12, "end");
        System.out.println(l);
        System.out.println(l.get(12));
//        l.add(15, "bhsjdl");

        System.out.println(l.remove(10));
        System.out.println(l);

        System.out.println(l.remove("hi"));
        System.out.println(l);
    }
}
