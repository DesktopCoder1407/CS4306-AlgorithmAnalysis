//Name: 		Parker Smith
//Class: 		CS 4306/1
//Term: 		Spring 2022
//Instructor: 	Dr. Haddad
//Assignment: 	6
//
//ALGORITHM DESIGN BLOCK: Emperical Results for 5 Sample Inputs vs Theoretical Efficiency
//
//Theoretical Efficiency of Dynamic Hashing: O(1) in the average case if the number of keys n is about equal to the hash table's size m
//
//Sample 1: n = 26, m = 26. Unique words starting with different letters.
//  Number of comparisons: 26. This is exactly O(1) since all of the words start with different letters and will always fill an empty linked list. This is the best case scenario.
//
//Sample 2: n = 26, m = 26. Same word starting with the same letter.
//  Number of comparisons: 76. This is O(2), which is still a constant value. The first item will go into an empty linked list, and each following item will then compare itself
//  with the item already in the linked list only once, since they are the same. This is an edge case, but still leads to a good result.
//
//Sample 3: n = 26, m = 26. Unique words starting with the same letter.
//  Number of comparisons: 676. This is O(n) since each entry must compare with every other previously entered item. This is the worst case scenario when n is less than or equal
//  to m.
//
//Sample 4: n = 26, m = 26. Random words.
//  Number of comparisons (in the test I performed): 50. Due to randomness, this can average anywhere from O(1) (every word starting with a different letter) to O(n) (unique 
//  words starting with the same letter), however it often rests at around O(2), where some words start with the same letter, but the rest are unique. As is expected, this is 
//  very close to the theoretical efficiency of dynamic hashing. when n is close to m, the average case is close to O(1).
//
//Sample 5: n > 26, m = 26. Large random text.
//  In this sample, the number of words is random, but larger than the size of the hash table. The number of comparisons varies, but it is typically within the range of O(n) and O(2).
//  The average number of comparisons for me was 156. This is around O(3), still lower than linear time.
//
//Ultimately, the average case for hashing all these numbers was very close to O(1). At maximum, the case was linear (As if searching through a linked list or an array). 
//  Overall, I feel the Theoretical Efficiency is accurate for Dynamic Hashing.
//
package Assignment6;

import java.util.Scanner;
import java.util.Random;

public class TextHashing {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int input;
		
		String textInput = "";
		HashSet hashedInput = new HashSet();
		HashSet Sample1;
		HashSet Sample2;
		HashSet Sample3;
		HashSet Sample4;
		HashSet Sample5;
		
		while(true) {
			//Main Menu Prompt
			System.out.println("\n---------------MAIN MENU---------------");
			System.out.println("1. Read input text");
			System.out.println("2. Hash Input Text to Hash Table");
			System.out.println("3. Display Words and Occurrences");
			System.out.println("4. Display Efficiency Outputs");
			System.out.println("5. Exit Program");
			System.out.print("\nEnter option number: ");
			
			//Input error detection
			try {
				input = scan.nextInt(); //Stores input
			} catch(Exception e) {System.out.println("Please enter an integer number."); continue;}
			finally {scan.nextLine();}
			
			//Results from Input
			if (input == 1) { //Read the Input Text from the user
				System.out.print("\nPlease enter the input text: ");
				textInput = scan.nextLine().toLowerCase();
			}
			else if (input == 2) { //Runs the text through the hashing function and into the hash table				
				hashedInput = new HashSet();
				
				String[] tokens = textInput.split(" ");
				for (int i = 0; i < tokens.length; i++)
					hashedInput.add(tokens[i]);
			}
			else if (input == 3) { //Displays the text input's words and their count.]
				System.out.println("Key Word\tWord Count");
				System.out.println("---------------------------");
				System.out.print(hashedInput);
			}
			else if (input == 4) { //Displays the results of 5 samples of the hashing table.
				String valuesInput = "";
				String[] tokens;
				
				Sample1 = new HashSet();
				System.out.println("\nSample test 1:");
				System.out.print("Input Values (26 unique words starting with different letters): ");
				
				valuesInput = scan.nextLine();
				tokens = valuesInput.split(" ");
				for (int i = 0; i < tokens.length; i++)
					Sample1.add(tokens[i]);
				
				System.out.println("Inputs size: 26 words");
				System.out.println("Number of comparisons: " + Sample1.comparisons);
				
				
				Sample2 = new HashSet();
				System.out.println("\nSample test 2:");
				System.out.print("Input Value (single word, will be repeated 26 times): ");
				
				valuesInput = scan.nextLine();
				tokens = valuesInput.split(" ");
				for (int i = 0; i < 26; i++)
					Sample2.add(tokens[0]);
				
				System.out.println("Inputs size: 26 words");
				System.out.println("Number of comparisons: " + Sample2.comparisons);
				
				
				Sample3 = new HashSet();
				System.out.println("\nSample test 3:");
				System.out.print("Input Values (26 unique words starting with the same letter): ");
				
				valuesInput = scan.nextLine();
				tokens = valuesInput.split(" ");
				for (int i = 0; i < tokens.length; i++)
					Sample3.add(tokens[i]);
				
				System.out.println("Inputs size: 26 words");
				System.out.println("Number of comparisons: " + Sample3.comparisons);
				
				
				Sample4 = new HashSet();
				System.out.println("\nSample test 4:");
				System.out.print("Input Values (26 random words): ");
				
				valuesInput = scan.nextLine();
				tokens = valuesInput.split(" ");
				for (int i = 0; i < tokens.length; i++)
					Sample4.add(tokens[i]);
				
				System.out.println("Inputs size: 26 words");
				System.out.println("Number of comparisons: " + Sample4.comparisons);
				
				
				Sample5 = new HashSet();
				System.out.println("\nSample test 5:");
				System.out.print("Input Values (large random text): ");
				
				valuesInput = scan.nextLine();
				tokens = valuesInput.split(" ");
				for (int i = 0; i < tokens.length; i++)
					Sample5.add(tokens[i]);
				
				System.out.println("Inputs size: " + tokens.length + " words");
				System.out.println("Number of comparisons: " + Sample5.comparisons);
				
			}
			else if (input == 5) { //Exits the program
				break;
			}
			else
				System.out.println("Please enter an integer number from 1 to 5.");
		}
		scan.close();
	}
	
	private static int hashFunction(String s) {
		return (int)(s.charAt(0)) - 97; //Returns the ASCII character of the string's first character and normalizes the returned value to a number between 0 and 25 inclusive.
	}
	
	private static int getAllComparisons(HashList[] s) {
		int totalComparisons = 0;
		for(HashList l : s)
			totalComparisons += l.comparisons;
		return totalComparisons;
	}
	
	private static String generateRandomWord() {
		Random rand = new Random();
		int lengthOfString = rand.nextInt(1,11);
		String outputString = "";
		
		for(int i = 0; i < lengthOfString; i++)
			outputString += (char)rand.nextInt(97, 123);
		
		return outputString;
	}
}
