import java.util.Random; //

public class QPHashTable extends OAHashTable {
	
	private long p;
	private ModHash QuadFunc;

	/**
	 * Constructor for class QPHashTable
	 * Complexity: O(1)
	 * @param m - hash table size.
	 * @param p - random prime number.
	 */
	public QPHashTable(int m, long p) {
		super(m);
		this.p = p;
		QuadFunc = new ModHash(m,p);
	}

	/**
	 * Hash(long x, int i)
	 * Complexity: O(1)
	 * @param x - the key to hash
	 * @param i - the index in the probing sequence
	 * @return hash value given key x and hash index i.
	 */
	@Override
	public int Hash(long x, int i) {
		return (int) ((QuadFunc.Hash(x) + (i*i)) % m);
	}
}
