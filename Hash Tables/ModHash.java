public class ModHash {

	private int m;
	private long p;
	private long a;
	private long b;

	/**
	 * ModHash(int m, long p)
	 * Constructor for class ModHash.
	 * @param m - Length of the hash table.
	 * @param p - random prime number.
	 */
	public ModHash(int m, long p) {
		this.a = randA(p);
		this.b = randB(p);
		this.p = p;
		this.m = m;
	}

	/**
	 * randA(long p)
	 * Helper function.
	 * Calculates random value for element a.
	 * @param p - random prime number.
	 * @return random value between 1 and p.
	 */
	public long randA(long p) {
		long leftLimitA = 1;
		return leftLimitA + (long) (Math.random() * (p - leftLimitA));
	}

	/**
	 * randB(long p)
	 * Helper function.
	 * Calculates random value for element b.
	 * @param p - random prime number.
	 * @return random value between 0 and p.
	 */
	public long randB(long p) {
		long leftLimitB = 0;
		return leftLimitB + (long) (Math.random() * (p - leftLimitB));
	}

	/**
	 * GetFunc(int m, long p)
	 * @param m - Length of the hash table.
	 * @param p - random prime number.
	 * @return new Hash function.
	 */
	public static ModHash GetFunc(int m, long p){
		return new ModHash(m,p);
	}

	/**
	 * Hash(long key)
	 * @param key - value for calculating hash index.
	 * @return hash index
	 */
	public int Hash(long key) {
		return (int) (Math.floorMod(((a*key) + b), p));
	}
}