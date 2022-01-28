// Name:		Parker Smith
// Class:		CS 4306/1
// Term:		Spring 2022
// Instructor:	Dr. Haddad
// Assignment:	1

/* -----Algorithm Design Block-----
 * 
 * Problem 3: Design an algorithm to determine whether two strings are the same.
 * 
 * First, the algorithm would check to see whether the lengths of the two strings
 * are the same. If not, the two strings cannot be the same. If so, a loop could
 * iterate through each character of the first string and compare it to the same
 * character index in the second string. If all the characters are the same, the
 * strings are equal.
 * 
 * Pseudocode:
 * //s1 is string 1
 * //s2 is string 2
 * 
 * if s1.length != s2.length then
 *   return false
 * for i <- 0 to s1.length do
 *   if s1[i] != s2[i] then
 *     return false
 * return true
 */
import java.util.Scanner;

public class StringMatching {
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
				System.out.println("Output: " + isStringMatching(string1, string2));
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
	
	static String isStringMatching(String string1, String string2) {
		comparisons = 0; //Resets number of comparisons to zero.
		
		comparisons++; //Increments the number of comparisons by one due to checking whether the two strings are of equal length.
		//If the strings are not of equal length, they cannot match.
		if(string1.length() != string2.length())
			return "Strings do not match";
		//For each character in both strings, check to see whether they match. If they don't, the strings don't match and the loop can be terminated.
		for(int i = 0; i < string1.length(); i++) {
			comparisons++; //Increment comparison counter before each comparison
			if(string1.charAt(i) != string2.charAt(i))
				return "Strings do not match";
		}
		return "Strings match"; //Only reaches here if the strings are of the same length and all characters match.
	}
}