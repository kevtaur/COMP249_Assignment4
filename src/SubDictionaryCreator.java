// -----------------------------------------------------
// Assignment 4
// Part 1: ArrayList & File I/O
// Written by: Kevin Ve 40032669
// -----------------------------------------------------

import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**SubDictionaryCreator contains the main method class that writes a dictionary from a given text file.
 * Each word is unique, all-caps, categorized by first letter and in alphabetical order
 * Special, numeric and whitespace characters are removed with the exception of the character '²'
 * The dictionary writes to a file called SubDictionary1 so as to not overwrite SubDictionary
 * @author KevinVe
 */
public class SubDictionaryCreator {
	public static void main(String[] args) {
		ArrayList<String> dictionary = new ArrayList<String>(); //initialize variables
		String path = "files\\";
		String fileInputName = path+"PersonOfTheCenturyUTF8.txt";	//put default input text document name here
		String fileOutputName = path+"SubDictionary1.txt";
		
		Scanner fileScanner = null; //keep compiler happy
		PrintWriter fileOutput = null;
		
		//get file input name, 0 for default
		//comment this section out for testing
		System.out.println("Welcome to Sub-Dictionary Creator!\n");
		System.out.print("Please enter the file name(0 for PersonOfTheCentury): ");
		Scanner keyIn = new Scanner(System.in);
		String input = keyIn.next();
		if (!input.equals("0"))
			fileInputName = path+input;
		keyIn.close();
		
		//open input file and create output file. if input file is not found then close and exit program
		try {
			fileScanner = new Scanner(new FileInputStream(fileInputName));
			fileOutput = new PrintWriter(new FileOutputStream(fileOutputName));
		} catch (FileNotFoundException e) {
			System.out.println(fileInputName + " was not found. Program exiting.");
			e.printStackTrace();
			fileScanner.close();
			System.exit(0);
		}
		
		System.out.println("\nProcessing " + fileInputName);
		String nextWord;
		while (fileScanner.hasNext()) {
			nextWord = fileScanner.next().replaceAll("[?:,=;!.\\s]", "").toUpperCase();	//get nextWord and apply any filters necessary
			
			if (nextWord.contains("’"))										//if word contains an apostrophe
				nextWord = nextWord.substring(0, nextWord.indexOf("’"));	//remove apostrophe and any following characters
		
			if (nextWord.matches(".*\\d.*") || nextWord.length() <= 1)		//if word contains any numeric character or is of size 0 or 1
				if(!(nextWord.contains("A") || nextWord.contains("I"))) 	//except unless word is "A" or "I" 
						continue;											//then skip this iteration
			
			if (!dictionary.contains(nextWord) || nextWord.matches("[0-9]+")) //if dictionary already contains words then skip
				dictionary.add(nextWord);									  //otherwise add word
		}
		
		dictionary.sort(String.CASE_INSENSITIVE_ORDER);	//sort word in alphabetical order
		fileOutput.println("The document produced this sub-dictionary, which includes "+dictionary.size()+" entries."); //print dictionary
		printDictionary(dictionary, fileOutput);
		fileOutput.close();
		
		System.out.println("\n" + fileOutputName + " has been produced. Thank you for using Sub-Dictionary Creator!");
	}
	
	/**Prints dictionary to given output file in specified format
	 * @param dictionary ArrayList of words to be printed
	 * @param pw output file stream
	 */
	public static void printDictionary(ArrayList<String> dictionary, PrintWriter pw) {
		char first = 'A'-1;
		for (int i = 0; i < dictionary.size(); i++) {
			if (!dictionary.get(i).startsWith(Character.toString(first))) {
				first = dictionary.get(i).charAt(0);
				pw.println("\n"+first+" \n==");
			}
			pw.println(dictionary.get(i));
		}
	}
}