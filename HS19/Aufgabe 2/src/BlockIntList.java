public class BlockIntList {
	private static final int MAX_BLOCKS = 50;
	private static final int FIRST_BLOCK_SIZE = 2;

	private int[][] blocks;
	private int lastBlock;
	private int elemsInLast;
	private int size;

	public BlockIntList() {
		blocks = new int[MAX_BLOCKS][];
		blocks[0] = new int[FIRST_BLOCK_SIZE];
		lastBlock = elemsInLast = size = 0;
	}

	public void add(int value) {
		ensureSpaceAdd();
		blocks[lastBlock][elemsInLast] = value;
		elemsInLast++;
		size++;
	}

	private void ensureSpaceAdd() {
		if (elemsInLast == blocks[lastBlock].length) {
			// add a new block
			int newSize = 2 * blocks[lastBlock].length;
			lastBlock++;
			blocks[lastBlock] = new int[newSize];
			elemsInLast = 0;
		}
	}

	public int size() {
		return size;
	}

	public String toString() {
		String result = "";
		for (int b = 0; b < lastBlock; b++) {
			for (int i = 0; i < blocks[b].length; i++) {
				result += blocks[b][i] + ", ";
			}
		}
		for (int i = 0; i < elemsInLast; i++) {
			result += blocks[lastBlock][i] + ", ";
		}
		if (!result.isEmpty()) {
			result = result.substring(0, result.length() - 2);
		}
		return "[" + result + "]";
	}
	
	// counter
	public int get(int index) {
		// invalid index
		if (index >= size) {
			throw new IndexOutOfBoundsException();
		}
		
		int newIndex = index;
		int block = 0;
		int blockSize = 2;
		int blockSum = 1;
		
		// while in lower block
		while (index > blockSum) {
			// next block
			++block;
			// index in this block
			newIndex -= blockSize;
			// adjust block size
			blockSize *= 2;
			// whole index
			blockSum += blockSize;
		}

		return blocks[block][newIndex];
	}

	// some getters
	public int[][] getBlocks() {
		return blocks;
	}

	public int getLastBlock() {
		return lastBlock;
	}

	public int getElemsInLast() {
		return elemsInLast;
	}

	public int getSize() {
		return size;
	}
	
	// de esch giga fräch
	public void addFirst(int value) {
		BlockIntList newBlock = new BlockIntList();

		newBlock.add(value);

		for (int i = 0; i < size; ++i) {
			newBlock.add(get(i));
		}

		blocks = newBlock.getBlocks();
		lastBlock = newBlock.getLastBlock();
		elemsInLast = newBlock.getElemsInLast();
		size = newBlock.getSize();

	}
}
