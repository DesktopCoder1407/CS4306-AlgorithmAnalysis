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
//Sample 1: n = 25, m = 26. Unique words starting with different letters.
//  Number of comparisons: 25. This is exactly O(1) since all of the words start with different letters and will always fill an empty linked list. This is the best case scenario.
//
//Sample 2: n = 25, m = 26. Same word starting with the same letter.
//  Number of comparisons: 49. This is O(2), which is still a constant value. The first item will go into an empty linked list, and each following item will then compare itself
//  with the item already in the linked list only once, since they are the same. This is an edge case, but still leads to a good result.
//
//Sample 3: n = 25, m = 26. Unique words starting with the same letter.
//  Number of comparisons: 625. This is O(n) since each entry must compare with every other previously entered item. This is the worst case scenario when n is less than or equal
//  to m.
//
//Sample 4: n = 25, m = 26. Random words.
//  Number of comparisons (in the test I performed): 51. Due to randomness, this can average anywhere from O(1) (every word starting with a different letter) to O(n^2) (unique 
//  words starting with the same letter), however it often rests at around O(2), where some words start with the same letter, but the rest are unique. As is expected, this is 
//  very close to the theoretical efficiency of dynamic hashing. when n is close to m, the average case is close to O(1).
//
//Sample 5: n = [100, 250], m = 26. Large random text.
//  In this sample, the number of words is anywhere from 100 to 250 inclusive. The number of comparisons varies, but it is typically larger than 625, the largest value from
//  the previous tests. This shows that when the number of samples is larger than the size of the hash table, many, many more comparisons are required. Over five tests, the
//  average number of comparisons was 1086. This shows that the average number of comparisons for a number of words larger than the size of the hash table is O(7): still
//  lower than linear time.
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
		HashList[] hashTable = new HashList[26];
		HashList[] Sample = new HashList[26];
		
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
				//Initialize the Linked List within each Hash Table cell
				for (int i = 0; i < hashTable.length; i++)
					hashTable[i] = new HashList();
				
				//Split the string into tokens and run those tokens through the hash function.
				String[] tokens = textInput.split(" ");
				for (int i = 0; i < tokens.length; i++) {
					if ((int)tokens[i].charAt(0) < 97 || (int)tokens[i].charAt(0) > 122)
						continue;
					hashTable[hashFunction(tokens[i])].add(tokens[i]);
				}
			}
			else if (input == 3) { //Displays the text input's words and their count.]
				System.out.println("Key Word\tWord Count");
				System.out.println("---------------------------");
				for(int i = 0; i < hashTable.length; i++)
					System.out.print(hashTable[i]);
			}
			else if (input == 4) { //Displays the results of 5 samples of the hashing table.
				//**Sample 1: 25 unique words starting with different letters.
				for (int i = 0; i < Sample.length; i++) //Initialize Lists
					Sample[i] = new HashList();
				for (int i = 97; i < 97 + 25; i++) //Add unique words w/ different letters
					Sample[hashFunction(Character.toString(i))].add(Character.toString(i));
				//Display
				System.out.println("\nSample 1: 25 unique words starting with different letters.");
				System.out.println("\tInputs Size: \t\t25 words");
				System.out.println("\tNumber of Comparisons: \t" + getAllComparisons(Sample));
				
				//**Sample 2: 25 same word.
				for (int i = 0; i < Sample.length; i++) //Initialize Lists
					Sample[i] = new HashList();
				for (int i = 0; i < 25; i++) //Add same word
					Sample[hashFunction(Character.toString('a'))].add(Character.toString('a'));
				//Display
				System.out.println("\nSample 2: 25 same word.");
				System.out.println("\tInputs Size: \t\t25 words");
				System.out.println("\tNumber of Comparisons: \t" + getAllComparisons(Sample));
				
				//**Sample 3: 25 unique words starting with the same letter.
				for (int i = 0; i < Sample.length; i++) //Initialize Lists
					Sample[i] = new HashList();
				for (int i = 97; i < 97 + 25; i++) //Add unique words starting w/ the same letter
					Sample[hashFunction(Character.toString('a') + Character.toString(i))].add(Character.toString('a') + Character.toString(i));
				//Display
				System.out.println("\nSample 3: 25 unique words starting with the same letter.");
				System.out.println("\tInputs Size: \t\t25 words");
				System.out.println("\tNumber of Comparisons: \t" + getAllComparisons(Sample));
				
				//**Sample 4: 25 random words.
				for (int i = 0; i < Sample.length; i++) //Initialize Lists
					Sample[i] = new HashList();
				for (int i = 0; i < 25; i++) { //Add random word
					String word = generateRandomWord();
					Sample[hashFunction(word)].add(word);
				}
				//Display
				System.out.println("\nSample 4: 25 random words.");
				System.out.println("\tInputs Size: \t\t25 words");
				System.out.println("\tNumber of Comparisons: \t" + getAllComparisons(Sample));
				
				//**Sample 5: Large random text.
				for (int i = 0; i < Sample.length; i++) //Initialize Lists
					Sample[i] = new HashList();
				int numWords = new Random().nextInt(100, 251);
				for (int i = 0; i < numWords; i++) { //Add random word
					String word = generateRandomWord();
					Sample[hashFunction(word)].add(word);
				}
				//Display
				System.out.println("\nSample 5: Large random text.");
				System.out.println("\tInputs Size: \t\t" + numWords + " words");
				System.out.println("\tNumber of Comparisons: \t" + getAllComparisons(Sample));
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
