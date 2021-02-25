import java.util.Random;

public class AQPHashTable extends OAHashTable {

	private ModHash alterFunc;
	private long p;

	/**
	 * Constructor for class AQPHashTable
	 * Complexity: O(1)
	 * @param m - hash table size.
	 * @param p - random prime number.
	 */
	public AQPHashTable(int m, long p) {
		super(m);
		this.p = p;
		alterFunc = new ModHash(m,p);
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
		return (int) Math.floorMod((long) (alterFunc.Hash(x) + Math.pow(-1, i) * (i*i)), m);
	}
}
