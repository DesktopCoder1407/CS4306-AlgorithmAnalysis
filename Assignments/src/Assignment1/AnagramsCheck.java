package Assignment1;
// Name:		Parker Smith
// Class:		CS 4306/1
// Term:		Spring 2022
// Instructor:	Dr. Haddad
// Assignment:	1

/* -----Algorithm Design Block-----
 * 
 * Problem 4: Design an algorithm to determine whether two strings are anagrams.
 * 
 * First, the algorithm must change all inputs to a single case and ignore spaces,
 * since anagrams can move captials and spaces freely. Then, it must loop through
 * each character in the first string. Within the outer loop is an inner loop
 * that iterates through each character in the second string.
 * If the outer loop's character equals the inner loop's character, remove the
 * character in the second string and continue to the next iteration in the outer loop.
 * If the inner loop cannot find a character that matches the outer loop's
 * character, the two strings are not anagrams. If the outer loop completes,
 * all characters match, therefore the two strings are anagrams.
 * 
 * Pseudocode:
 * //s1 is string 1 in a single case and with spaces removed
 * //s2 is string 2 in a single case and with spaces removed
 * 
 * for i <- 0 to s1.length - 1 do
 *   for j <- 0 to s2.length - 1 do
 *     if s1[i] == s2[j]
 *       s2[j] <- ""
 *       break
 *     if j == s2.length - 1
 *       return false //Only activates if the if statement is false AND this is the last iteration of the inner loop
 * return true
 */
import java.util.Scanner;

public class AnagramsCheck {
	static int comparisons = 0;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int input;
		String string1 = "";
		String string2 = "";
		
		while(true) {
			//Main Menu Prompt
			System.out.println("\n-----------------MAIN MENU--------------");
			System.out.println("1. Read input string1 and string2");
			System.out.println("2. Run algorithm and display output");
			System.out.println("3. Exit program");
			System.out.print("\nEnter option number: ");
			
			try {
				input = scan.nextInt(); //Stores input
			} catch(Exception e) {System.out.println("Please enter 1, 2, or 3."); continue;}
			finally {scan.nextLine();}
			
			if (input == 3) //Exits the program upon receiving a 3
				break;
			else if (input == 2) { //Runs the algorithm and displays results upon receiving a 2
				System.out.println("\nString 1: " + string1);
				System.out.println("String 2: " + string2);
				System.out.println("Output: " + isAnagram(string1, string2));
				System.out.println("Comparisons: " + comparisons);
			}
			else if (input == 1) { //Reads two input strings upon receiving a 1
				System.out.print("\nPlease enter string1: ");
				string1 = scan.nextLine();
				System.out.print("Please enter string2: ");
				string2 = scan.nextLine();
			}
		}
		scan.close();
	}

	static String isAnagram(String string1, String string2) {
		comparisons = 0; //Resets comparisons
		//Removes all spaces and changes all letters to lowercase. Anagrams do not care about case or spaces.
		string1 = string1.toLowerCase().replaceAll(" ", "");
		string2 = string2.toLowerCase().replaceAll(" ", "");
		
		for(int i = 0; i < string1.length(); i++) { //Outer Loop
			for(int j = 0; j < string2.length(); j++) { //Inner Loop, has a variable loop length as the length of string2 changes if a matching letter is found.
				comparisons++;
				if(string1.charAt(i) == string2.charAt(j)) { //Core comparison
					//If string1 and string2 have a character in common, remove that character from string2 and continue iterating through string1
					string2 = string2.replaceFirst(string2.charAt(j) + "", "");
					break;
				}
				if(j+1 == string2.length()) //If the inner loop has completed a full cycle and no character has been found to be in common, the two strings cannot be anagrams.
					return "Strings are not anagrams";
			}
		}
		return "Strings are anagrams"; //Outer loop complete. Strings are anagrams
	}
}
