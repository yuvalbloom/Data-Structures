import java.util.Stack;

/**
 * AVLTree
 * <p>
 * An implementation of a AVL Tree with
 * distinct integer keys and info
 */

public class AVLTree {
    private IAVLNode root;
    private int length = 0;

    /**
     * public boolean empty()
     * <p>
     * returns true if and only if the tree is empty
     */
    public boolean empty() {
        return root == null;
    }

    /**
     * public String search(int k)
     * <p>
     * returns the info of an item with key k if it exists in the tree
     * otherwise, returns null
     */
    public String search(int k) {
        IAVLNode node = root;
        while (node != null) {
            if (k == node.getKey()) {
                return node.getValue();
            } else if (k > node.getKey()) { // proceed right in the tree if the key we search is bigger than the current key.
                node = node.getRight();
            } else { // proceed left in the tree if the key we search is smaller than the current key.
                node = node.getLeft();
            }
        }
        return null;
    }

    /**
     * public int insert(int k, String i)
     * <p>
     * inserts an item with key k and info i to the AVL tree.
     * the tree must remain valid (keep its invariants).
     * returns the number of rebalancing operations, or 0 if no rebalancing operations were necessary.
     * returns -1 if an item with key k already exists in the tree.
     */
    public int insert(int k, String i) {
        IAVLNode nodeToInsert = new AVLNode(k, i);
        if (empty()) { // if the tree is empty, we insert the node as the root.
            root = nodeToInsert;
            length++;
            return 0;
        }
        IAVLNode node = root;
        IAVLNode prev = null;
        while (node != null) {
            prev = node;
            if (k == node.getKey()) {
                return -1;
            } else if (k > node.getKey()) { // proceed right in the tree if the key we search is bigger than the current key.
                node = node.getRight();
            } else { // proceed left in the tree if the key we search is smaller than the current key.
                node = node.getLeft();
            }
        }
        length++;
        if (k < prev.getKey()) { // the node is the left child
            prev.setLeft(nodeToInsert);
            prev.setHeight(prev.getHeight() + 1);
        } else { // the node is the right child
            prev.setRight(nodeToInsert);
            prev.setHeight(prev.getHeight() + 1);
        }
        nodeToInsert.setParent(prev);
        return countRotations(nodeToInsert);
    }

    /**
     * public int delete(int k)
     * <p>
     * deletes an item with key k from the binary tree, if it is there;
     * the tree must remain valid (keep its invariants).
     * returns the number of rebalancing operations, or 0 if no rebalancing operations were needed.
     * returns -1 if an item with key k was not found in the tree.
     */
    public int delete(int k) {
        IAVLNode node = root;
        IAVLNode suc = root;
        while (node != null) {
            if (k == node.getKey()) {
                break;
            } else if (k > node.getKey()) { // proceed right in the tree if the key we search is bigger than the current key.
                node = node.getRight();
            } else { // proceed left in the tree if the key we search is smaller than the current key.
                node = node.getLeft();
            }
        }
        if (node == null) {
            return -1;
        }
        length--;
        if (node.getLeft() == null && node.getRight() == null) { // the node is a leaf
            if (k < node.getParent().getKey()) {
                node.getParent().setLeft(null);
            } else {
                node.getParent().setRight(null);
            }
            IAVLNode temp = node;
            node = node.getParent();
            temp.setParent(null);
            setNodeHeight(node);
        } else if (node.getLeft() == null || node.getRight() == null) { // the node has one child
            if (k < node.getParent().getKey()) { // node is the left child of its parent
                if (node.getLeft() == null) {
                    node.getParent().setLeft(node.getRight());
                } else {
                    node.getParent().setLeft(node.getLeft());
                }
            } else { // node is the Right child of its parent
                if (node.getLeft() == null) {
                    node.getParent().setRight(node.getRight());
                } else {
                    node.getParent().setRight(node.getLeft());
                }
            }
            IAVLNode temp = node;
            node = node.getParent();
            temp.setParent(null);
            setNodeHeight(node);
        } else { // the node has two children.
            IAVLNode find = node.getRight();
            suc = find;
            while (find != null) { // find the successor of node
                suc = find;
                find = find.getLeft();
            } // setting the successor of node instead of node
            if (suc.getParent() != node) {
                suc.getParent().setLeft(suc.getRight());
                suc.getRight().setParent(suc.getParent());
                suc.setRight(node.getRight());
            }
            suc.setParent(node.getParent());
            suc.setLeft(node.getLeft());
            if (k < node.getParent().getKey()) {
                node.getParent().setLeft(suc);
            } else {
                node.getParent().setRight(suc);
            }
            node.setLeft(null);
            node.setRight(null);
            node.setParent(null);
            setNodeHeight(suc);
            node = suc;
        }
        return countRotations(node);
    }

