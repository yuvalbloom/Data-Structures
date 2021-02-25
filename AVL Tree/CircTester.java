import java.util.Arrays;

public class CircTester {
    private static int key;
    private static String info;

    public static void main(String args[]) {

        CircularList circ1 = new CircularList(50);
        CircularList circ2 = new CircularList(50);
        CircularList circ3 = new CircularList(50);
        CircularList circ4 = new CircularList(50);

        // Insert

        System.out.println("Circ1");
        circ1.insert(0, 10, "a");
        circ1.insert(1, 20, "b");
        circ1.insert(0, 30, "c");
        circ1.insert(1, 40, "d");
        System.out.println(circ1.retrieve(0).getInfo());
        System.out.println(circ1.retrieve(0).getKey());
        System.out.println(circ1.retrieve(1).getInfo());
        System.out.println(circ1.retrieve(1).getKey());
        System.out.println(circ1.retrieve(2).getInfo());
        System.out.println(circ1.retrieve(2).getKey());
        System.out.println(circ1.retrieve(3).getInfo());
        System.out.println(circ1.retrieve(3).getKey());

        circ1.delete(1);
        circ1.delete(3);

        System.out.println();
        System.out.println(circ1.retrieve(0).getInfo());
        System.out.println(circ1.retrieve(0).getKey());
        System.out.println(circ1.retrieve(1).getInfo());
        System.out.println(circ1.retrieve(1).getKey());

/**
        System.out.println("Circ2");
        circ2.insert(0, 10, "a");
        circ2.insert(0, 20, "b");
        circ2.insert(0, 30, "c");
        circ2.insert(0, 40, "d");
        System.out.println(circ2.retrieve(0).getInfo());
        System.out.println(circ2.retrieve(0).getKey());
        System.out.println(circ2.retrieve(1).getInfo());
        System.out.println(circ2.retrieve(1).getKey());
        System.out.println(circ2.retrieve(2).getInfo());
        System.out.println(circ2.retrieve(2).getKey());
        System.out.println(circ2.retrieve(3).getInfo());
        System.out.println(circ2.retrieve(3).getKey());
**/



    }

}