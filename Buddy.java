import java.util.ArrayList;
import java.util.HashMap;

public class Buddy {
	private final HashMap<Integer, ArrayList<Integer>> buddy;
	private final int[] blocks;
	private final int block_count;
	
	public static Buddy create(int number_of_blocks) {
		if (number_of_blocks <= 0 || number_of_blocks != roundToPowerOfTwo(number_of_blocks)) {
			throw new IllegalArgumentException(String.format("(Buddy) Size of %d not supported",number_of_blocks));
		}
		return new Buddy(number_of_blocks);
	}

	private Buddy(int blocks) {
		this.buddy = new HashMap<Integer,ArrayList<Integer>>(blocks);
		get(blocks).add(0);
		this.block_count = blocks;
		this.blocks = new int[block_count];
	}
	
	public static final int ALLOC_ERROR = -1;
	public int alloc(int size) {
		if (size > this.block_count) { return ALLOC_ERROR; } 
		size = roundToPowerOfTwo(size);
		if (!isFree(size) && !split(size * 2)) { return ALLOC_ERROR; }
		return pull(size);
	}
	
	private boolean split(int size) {
		if (size > this.block_count) return false;
		if (!isFree(size)) { split(size * 2); }
		int start = get(size).remove(0);
		put(size, start);
		put(size, start+size);
		return true;
	}
	
	private ArrayList<Integer> get(int key) {
		if (!this.buddy.containsKey(key)) {
			this.buddy.put(key, new ArrayList<Integer>());
		}
		return this.buddy.get(key);
	}
	
	private void put(int key, int value) {
		get(key).add(value);
	}
	
	private int pull(int key) {
		return get(key).remove(0);
	}

	private boolean isFree(int size) {
		return this.buddy.containsKey(size) && this.buddy.get(size).size() > 0;
	}
	
	/**
	 * Finde die nächste größere Zahl die 2^n entspricht 
	 * @param size
	 * @return 2^n
	 */
	private static int roundToPowerOfTwo(int number) {
		for (int i = 0; i < number; i++) {
			if (number <= Math.pow(2, i)) {
				return (int) Math.pow(2, i);
			}
		}
		return 0;
	}
	
	public String toString() {
		StringBuilder r = new StringBuilder();
		for (int b : this.blocks) {
			System.out.print(b);
		}
		return r.toString();
	}
}
