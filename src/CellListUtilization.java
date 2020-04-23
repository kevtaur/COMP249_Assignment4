// -----------------------------------------------------
// Assignment 4
// Part 1: ArrayList & File I/O
// Written by: Kevin Ve 40032669
// -----------------------------------------------------

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**CellListUtilization is the main method class that tests each method of CellList using a data read in from Cell_Info.txt
 * @see CellList
 * @see	CellPhone
 * @author KevinVe
 */
public class CellListUtilization {
	public static void main(String[] args) {
		CellList list1 = new CellList();
		CellList list2 = new CellList();
		
		String path = "files\\";
		String fileName = path+"Cell_Info.txt";
		
		System.out.println("Welcome to CellListUtilization!\n");
		
		//initialize input file stream
		Scanner fileIn = null;
		try {
			fileIn = new Scanner(new FileInputStream(fileName));
		} catch (FileNotFoundException e) {
			System.out.println("");
			e.printStackTrace();
			System.exit(0);
		}
		
		list1.deleteFromStart(); //test
		
		Long serialNum;
		String brand;
		int year;
		double price;
		while(fileIn.hasNext()) {
			serialNum = fileIn.nextLong();
			brand = fileIn.next();
			price = fileIn.nextDouble();
			year = fileIn.nextInt();
			list1.addToStart(new CellPhone(serialNum, brand, year, price));
			list2.insertAtIndex(new CellPhone(serialNum, brand, year, price), 0);
		}
		
		/* Testing Block
		 * find() and contains() methods are private and used extensively inside the class
		 * */
		System.out.println("\nTesting Phase");
		if (list1.equals(list2))
			System.out.println("List1 and List2 are the same");
		else 
			System.out.println("List1 and List2 are not the same\n");
		
		list1.insertAtIndex(new CellPhone(7777777l, "poop", 1000, 100.0), 0);
		list1.deleteFromStart();
		
		list2.insertAtIndex(new CellPhone(7777777l, "poop", 1000, 100.0), list2.getSize());
		list2.replaceAtIndex(new CellPhone(1111111l, "POOP", 9999, 999.99), 0);
		//list2.insertAtIndex(new CellPhone(2222222l, "poop", 1000, 100.0), -1);
		list2.deleteFromIndex(2);
		
		System.out.println("\nAfter modifications:");
		if (list1.equals(list2))
			System.out.println("List1 and List2 are the same");
		else 
			System.out.println("List1 and List2 are not the same");
		/**/
		
		//display contents
		System.out.println("\nList1 (size: "+list1.getSize()+")\n"+list1.showContents());
		System.out.println("\nList2 (size: "+list2.getSize()+")\n"+list2.showContents());
		
		//find serial 
		Scanner keyIn = new Scanner(System.in);
		String input;
		long serial = 0l;
		do {
			System.out.print("Please enter a serial number to find in list2 (0 to exit): ");
			input = keyIn.next();
			if (!input.equals("0")) {
				serial = Long.parseLong(input);
				if (list2.contains(serial))
					System.out.println(list2.find(serial).getData().toString() + "\n");
				else 
					System.out.println("List 2 does not contain " + serial + "\n");
			}
		} while (!input.equals("0"));
		
		fileIn.close();
		keyIn.close();
		System.out.println("Thank you for using CellListUtilization!");
	}
}