    /**
     * private int countRotations(IAVLNode node)
     * Calculates the number of rotation needed to maintain AVL tree,
     * and performs the needed rotations
     * Time Complexity: O(logn)
     */
    private int countRotations(IAVLNode node) {
        int bf, count = 0;
        while (node != null) {
            bf = BF(node);
            if (bf == -2) { // check if a rotation is needed and which.
                if (BF(node.getRight()) == -1 || BF(node.getRight()) == 0) { // BF = 0 can only happen at deletion
                    LeftRotate(node);
                    count++;
                } else if (BF(node.getRight()) == 1) {
                    rightRotate(node.getRight());
                    LeftRotate(node);
                    count += 2;
                }
            } else if (bf == 2) {
                if (BF(node.getLeft()) == 1 || BF(node.getLeft()) == 0) {
                    rightRotate(node);
                    count++;
                } else if (BF(node.getLeft()) == -1) {
                    LeftRotate(node.getLeft());
                    rightRotate(node);
                    count += 2;
                }
            }
            node = node.getParent();
            if (node != null) {
                setNodeHeight(node); // setting the height of each node on the path from the given node to the root.
            }
        }
        return count;
    }

    /**
     * private int BF(IAVLNode node)
     * Calculates the balance factor of a given node
     * Time Complexity: O(1)
     */
    private int BF(IAVLNode node) {
        if (node.getLeft() != null && node.getRight() != null) { // if node has two children
            return node.getLeft().getHeight() - node.getRight().getHeight();
        } else if (node.getLeft() != null && node.getRight() == null) { // if node has a left child
            return 1 + node.getLeft().getHeight();
        } else if (node.getLeft() == null && node.getRight() != null) { // if node has a right child
            return - (1 + node.getRight().getHeight());
        } else { // if node is a leaf
            return 0;
        }
    }

    /**
     * private void setNodeHeight (IAVLNode node)
     * Updates the height of a given node
     * Time Complexity: O(1)
     */
    private void setNodeHeight (IAVLNode node) {
        if (node.getLeft() != null && node.getRight() != null) { // if node has two children
            node.setHeight(1 + Math.max(node.getLeft().getHeight(), node.getRight().getHeight()));
        } else if (node.getLeft() != null && node.getRight() == null) { // if node has a left child
            node.setHeight(1 + node.getLeft().getHeight());
        } else if (node.getLeft() == null && node.getRight() != null) { // if node has a right child
            node.setHeight(1 + node.getRight().getHeight());
        } else { // if node is a leaf
            node.setHeight(0);
        }
    }

    /**
     * private void leftRotate(IAVLNode node)
     * <p>
     * Performs left rotation on the "criminals"
     * Time Complexity: O(1)
     */
    private void LeftRotate(IAVLNode node) {
        IAVLNode y = node.getRight();
        node.setRight(y.getLeft());
        if (y.getLeft() != null) {
            y.getLeft().setParent(node);
        }
        y.setParent(node.getParent());
        if (node.getParent() == null) {
            root = y;
        } else if (node == node.getParent().getLeft()) {
            node.getParent().setLeft(y);
        } else {
            node.getParent().setRight(y);
        }
        y.setLeft(node);
        node.setParent(y);
        setNodeHeight(node);
    }

