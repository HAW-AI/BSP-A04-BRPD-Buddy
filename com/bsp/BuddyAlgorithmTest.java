package com.bsp;

public class BuddyAlgorithmTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BuddyAlgorithm buddy = BuddyAlgorithm.create(128);
		buddy.allocate(23);
		System.out.println(buddy.toString());
		buddy.allocate(1);
		System.out.println(buddy.toString());
		buddy.allocate(4);
		System.out.println(buddy.toString());
		buddy.allocate(13);
		System.out.println(buddy.toString());
		buddy.allocate(64);
		System.out.println(buddy.toString());
		buddy.allocate(1);
		System.out.println(buddy.toString());
	}

}
