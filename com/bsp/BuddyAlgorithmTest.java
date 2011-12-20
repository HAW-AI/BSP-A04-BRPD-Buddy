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
		int address1 = buddy.allocate(23);
		System.out.println(buddy.toString());
		System.out.println("Free the 23 Block");
		buddy.free(address1);
		System.out.println(buddy.toString());
		System.out.println("Allocate 64");
		int address2 = buddy.allocate(64);
		System.out.println(buddy.toString());
		System.out.println("Allocate 12");
		int address3 = buddy.allocate(12);
		System.out.println(buddy.toString());
		System.out.println("Free the 64 Block");
		buddy.free(address2);
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