    /**
     * private void rightRotate(IAVLNode node)
     * <p>
     * Performs right rotation on the "criminals"
     * Time Complexity: O(1)
     */
    private void rightRotate(IAVLNode node) {
        IAVLNode y = node.getLeft();
        node.setLeft(y.getRight());
        if (y.getRight() != null) {
            y.getRight().setParent(node);
        }
        y.setParent(node.getParent());
        if (node.getParent() == null) {
            root = y;
        } else if (node == node.getParent().getLeft()) {
            node.getParent().setLeft(y);
        } else {
            node.getParent().setRight(y);
        }
        y.setRight(node);
        node.setParent(y);
        setNodeHeight(node);
    }

    /**
     * public String min()
     * <p>
     * Returns the info of the item with the smallest key in the tree,
     * or null if the tree is empty
     * Time Complexity: O(logn)
     */
    public String min() {
        if (empty()) {
            return null;
        }
        IAVLNode node = root;
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node.getValue();
    }

    /**
     * public String max()
     * <p>
     * Returns the info of the item with the largest key in the tree,
     * or null if the tree is empty
     * Time Complexity: O(logn)
     */
    public String max() {
        if (empty()) {
            return null;
        }
        IAVLNode node = root;
        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node.getValue();
    }

    /**
     * public int[] keysToArray()
     * Returns a sorted array which contains all keys in the tree,
     * or an empty array if the tree is empty.
     * Time Complexity: O(n)
     */
    public int[] keysToArray() {
        Stack<Integer> st = new Stack<>();
        IAVLNode node = root;
        Stack<Integer> stToReturn = keysToArrayRec(st, node); // calls a recursive function that performs an in-order traversal
        Object[] arr = stToReturn.toArray();
        int[] arrToReturn = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arrToReturn[i] = (int) arr[i];
        }
        return arrToReturn;
    }


    /**
     * private Stack<Integer> keysToArrayRec(Stack<Integer> st, IAVLNode node)
     * Helper function for keysToArray, acts recursively.
     * Performs in-order traversal on a given subtree.
     * Time Complexity: O(n)
     */
    private Stack<Integer> keysToArrayRec(Stack<Integer> st, IAVLNode node) {
        if (node == null) {
            return st;
        }
        keysToArrayRec(st, node.getLeft());
        st.push(node.getKey());
        keysToArrayRec(st, node.getRight());
        return st;
    }


    /**
     * public String[] infoToArray()
     * Returns an array which contains all info in the tree,
     * sorted by their respective keys,
     * or an empty array if the tree is empty.
     * Time Complexity: O(n)
     */
    public String[] infoToArray() {
        Stack<String> st = new Stack<>();
        IAVLNode node = root;
        Stack<String> stToReturn = infoToArrayRec(st, node); // calls a recursive function that performs an in-order traversal
        Object[] arr = stToReturn.toArray();
        String[] arrToReturn = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            arrToReturn[i] = (String) arr[i];
        }
        return arrToReturn;
    }

    /**
     * private Stack<Integer> infoToArrayRec(Stack<Integer> st, IAVLNode node)
     * Helper function for infoToArray, acts recursively.
     * Performs in-order traversal on a given subtree.
     * Time Complexity: O(n)
     */
    private Stack<String> infoToArrayRec(Stack<String> st, IAVLNode node) {
        if (node == null) {
            return st;
        }
        infoToArrayRec(st, node.getLeft());
        st.push(node.getValue());
        infoToArrayRec(st, node.getRight());
        return st;
    }

    /**
     * public int size()
     * <p>
     * Returns the number of nodes in the tree.
     * <p>
     */
    public int size() {
        return length;
    }

    /**
     * public int getRoot()
     * Returns the root AVL node, or null if the tree is empty
     * precondition: none
     * postcondition: none
     * Time Complexity: O(1)
     */
    public IAVLNode getRoot() {
        return root;
    }

    /**
     * public int getRoot()
     * Returns the root AVL node, or null if the tree is empty.
     * Time Complexity: O(1)
     */
    public void setRoot(IAVLNode root) {
        this.root = root;
    }

    /**
     * public interface IAVLNode
     * ! Do not delete or modify this - otherwise all tests will fail !
     */
    public interface IAVLNode {
        public int getKey(); //returns node's key

        public String getValue(); //returns node's value [info]

        public void setLeft(IAVLNode node); //sets left child

        public IAVLNode getLeft(); //returns left child (if there is no left child return null)

        public void setRight(IAVLNode node); //sets right child

        public IAVLNode getRight(); //returns right child (if there is no right child return null)

        public void setParent(IAVLNode node); //sets parent

        public IAVLNode getParent(); //returns the parent (if there is no parent return null)

        public void setHeight(int height); // sets the height of the node

        public int getHeight(); // Returns the height of the node
    }

    /**
     * public class AVLNode
     * <p>
     * If you wish to implement classes other than AVLTree
     * (for example AVLNode), do it in this file, not in
     * another file.
     * This class can and must be modified.
     * (It must implement IAVLNode)
     */
    public class AVLNode implements IAVLNode {
        private int key;
        private String info;
        private IAVLNode right = null;
        private IAVLNode left = null;
        private IAVLNode parent;
        private int treeHeight;
        private int size;

        /**
         * public AVLNode(int k, String s)
         * builder function for AVLNode
         * Time Complexity: O(1)
         */
        public AVLNode(int k, String s) {
            key = k;
            info = s;
        }

        public int getSize() { // returns the size of the node. time complexity: O(1)
            return size;
        }

        public void setSize(int size) { // updates the size of the node. time complexity: O(1)
            this.size = size;
        }

        public int getKey() { // returns the key of the node. time complexity: O(1)
            return key;
        }

        public String getValue() { // returns the value of the node. time complexity: O(1)
            return info;
        }

        public void setLeft(IAVLNode node) { // updates the left child of the node. time complexity: O(1)
            left = node;
        }

        public IAVLNode getLeft() { // returns the left child of the node. time complexity: O(1)
            return left;
        }

        public void setRight(IAVLNode node) { // updates the right child of the node. time complexity: O(1)
            right = node;
        }

        public IAVLNode getRight() { // returns the right child of the node. time complexity: O(1)
            return right;
        }

        public void setParent(IAVLNode node) { // updates the parent of the node. time complexity: O(1)
            parent = node;
        }

        public IAVLNode getParent() { // returns the parent of the node. time complexity: O(1)
            return parent;
        }

        public void setHeight(int height) { // updates the height of the node. time complexity: O(1)
            treeHeight = height;
        }

        public int getHeight() { // returns the height of the node. time complexity: O(1)
            return treeHeight;
        }


        public void printSubTree(boolean compact) {
            int n = this.getHeight();
            int h = (int) Math.pow(2, n + 1) + n;
            int w = h * 2 + 3;
            if (compact) h = 1+3*n;
            String[][] res = new String[h][w];
            printSubTreeRec(res, 0, w / 2, (int) Math.pow(2, n), compact);
            for (String[] row : res) {
                for (String s : row) {
                    if (s == null)
                        System.out.print(" ");
                    else if (s.contentEquals("x"))
                        System.out.print("");
                    else
                        System.out.print(s);
                }
                System.out.println();
            }
        }

        public void printSubTreeRec(String[][] res, int row, int pos, int length, boolean compact) {
            res[row][pos] = Integer.toString(this.getKey()) + "-"+Integer.toString(this.getSize());
            int nofDigits = (int) Math.log10(this.getKey()) + 1;
            for (int i = 1; i < nofDigits+2; i++) {
                res[row][pos + i] = "x";
            }
            if (this.getHeight() > 0) {
                int i;
                for (i = 1; i < length; i++) { // prints branches
                    row = (compact) ? ((i==1||i==length-1) ? row+1 : row+0) : row + 1;
                    if ((AVLNode) getLeft() != null)
                        res[row][pos - i] = (compact) ? ((i==1 || i==length-1)? "/" : "-") : "/";
                    if ((AVLNode) getRight() != null)
                        res[row][pos + i] = (compact) ? ((i==1 || i==length-1)? "\\" : "-") : "\\";
                }
                if ((AVLNode) getLeft() != null)
                    ((AVLNode) getLeft()).printSubTreeRec(res, row+1, pos - i, length / 2, compact);
                if ((AVLNode) getRight() != null)
                    ((AVLNode) getRight()).printSubTreeRec(res, row+1, pos + i, length / 2, compact);
            }

        }
    }

}



