
import java.util.Random; //

public class LPHashTable extends OAHashTable {
	private long p;
	private ModHash linearHash;

	/**
	 * Constructor for class LPHashTable
	 * Complexity: O(1)
	 * @param m - hash table size.
	 * @param p - random prime number.
	 */
	public LPHashTable(int m, long p) {
		super(m);
		this.p = p;
		this.linearHash = new ModHash(m,p);
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
		return (int) Math.floorMod((this.linearHash.Hash(x) + i), m);
	}
	
}
