
/**
 *
 * Circular list
 *
 * An implementation of a circular list with  key and info
 * We use the method Math.floorMod for cases where the value is negative
 */
 
 public class CircularList{

    private Item[] maxList; // circular list variables of type Item
    private int start; // start index
    private int length; // list length
    private int maxLength; // max length of the list

    /**
     * CircularList (int maxLen)
     *
     * Constructor Function for class Circular List
     * Time Complexity: O(maxLen)
     */
	public CircularList (int maxLen) {
        maxList = new Item[maxLen];
        start=0;
        length=0;
        maxLength=maxLen;
	}
	/**
   * public Item retrieve(int i)
   *
   * returns the item in the ith position if it exists in the list.
   * otherwise, returns null.
   * Time Complexity: O(1)
   */
     public Item retrieve(int i)
     {
         if (i >= 0 && i < length) { // if i is in the list range
             return maxList[Math.floorMod(start + i, maxLength)];
         }
         else { // if i is out of the list range
             return null;
         }
     }

  /**
   * public int insert(int i, int k, String s) 
   *
   * inserts an item to the ith position in list  with key k and info s.
   * returns -1 if i<0 or i>n or n=maxLen otherwise return 0.
   * Time Complexity: O(min(length-i, i))
   */
    public int insert(int i, int k, String s) {
        if (i<0 || i>length || length == maxLength) { // if i is out of the list range.
            return -1;
        }
        Item toInsert = new Item(k, s);
        if (i < (length - i)) { // if the beginning of the list is shorter, we shift it backwards.
            for (int j = 0; j <= i - 1; j++) {
                maxList[Math.floorMod(start + j - 1, maxLength)] = maxList[Math.floorMod(start + j, maxLength)];
            }
            maxList[Math.floorMod(start + i - 1, maxLength)] = toInsert;
            start = Math.floorMod(start - 1, maxLength);
        } else { // if the end of the list is shorter, we shift it forwards
            for (int j = length - 1; j >= i; j--) {
                maxList[Math.floorMod(start + j + 1, maxLength)] = maxList[Math.floorMod(start + j, maxLength) ];
            }
            maxList[Math.floorMod(start + i, maxLength)] = toInsert;
        }
        length++; //
        return 0;
    }

   /**
   * public int delete(int i)
   *
   * deletes an item in the ith position from the list.
   * returns -1 if i<0 or i>n-1 otherwise returns 0.
   * Time Complexity: O(min(length-i, i))
   */
   public int delete(int i) {
       if (i<0 || i>length-1) {
           return -1;
       }
       else {
           if (i < (length - i)) { // if the beginning of the list is shorter, we shift it forwards.
               for (int j = i; j >= 0; j--) {
                   maxList[Math.floorMod(start + j, maxLength)] = maxList[Math.floorMod(start + j - 1, maxLength)];
               }
               start = Math.floorMod(start + 1, maxLength);
           } else { // if the end of the list is shorter, we shift it backwards.
               for (int j = i; j < length; j++) {
                   maxList[Math.floorMod(start + j, maxLength)] = maxList[Math.floorMod(start + j + 1, maxLength)];
               }
           }
           length--;
           return 0;

       }
   }
 }
 
 
 