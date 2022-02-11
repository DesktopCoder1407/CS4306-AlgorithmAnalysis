//Name:		Parker Smith
//Class:		CS 4306/1
//Term:		Spring 2022
//Instructor:	Dr. Haddad
//Assignment:	2

/* -----Algorithm Design Block-----
* 
* Problem 2: Design an algorithm to identify the number of substrings that start with A and end with B in a given text.
* 
* PSEUDOCODE:
* //s is the given input string
* //sLen is the length of the input string 
* 
* n <- 0 //number of substrings
* c <- 0 //number of comparisons
* for i <- 0 to sLen - 2 do
*   c <- c + 1 //Increment Comparison
*   if s[i] = 'A'
*     for j <- i + 1 to sLen - 1
*       c <- c + 1 //Increment Comparison
*       if s[j] = 'B'
*         n <- n + 1 //A substring has been found
* 
* PERFORMANCE ANALYSIS:
* i			|Assign	|Comp	|Min	|Max ->	|j					|Assign		|Comp		|Min	|Max
* 0			|1		|1		|0		|		|1 to sLen-1		|n-1		|n-1		|0		|n-1
* 1			|1		|1		|0		|		|2 to sLen-1		|n-2		|n-2		|0		|n-2
* 2			|1		|1		|0		|		|3 to sLen-1		|n-3		|n-3		|0		|n-3
* sLen-3	|1		|1		|0		|		|sLen-2 to sLen-1	|2			|2			|0		|2
* sLen-2	|1		|1		|0		|		|sLen-1	to sLen-1	|1			|1			|0		|1
* ----------|-------|-------|-------|-------|-------------------|-----------|-----------|-------|---------
* 			|n-1	|n-1	|0		|		|					|(n^2-n)/2	|(n^2-n)/2	|0		|(n^2-n)/2

* Best Case: n-1 + n-1 + 0 = 2n-2
* Worst Case: n-1 + n-1 + (n^2-n)/2 + (n^2-n)/2 + (n^2-n)/2 = 2n-2 + 3(n^2-n)/2
* Average Case: (4n-4 + 3(n^2-n)/2)/2 = 2n-2 + 3(n^2-n)/4
* Big O Notation: O(n^2)
*/
package Assignment2;
import java.util.Scanner;

public class Substrings {
	static int comparisons = 0;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int input;
		String inputString = "";
		
		while(true) {
			//Main Menu Prompt
			System.out.println("\n-----------------MAIN MENU--------------");
			System.out.println("1. Read input string");
			System.out.println("2. Run algorithm and display output(substrings and number of comparisons)");
			System.out.println("3. Exit program");
			System.out.print("\nEnter option number: ");
			
			//Input error detection
			try {
				input = scan.nextInt(); //Stores input
			} catch(Exception e) {System.out.println("Please enter an integer number."); continue;}
			finally {scan.nextLine();}
			
			//Results from Input
			if (input == 1) { //Reads input string
				System.out.print("\nPlease enter the input string: ");
				try {inputString = scan.nextLine();}
				catch(Exception e) {System.out.println("Please enter a string input value.");}
			}
			else if (input == 2) { //Runs the algorithm and displays output
				System.out.println("\nInput string:\t\t" + inputString);
				System.out.println("# of substrings:\t" + getNumSubstrings(inputString));
				System.out.println("# of comparisons:\t" + comparisons);
			}
			else if (input == 3) { //Exits the program
				break;
			}
			else
				System.out.println("Please enter an integer number from 1 to 3.");
		}
		scan.close();
	}
	
	static int getNumSubstrings(String input) {
		input = input.toUpperCase();
		int inputLength = input.length();
		int numSubstrings = 0;
		comparisons = 0;
		
		for (int i = 0; i < inputLength - 1; i++) {
			comparisons++;
			if (input.charAt(i) == 'A') { //First A found
				for(int j = i + 1; j < inputLength; j++) { //Continue looping through the string from that point
					comparisons++;
					if(input.charAt(j) == 'B') //Until a B is found
						numSubstrings++; //Once an A and a B are found in that order, a substring has been found.
				}
			}
		}
		
		return numSubstrings;
	}
}
