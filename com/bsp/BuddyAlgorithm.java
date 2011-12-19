package com.bsp;


public class BuddyAlgorithm {
	
	private int[] memory;
	private int objectCounter ;

	private BuddyAlgorithm(Integer blocksize, Integer memorysize) {
		this.objectCounter = 0;
		this.memory = new int[memorysize];
		// initialize the empty memory with -1
		for (int i = 0; i < memory.length; i++) {
			memory[i] = -1;
		}
	}

	public static BuddyAlgorithm create(Integer blocksize, Integer memorysize) {
		if (blocksize > memorysize || memorysize == 0 || blocksize == 0) {
			throw new IllegalArgumentException();
		}
		return new BuddyAlgorithm(blocksize, memorysize);
	}
	
	public int allocate(int neededMemorySpace) {
		int result = -1;
		
		int neededBlockSize = closestPowerOfTwo(neededMemorySpace);
		
		int i;
		boolean alreadyAllocated = true;
		for (i = 0; i < memory.length && i+neededBlockSize < memory.length && alreadyAllocated; i++) {
			alreadyAllocated = true;
			// if the the beginning and end of the memoryspace we want to allocate is avaible
			if (memory[i] == -1 && memory[i+neededBlockSize] == -1) {
				alreadyAllocated = false;
				// check if space inbetween memory[i] and memory[i+neededBlockSize] is available
				for (int j = 0; j < neededBlockSize; j++) {
					if (memory[i+j] != -1) {
						alreadyAllocated = true;
					}
				}
			} else {
				// we can skip forward for the entire neededBlockSize because the next possible
				// space to allocate is after this
				// TODO check bounds
				i += neededBlockSize-1;
			}
		}
		
		if (!alreadyAllocated) {
			for (int j = i-neededBlockSize; j <= i ; j++) {
				memory[j] = objectCounter;
			}
			objectCounter++;
			result = objectCounter;
		}
		
		return result;
	}
	
	public boolean free(int address) {
		boolean result = false;
		
		int valueAtAddress = memory[address];
		// you cannot free memory in the middle of a block
		for (int i = 0; i < memory.length; i++) {
			if (i < address && valueAtAddress == memory[i]) {
				return false;
			}
		}
		
		//reset block to empty (-1)
		for (int i = 0; i < memory.length; i++) {
			if(memory[i] == valueAtAddress) {
				memory[i] = -1;
			}
			result = true;
		}
		
		return result;
	}
	
	// http://en.wikipedia.org/wiki/Power_of_two#Algorithm_to_round_up_to_power_of_two
	private int closestPowerOfTwo(int input) {
		if (input == 0)
			return 0;
		int result = 1;
		while(result <= input) {
			result += result;
		}
		return result;
	}
	
	
}
