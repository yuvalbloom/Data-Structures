import java.util.Random;

public class DoubleHashTable extends OAHashTable {
	
	private long p;
	private ModHash DoubleFunc;
	private ModHash DoubleFunc2;

	/**
	 * Constructor for class DoubleHashTable
	 * Complexity: O(1)
	 * @param m - hash table size.
	 * @param p - random prime number.
	 */
	public DoubleHashTable(int m, long p) {
		super(m);
		this.p = p;
		DoubleFunc = new ModHash(m,p);
		DoubleFunc2 = new ModHash(m, p);
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
		return (int) Math.floorMod((DoubleFunc.Hash(x) + i * DoubleFunc2.Hash(x)), m);
	}
	
}
