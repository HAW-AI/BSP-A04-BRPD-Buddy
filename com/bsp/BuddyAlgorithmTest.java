package com.bsp;

public class BuddyAlgorithmTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BuddyAlgorithm buddy = BuddyAlgorithm.create(128);
		System.out.println("Allocate 23");
		buddy.allocate(23);
		System.out.println(buddy.toString());
		System.out.println("Allocate 1");
		buddy.allocate(1);
		System.out.println(buddy.toString());
		System.out.println("Allocate 4");
		buddy.allocate(4);
		System.out.println(buddy.toString());
		System.out.println("Allocate 13");
		buddy.allocate(13);
		System.out.println(buddy.toString());
		System.out.println("Allocate 64");
		buddy.allocate(64);
		System.out.println(buddy.toString());
		System.out.println("Allocate 1");
		buddy.allocate(1);
		System.out.println(buddy.toString());
		
		System.out.println("Reset");
		
		buddy = BuddyAlgorithm.create(64);
		System.out.println("Allocate 32");
		buddy.allocate(32);
		System.out.println(buddy.toString());
		System.out.println("Allocate 16");
		buddy.allocate(16);
		System.out.println(buddy.toString());
		
		System.out.println("Reset");
		
		buddy = BuddyAlgorithm.create(128);
		System.out.println("Allocate 23");
		int address = buddy.allocate(23);
		System.out.println(buddy.toString());
		System.out.println("Free the same Block");
		buddy.free(address);
		System.out.println(buddy.toString());
		
		System.out.println("Reset");
		
		buddy = BuddyAlgorithm.create(64);
		System.out.println("Allocate 1");
		buddy.allocate(1);
		System.out.println(buddy.toString());
		System.out.println("Allocate 32");
		buddy.allocate(32);
		System.out.println(buddy.toString());
	}

}
