package com.bsp;


public class BuddyAlgorithm {
	
	private int[] memory;
	private int objectCounter ;

	private BuddyAlgorithm(Integer memorysize) {
		this.objectCounter = 0;
		this.memory = new int[memorysize];
		// initialize the empty memory with -1
		for (int i = 0; i < memory.length; i++) {
			memory[i] = -1;
		}
	}

	public static BuddyAlgorithm create(Integer memorysize) {
		if (memorysize == 0) {
			throw new IllegalArgumentException();
		}
		return new BuddyAlgorithm(memorysize);
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		int allocatedCounter = 0;
		for (int i : memory) {
			if (i != -1) {
				result.append(i);
				allocatedCounter++;
			}
			else {
				result.append("#");
			}
		}
		
		result.append(" Allocated Bytes: ");
		result.append(allocatedCounter);
		result.append(" Of: ");
		result.append(memory.length);
		result.append(" Available Bytes.\n");
		
		return result.toString();
	}
	/**
	 * @param neededMemorySpace
	 * @return the starting address of the allocated block
	 */
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
		
		//get to the correct index
		i--;
		
		if (!alreadyAllocated) {
			for (int j = 0; j < neededBlockSize; j++) {
				memory[i+j] = objectCounter;
			}

			objectCounter++;
			result = i;
		}
		
		return result;
	}
	/**
	 * @param address from which the free method should start to try to free the memory block
	 * @return true if the block is allocatable again, else false
	 */
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
