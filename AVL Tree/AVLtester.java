import java.util.Arrays;

public class AVLtester {
    public static void main(String args[]) {
        AVLTree tree1 = new AVLTree();
        AVLTree tree2 = new AVLTree();
        AVLTree tree3 = new AVLTree();
        TreeList treelst1 = new TreeList();
        TreeList treelst2 = new TreeList();

        

        System.out.println("tree1");
        System.out.println("Insert");
        int x = tree1.insert(5,"a");
        tree1.insert(3,"b");
        int xx = tree1.insert(4,"c");
        tree1.insert(7,"d");
        int y = tree1.insert(3,"b");
        int minus = tree1.insert(-1,"e");
        tree1.insert(2,"f");
        int minus2 = tree1.insert(-6,"g");
        tree1.insert(8,"h");
        System.out.println(x);
        System.out.println(xx);
        System.out.println(y);
        System.out.println(minus);
        System.out.println(minus2);
        System.out.println(tree1.search(5));
        System.out.println(tree1.search(3));
        System.out.println(tree1.search(4));
        System.out.println(tree1.search(7));
        System.out.println(tree1.search(-1));
        System.out.println(tree1.search(2));
        System.out.println(tree1.search(-6));
        System.out.println(tree1.search(8));
        System.out.println(tree1.search(10));
        System.out.println(tree1.min());
        System.out.println(tree1.max());



        System.out.println(Arrays.toString(tree1.keysToArray()));
        System.out.println("Delete");
        int z = tree1.delete(8);
        int w = tree1.delete(3);
        System.out.println(tree1.search(4));
        System.out.println(tree1.search(1));
        System.out.println(z);
        System.out.println(w);
        System.out.println(tree1.search(8));
        System.out.println(Arrays.toString(tree1.keysToArray()));
        System.out.println(Arrays.toString(tree1.infoToArray()));
        System.out.println(tree1.min());
        System.out.println(tree1.max());


        System.out.println("tree 2");

        System.out.println(Arrays.toString(tree1.keysToArray()));
        System.out.println(tree1.size());


        tree2.insert(1, "A");
        tree2.insert(2, "B");
        int a = tree2.insert(3, "C");
        int b = tree2.insert(4, "D");
        int c = tree2.insert(4, "T");
        int e = tree2.insert(5, "E");
        int f = tree2.insert(6, "F");
        int g = tree2.insert(7, "G");
        int d = tree2.insert(8, "H");
        System.out.print(tree2.empty());
        System.out.println();
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(e);
        System.out.println(f);
        System.out.println(g);
        System.out.println(d);
        System.out.println(tree2.search(1));
        System.out.println(tree2.search(2));
        System.out.println(tree2.search(3));
        System.out.println(tree2.search(4));
        System.out.println(tree2.search(5));
        System.out.println(tree2.search(6));
        System.out.println(tree2.search(7));
        System.out.println(tree2.search(8));
        System.out.println(tree2.search(10));
        System.out.println(tree2.min());
        System.out.println(tree2.max());
        System.out.println(tree2.size());



        System.out.println("tree 3");
        System.out.print(tree3.empty());
        System.out.println();
        int kk = tree3.insert(8, "A");
        int ll = tree3.insert(5, "B");
        int aa = tree3.insert(10, "C");
        int ba = tree3.insert(3, "D");
        int ca = tree3.insert(6, "E");
        int ea = tree3.insert(11, "F");
        int fa = tree3.insert(2, "G");
        int ga = tree3.insert(4, "H");
        int da = tree3.insert(7, "I");
        int ha = tree3.insert(12, "J");
        int ia = tree3.insert(9, "K");
        int ja = tree3.insert(1, "L");
        System.out.print(tree3.empty());
        System.out.println();
        System.out.println(kk);
        System.out.println(ll);
        System.out.println(aa);
        System.out.println(ba);
        System.out.println(ca);
        System.out.println(ea);
        System.out.println(fa);
        System.out.println(ga);
        System.out.println(da);
        System.out.println(ha);
        System.out.println(ia);
        System.out.println(ja);
        System.out.println(tree3.search(1));
        System.out.println(tree3.search(2));
        System.out.println(tree3.search(3));
        System.out.println(tree3.min());
        System.out.println(tree3.max());
        System.out.println(tree3.size());

        System.out.println(Arrays.toString(tree3.keysToArray()));
        System.out.println("Delete");
        int dd = tree3.delete(11);
        System.out.println(tree3.search(11));
        System.out.println(dd);

        tree3.insert(14, "O");
        int ee = tree3.delete(2);
        System.out.println(tree3.search(2));
        System.out.println(ee);

        System.out.println(Arrays.toString(tree3.keysToArray()));
        System.out.println(Arrays.toString(tree3.infoToArray()));
        System.out.println(tree3.min());
        System.out.println(tree3.max());


         

        System.out.println();
        System.out.println("tree list 1");


        int i = treelst1.insert(0, 10, "Z");
        int j = treelst1.insert(1, 15, "N");
        int k = treelst1.insert(2, 5, "O");
        int l = treelst1.insert(3, 25, "d");

        System.out.println(i);
        System.out.println(j);
        System.out.println(k);
        System.out.println(l);


        System.out.println(Item.Print(treelst1.retrieve(0)));
        System.out.println(Item.Print(treelst1.retrieve(1)));
        System.out.println(Item.Print(treelst1.retrieve(2)));
        System.out.println(Item.Print(treelst1.retrieve(3)));


        System.out.println(treelst1.insert(3, 20, "T"));

        System.out.println(Item.Print(treelst1.retrieve(0)));
        System.out.println(Item.Print(treelst1.retrieve(1)));
        System.out.println(Item.Print(treelst1.retrieve(2)));
        System.out.println(Item.Print(treelst1.retrieve(3)));
        System.out.println(Item.Print(treelst1.retrieve(4)));


        System.out.println(treelst1.insert(0, 1, "B"));
        System.out.println(treelst1.insert(0, 3, "B"));
        
        System.out.println(Item.Print(treelst1.retrieve(0)));
        System.out.println(Item.Print(treelst1.retrieve(1)));
        System.out.println(Item.Print(treelst1.retrieve(2)));
        System.out.println(Item.Print(treelst1.retrieve(3)));
        System.out.println(Item.Print(treelst1.retrieve(4)));
        System.out.println(Item.Print(treelst1.retrieve(5)));
        System.out.println(Item.Print(treelst1.retrieve(6)));
        System.out.println(Item.Print(treelst1.retrieve(7)));


        treelst1.getRoot().printSubTree(true);

        System.out.println("Delete");
        int mm = treelst1.delete(5);
        System.out.println(mm);
        System.out.println(Item.Print(treelst1.retrieve(0)));
        System.out.println(Item.Print(treelst1.retrieve(1)));
        System.out.println(Item.Print(treelst1.retrieve(2)));
        System.out.println(Item.Print(treelst1.retrieve(3)));
        System.out.println(Item.Print(treelst1.retrieve(4)));
        System.out.println(Item.Print(treelst1.retrieve(5)));

        treelst1.getRoot().printSubTree(true);

        int nn = treelst1.delete(5);
        System.out.println(nn);

        treelst1.getRoot().printSubTree(true);

        treelst1.insert(5, 3, "O");
        treelst1.insert(6, 2, "d");
        treelst1.getRoot().printSubTree(true);

        treelst1.insert(5, 30, "O");
        treelst1.insert(6, 35, "d");
        treelst1.insert(5, 40, "O");
        treelst1.insert(6, 45, "d");
        treelst1.insert(5, 50, "O");
        treelst1.insert(6, 55, "d");
        treelst1.insert(5, 60, "O");

        treelst1.getRoot().printSubTree(true);

        treelst1.insert(6, 65, "d");

        treelst1.getRoot().printSubTree(true);

        int xxx = treelst1.delete(3);
        System.out.println(xxx);

        treelst1.getRoot().printSubTree(true);

        int yy = treelst1.delete(4);
        System.out.println(yy);

        treelst1.getRoot().printSubTree(true);



       
        int n = treelst1.delete(7);
        System.out.println(n);
        int s = treelst1.delete(1);
        System.out.println(s);
        System.out.println(Item.Print(treelst1.retrieve(0)));
        System.out.println(Item.Print(treelst1.retrieve(1)));
        System.out.println(Item.Print(treelst1.retrieve(2)));
        System.out.println(Item.Print(treelst1.retrieve(3)));
        System.out.println(Item.Print(treelst1.retrieve(4)));
        int r = treelst1.delete(0);
        System.out.println(r);
        System.out.println(Item.Print(treelst1.retrieve(0)));
        System.out.println(Item.Print(treelst1.retrieve(1)));
        System.out.println(Item.Print(treelst1.retrieve(2)));
        System.out.println(Item.Print(treelst1.retrieve(3)));

        int o = treelst1.delete(0);
        System.out.println(o);
        System.out.println(Item.Print(treelst1.retrieve(0)));
        System.out.println(Item.Print(treelst1.retrieve(1)));
        System.out.println(Item.Print(treelst1.retrieve(2)));



        System.out.println("tree list 1");
        int i2 = treelst1.insert(0, 10, "Z");
        int j2 = treelst1.insert(0, 15, "N");
        int k2 = treelst1.insert(2, 5, "O");
        int l2 = treelst1.insert(2, 25, "d");
        int kk2 = treelst1.insert(0, 45, "O");
        int ll2 = treelst1.insert(5, 30, "d");
        int ii = treelst1.insert(5, 33, "d");
        int jj = treelst1.insert(7, 35, "d");


        System.out.println(Item.Print(treelst1.retrieve(0)));
        System.out.println(Item.Print(treelst1.retrieve(1)));
        System.out.println(Item.Print(treelst1.retrieve(2)));
        System.out.println(Item.Print(treelst1.retrieve(3)));
        System.out.println(Item.Print(treelst1.retrieve(4)));
        System.out.println(Item.Print(treelst1.retrieve(5)));
        System.out.println(Item.Print(treelst1.retrieve(6)));
        System.out.println(Item.Print(treelst1.retrieve(7)));

        int n2 = treelst1.delete(5);

        System.out.println("\n" + Item.Print(treelst1.retrieve(0)));
        System.out.println(Item.Print(treelst1.retrieve(1)));
        System.out.println(Item.Print(treelst1.retrieve(2)));
        System.out.println(Item.Print(treelst1.retrieve(3)));
        System.out.println(Item.Print(treelst1.retrieve(4)));
        System.out.println(Item.Print(treelst1.retrieve(5)));
        System.out.println(Item.Print(treelst1.retrieve(6)));



         

    }
}