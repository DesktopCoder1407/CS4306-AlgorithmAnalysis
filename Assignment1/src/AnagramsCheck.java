// Name:		Parker Smith
// Class:		CS 4306/1
// Term:		Spring 2022
// Instructor:	Dr. Haddad
// Assignment:	1

/* -----Algorithm Design Block-----
 * 
 * Problem 4: Design an algorithm to determine whether two strings are anagrams.
 * 
 * First, the algorithm must remove all spaces from both input strings. Then, 
 * the algorithm would compare whether the two modified input strings are the 
 * same length. If they aren't, the strings cannot be anagrams. Otherwise, create
 * a loop that iterates through each character in the first string. Within the 
 * outer loop is an inner loop that iterates through each character in the second 
 * string. If the outer loop's character equals the inner loop's character, remove
 * the character from the second string and continue to the next iteration in the
 * outer loop. If the inner loop cannot find a character that matches the outer
 * loop's character, the two strings are not anagrams. If the outer loop completes,
 * all characters match, therefore the two strings are anagrams.
 * 
 * Pseudocode:
 * //s1 is string 1
 * //s2 is string 2
 * 
 * s1 <- s1.removeSpaces
 * s2 <- s2.removeSpaces
 * if s1.length != s2.length then
 *   return false
 * for i <- 0 to s1.length - 1 do
 *   for j <- 0 to s2.length - 1 do
 *     if s1[i] == s2[j]
 *       s2 <- s2.removeFirstCharacter(j)
 *       break
 *   //Strings are not anagrams if the program reaches this point
 *   return false
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
		return "Strings are anagrams";
	}
}
