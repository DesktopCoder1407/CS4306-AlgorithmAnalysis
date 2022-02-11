//Name:		Parker Smith
//Class:		CS 4306/1
//Term:		Spring 2022
//Instructor:	Dr. Haddad
//Assignment:	2

/* -----Algorithm Design Block-----
* 
* Problem 3: Design an algorithm to 
* 
* PSEUDOCODE:
* 
* 
* PERFORMANCE ANALYSIS:
* 
*/
package Assignment2;
import java.util.Scanner;

public class Partition {
	static int comparisons = 0;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int input;
		int size = 0;
		int[] integerValues = new int[size];
		
		while(true) {
			//Main Menu Prompt
			System.out.println("\n-----------------MAIN MENU--------------");
			System.out.println("1. Read set size (number of integers)");
			System.out.println("2. Read integer values");
			System.out.println("3. Run algorithm and display output(substrings and number of comparisons)");
			System.out.println("4. Exit program");
			System.out.print("\nEnter option number: ");
			
			//Input error detection
			try {
				input = scan.nextInt(); //Stores input
			} catch(Exception e) {System.out.println("Please enter an integer number."); continue;}
			finally {scan.nextLine();}
			
			//Results from Input
			if (input == 1) { //Reads the set size
				System.out.print("\nPlease enter the number of integers in the set: ");
				try {size = scan.nextInt();}
				catch(Exception e) {System.out.println("Please enter an integer value.");}
			}
			else if (input == 2) { //Reads integer values
				integerValues = new int[size];
				for (int i = 0; i < size; i++) {
					System.out.printf("\nPlease enter an integer value in the set (%d of %d):", i + 1, size);
					try {integerValues[i] = scan.nextInt();}
					catch(Exception e) {System.out.println("Please enter only integer values. The set of integer values has been cleared."); integerValues = new int[0]; break;}
				}
			}
			else if (input == 3) { //Runs algorithm and displays output
				System.out.println("\nSet size:\t\t\t" + size);
				System.out.print("Integer values:\t\t\t");
				for (int i : integerValues)
					System.out.print(i + " ");
				System.out.println();
				String[] subsets = getSubsetsWithSameSum(integerValues);
				System.out.println("Disjoint subsets with same sum:\t" + subsets[0]);
				System.out.println("\t\t\t\t" + subsets[1]);
			}
			else if (input == 4) { //Exits the program
				break;
			}
			else
				System.out.println("Please enter an integer number from 1 to 4.");
		}
		scan.close();
	}
	
	static String[] getSubsetsWithSameSum(int[] integerValues) {
		return new String[] {"{1, 2}", "{3}"};
	}
}