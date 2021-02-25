
public class Item{
	
	private int key; // key value for Item variable of type int
	private String info; // Into for Item variable of type String


	/**
	 * Item()
	 *
	 * Constructor Function for class Item,
	 * sets key and info to given values
	 * Time Complexity: O(1)
	 */
	public Item (int key, String info){
		this.key = key;
		this.info = info;
	}

	/**
	 * getKey()
	 *
	 * returns the key of the Item
	 * Time Complexity: O(1)
	 */
	public int getKey(){
		return key;
	}

	/**
	 * getInfo()
	 *
	 * returns the Info of the Item
	 * Time Complexity: O(1)
	 */
	public String getInfo(){
		return info;
	}

	public static String Print(Item item) {
		return (item != null) ? ( "key: " + item.key + "\nInfo: " + item.info): null;
	}
}