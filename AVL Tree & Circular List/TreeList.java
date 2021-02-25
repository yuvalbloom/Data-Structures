import java.util.Stack;

/**
 *
 * Tree list
 *
 * An implementation of a Tree list with  key and info
 *
 */
 public class TreeList{
     private AVLTree.IAVLNode root; // the root of the tree list
     private int length = 0; // the length of the tree list

   /**
   * public Item retrieve(int i)
   * Returns the item in the ith position if it exists in the list.
   * Otherwise, returns null
   * Time Complexity: O(logn)
   */
  public Item retrieve(int i)
  {
      if (i < 0 || i > length - 1) {
          return null;
      }
      AVLTree.AVLNode node = (AVLTree.AVLNode) root;
      AVLTree.AVLNode left = (AVLTree.AVLNode) node.getLeft();
      int rank = 0;
      if (left == null) {
          rank++;
      }
      else {
          rank += left.getSize() + 1;
      }
      while (node != null) {
          if (rank > i + 1) { // proceed left in the tree if (i+1) is smaller than the current rank.
              node = (AVLTree.AVLNode) node.getLeft();
              rank -= left.getSize() + 1;
          }
          else if (rank < i + 1) { // proceed right in the tree if (i+1) is bigger than the current rank.
              node = (AVLTree.AVLNode) node.getRight();
          }
          else if (rank == i + 1) { // found the wanted node!
              return new Item (node.getKey(), node.getValue());
          }
          left = (AVLTree.AVLNode) node.getLeft();
          if (left == null) {
              rank++;
          }
          else {
              rank += left.getSize() + 1;
          }
      }
      return null; // we shouldn't get here
  }

 /**
  * private int getRank (AVLTree.IAVLNode node)
  * Returns the rank of the given node in a tree list
  * Time Complexity: O(logn)
  */
  private int getRank (AVLTree.IAVLNode node) {
      int rank = 0;
      if (node.getLeft() != null) {
          AVLTree.AVLNode left = (AVLTree.AVLNode) node.getLeft();
          rank += left.getSize() + 1;
      }
      else {
          rank++;
      }
      AVLTree.IAVLNode prev = node;
      node = node.getParent();
      while (node != null) {
          if (node.getRight() == prev) { // adds the size of the left subtree only if we went up from the right child.
              AVLTree.AVLNode left = (AVLTree.AVLNode) node.getLeft();
              if (left != null) {
                  rank += left.getSize() + 1;
              }
              else {
                  rank++;
              }
          }
          node = node.getParent();
          prev = prev.getParent();
      }
      return rank;
  }

  /**
   * public int insert(int i, int k, String s)
   * Inserts an item to the ith position in list  with key k and  info s.
   * Returns -1 if i<0 or i>n otherwise return 0.
   * Time Complexity: O(logn)
   */
   public int insert(int i, int k, String s) {
       AVLTree.AVLNode nodeToInsert = new AVLTree().new AVLNode(k, s);
       if (root == null) { // if the tree is empty, we insert the node as the root.
           root = nodeToInsert;
           length = 1;
           root.setHeight(1);
           return 0;
       }
       if (i<0 || i>length) {
           return -1;
       }
       AVLTree.IAVLNode node = root;
       if (i == length) { // if i = length, we look for the max node and insert the given node after
           while (node.getRight() != null) {
               node = node.getRight();
           }
           node.setRight(nodeToInsert);
           nodeToInsert.setParent(node);
           setNodeHeight(node);
       }
       else {
           AVLTree.AVLNode left = (AVLTree.AVLNode) node.getLeft();
           int rank = 0;
           if (left == null) {
               rank++;
           }
           else {
               rank += left.getSize() + 1;
           }
           while (node != null) {
               if (rank > i + 1) { // proceed left in the tree if (i+1) is smaller than the current rank.
                   node = node.getLeft();
                   rank -= left.getSize() + 1;
               }
               else if (rank < i + 1) { // proceed right in the tree if (i+1) is bigger than the current rank.
                   node = node.getRight();
               }
               else if (rank == i + 1) { // found the wanted node!
                   break;
               }
               left = (AVLTree.AVLNode) node.getLeft();
               if (left == null) {
                   rank++;
               }
               else {
                   rank += left.getSize() + 1;
               }
           }
           if (node.getLeft() == null) { // insert before the current node
               node.setLeft(nodeToInsert);
               nodeToInsert.setParent(node);
           }
           else {
               AVLTree.AVLNode pre = (AVLTree.AVLNode) node.getLeft();
               while (pre.getRight() != null) { // search for the predecessor
                   pre = (AVLTree.AVLNode) pre.getRight();
               }
               pre.setRight(nodeToInsert);
               nodeToInsert.setParent(pre);
           }
       }
       length++;
       insertionSize(nodeToInsert);
       Rotations(nodeToInsert);
       return 0;
   }

  /**
   * public int delete(int i)
   * deletes an item in the ith position from the list.
	* returns -1 if i<0 or i>n-1 otherwise returns 0.
   * Time Complexity: O(logn)
   */
   public int delete(int i)  {
	   if (i < 0 || i > length - 1) {
	       return -1;
       }
       AVLTree.IAVLNode node = root;
       AVLTree.AVLNode left = (AVLTree.AVLNode) node.getLeft();
       if (i == length - 1 && length == 1) {
           root = null;
           length--;
           return 0;
       }
       int rank = 0;
       if (left == null) {
           rank++;
       }
       else {
           rank += left.getSize() + 1;
       }
       while (node != null) {
           if (rank > i + 1) { // proceed left in the tree if (i+1) is smaller than the current rank.
               node = (AVLTree.AVLNode) node.getLeft();
               rank -= left.getSize() + 1;
           }
           else if (rank < i + 1) { // proceed right in the tree if (i+1) is bigger than the current rank.
               node = (AVLTree.AVLNode) node.getRight();
           }
           else if (rank == i + 1) { // found the wanted node!
               break;
           }
           left = (AVLTree.AVLNode) node.getLeft();
           if (left == null) {
               rank++;
           }
           else {
               rank += left.getSize() + 1;
           }
       }
       if (node.getLeft() == null && node.getRight() == null) { // the node is a leaf
           if (i + 1 < getRank(node.getParent())) {
               node.getParent().setLeft(null);
           } else {
               node.getParent().setRight(null);
           }
           node.getParent().setHeight(node.getParent().getHeight() - 1);
       } else if (node.getLeft() == null || node.getRight() == null) { // the node has one child
           if (node == root) {
               if (node.getLeft() == null) {
                   root = node.getRight();
                   node.getRight().setParent(null);
                   node.setRight(null);
               } else {
                   root = node.getLeft();
                   node.getLeft().setParent(null);
                   node.setLeft(null);
               }
           }
           else if (i+1 < getRank(node.getParent())) { // node is the left child of its parent
               if (node.getLeft() == null) {
                   node.getRight().setParent(node.getParent());
                   node.getParent().setLeft(node.getRight());
                   node.setRight(null);
               } else {
                   node.getLeft().setParent(node.getParent());
                   node.getParent().setLeft(node.getLeft());
                   node.setLeft(null);
               }
           } else { // node is the Right child of its parent
               if (node.getLeft() == null) {
                   node.getRight().setParent(node.getParent());
                   node.getParent().setRight(node.getRight());
                   node.setRight(null);
               } else {
                   node.getLeft().setParent(node.getParent());
                   node.getParent().setRight(node.getLeft());
                   node.setLeft(null);
               }
           }
           AVLTree.IAVLNode temp = node;
           node = node.getParent();
           temp.setParent(null);
           if(node != null){
               setNodeHeight(node);
           }
       } else { // the node has two children.
           AVLTree.AVLNode find = (AVLTree.AVLNode) node.getRight();
           AVLTree.AVLNode suc = find;
           AVLTree.IAVLNode temp;
           while (find != null) { // find the successor of node
               suc = find;
               find = (AVLTree.AVLNode) find.getLeft();
           }
           // setting the successor of node instead of node
           if (suc.getParent() != node) {
               suc.getParent().setLeft(suc.getRight());
               if (suc.getRight()!=null) {
                   suc.getRight().setParent(suc.getParent());
               }
               suc.setRight(node.getRight());
               node.getRight().setParent(suc);
               temp = suc.getParent();
           } else {
               temp = suc;
           }
           suc.setParent(node.getParent());
           suc.setLeft(node.getLeft());
           node.getLeft().setParent(suc);
           if (node.getParent() != null){
               if (i+1 < getRank(node.getParent())) {
                   node.getParent().setLeft(suc);
               } else {
                   node.getParent().setRight(suc);
               }
           }
           else {
               root = suc;
           }
           AVLTree.AVLNode newNode = (AVLTree.AVLNode) node;
           suc.setSize(newNode.getSize());
           node.setLeft(null);
           node.setRight(null);
           node.setParent(null);
           setNodeHeight(suc);
           node = temp;
       }
       length--;
       deletionSize(node);
       Rotations(node);
       return 0;
   }

     /**
      * private int Rotations(IAVLNode node)
      * Performs the rotations needed to maintain AVL tree.
      * Time Complexity: O(logn)
      */
     private void Rotations(AVLTree.IAVLNode node) {
         int bf;
         int countRight = 0, countLeft = 0;
         while (node != null) {
             bf = BF(node);
             if (bf == -2) { // check if a rotation is needed and which.
                 if (BF(node.getRight()) == -1 || BF(node.getRight()) == 0) { // BF = 0 can only happen at deletion
                     LeftRotate(node);
                 } else if (BF(node.getRight()) == 1) {
                     RightRotate(node.getRight());
                     LeftRotate(node);
                 }
             } else if (bf == 2) {
                 if (BF(node.getLeft()) == 1 || BF(node.getLeft()) == 0) {
                     RightRotate(node);
                 } else if (BF(node.getLeft()) == -1) {
                     LeftRotate(node.getLeft());
                     RightRotate(node);
                 }
             }
             if (node != null) {
                 setNodeHeight(node); // setting the height of each node on the path from the given node to the root.
             }
             node = node.getParent();
         }
     }


    /**
     * private void insertionSize (AVLTree.IAVLNode node)
     * adds 1 to the size of every node on the branch from the given node to the root
     * Time Complexity: O(logn)
     */
     private void insertionSize (AVLTree.IAVLNode node) {
        while (node != null) {
            ((AVLTree.AVLNode) node).setSize(((AVLTree.AVLNode) node).getSize() + 1);
            node = node.getParent();
        }
     }

    /**
     * private void deletionSize (AVLTree.IAVLNode node)
     * reduces 1 from the size of every node on the branch from the given node to the root
     * Time Complexity: O(logn)
     */
     private void deletionSize (AVLTree.IAVLNode node) {
         while (node != null) {
             ((AVLTree.AVLNode) node).setSize(((AVLTree.AVLNode) node).getSize() - 1);
             node = node.getParent();
         }
     }


    /**
     * private void setNodeHeight (AVLTree.IAVLNode node)
     * updates the height of a given node
     * Time Complexity: O(1)
     */
     private void setNodeHeight (AVLTree.IAVLNode node) {
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
     * private void setNodeSize (AVLTree.IAVLNode node)
     * updates the size of a given node
     * Time Complexity: O(1)
     */
     private void setNodeSize (AVLTree.IAVLNode node) {
         AVLTree.AVLNode right = (AVLTree.AVLNode) node.getRight(), left = (AVLTree.AVLNode) node.getLeft();
         if (node.getLeft() != null && node.getRight() != null) { // if node has two children
             ((AVLTree.AVLNode) node).setSize(1 + right.getSize() + left.getSize());
         } else if (node.getLeft() != null && node.getRight() == null) { // if node has a left child
             ((AVLTree.AVLNode) node).setSize(1 + left.getSize());
         } else if (node.getLeft() == null && node.getRight() != null) { // if node has a right child
             ((AVLTree.AVLNode) node).setSize(1 + right.getSize());
         } else { // if node is a leaf
             ((AVLTree.AVLNode) node).setSize(1);
         }
     }

     /**
      * private int BF(IAVLNode node)
      * Calculates the BF of the given node
      * Time Complexity: O(1)
      */
     private int BF(AVLTree.IAVLNode node) {
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
      * private void leftRotate(IAVLNode node)
      * <p>
      * performs right rotation on the "criminals"
      * Time Complexity: O(1)
      */
     private void LeftRotate(AVLTree.IAVLNode node) {
         AVLTree.IAVLNode y = node.getRight();
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
         setNodeSize(node);
         setNodeHeight(node);
         setNodeSize(y);
     }

     /**
      * private void rightRotate(IAVLNode node)
      * <p>
      * performs right rotation on the "criminals"
      * Time Complexity: O(1)
      */
     private void RightRotate(AVLTree.IAVLNode node) {
         AVLTree.IAVLNode y = node.getLeft();
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
         setNodeSize(node);
         setNodeHeight(node);
         setNodeSize(y);
     }

     public AVLTree.AVLNode getRoot(){
         return (AVLTree.AVLNode) root;
     }

 }