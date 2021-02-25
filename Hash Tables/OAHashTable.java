
public abstract class OAHashTable implements IHashTable {
	
	private final HashTableElement DELETED = new HashTableElement(0,0);
	protected HashTableElement [] table; 
	protected int m;

	/**
	 * OAHashTable (int m)
	 * constructor for class OAHashTable.
	 * Complexity: O(n)
	 * @param m - the length of the hash table
	 */
	public OAHashTable(int m) {
		this.table = new HashTableElement[m];
		this.m = m;
	}
	

	/**
	 * HashTableElement Find(long key)
	 * Finds the value of the given key.
	 * Complexity: O(1) average
	 * @return HashTableElement with key and value, or null if the key doesn't exist.
	 */
	@Override
	public HashTableElement Find(long key) {
		for (int i = 0; i < table.length; i++) {
			int j = Hash(key,i);
			if (table[j] == null) {
				return null;
			} else if (table[j].GetKey() == key) {
				return table[j];
			}
		}
		return null;
	}

	/**
	 * Insert(HashTableElement hte)
	 * Inserts given value to the hash table.
	 * Complexity: O(1) average
	 * @param hte - the table entry to add
	 * @throws TableIsFullException if there was no place to enter the given element.
	 * @throws KeyAlreadyExistsException if the key was already in the table.
	 */
	@Override
	public void Insert(HashTableElement hte) throws TableIsFullException,KeyAlreadyExistsException {
		int j = 0;
		for (int i = 0; i < table.length; i++) {
			j = Hash(hte.GetKey(), i);
			if (table[j] == null) {
				table[j] = hte;
				break;
			}
			else if (table[j] == DELETED) {
				if (Find(hte.GetKey()) != null) {
					throw new KeyAlreadyExistsException(hte);
				}
				else {
					table[j] = hte;
					break;
				}
			}
			else {
				if (table[j].GetKey() == hte.GetKey()) {
					throw new KeyAlreadyExistsException(hte);
				}
			}
		}
		if (table[j] != hte)
			throw new TableIsFullException(hte);
	}

	/**
	 * Delete(long key)
	 * given a key, deletes it from the table.
	 * Complexity: O(1) average
	 * @param key - the key to delete from the table.
	 * @throws KeyDoesntExistException if the key doesn't exist in the hash table.
	 */
	@Override
	public void Delete(long key) throws KeyDoesntExistException {
		int j = 0;
		for (int i = 0; i < table.length; i++) {
			j = Hash(key,i);
			if (table[j] != null) {
				if (table[j].GetKey() == key) {
					table[j] = DELETED;
					break;
				}
			}
		}
		if (table[j] != DELETED) {
			throw new KeyDoesntExistException(key);
		}
	}
	
	/**
	 * abstract hash method.
	 * @param x - the key to hash
	 * @param i - the index in the probing sequence
	 * @return the index into the hash table to place the key x
	 */
	public abstract int Hash(long x, int i);
}
