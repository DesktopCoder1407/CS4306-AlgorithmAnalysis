// Name:		Parker Smith
// Class:		CS 4306/1
// Term:		Spring 2022
// Instructor:	Dr. Haddad
// Assignment:	1

/* -----Algorithm Design Block-----
 * 
 * Problem 2: Describe the standard algorithm for finding the binary representation of a positive decimal integer.
 * 
 * The standard algorithm for finding the binary representation of a decimal integer is to have a loop that
 * continues while n is greater than or equal to 0. Inside the loop, the result of n mod 2 is appended to the beginning of the binary
 * representation output. n is then divided by two, ignoring the decimal result. The loop then continues with this
 * new n value.
 * 
 * Pseudocode:
 * //n is the decimal input
 * //b is the binary output
 * 
 * while n >= 0 do
 *   b <- (n mod 2) + b
 *   n <- n / 2
 * return b
 */

import java.util.Scanner;

public class DecimalToBinary {
	static int divisions = 0;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int input;
		int decimal = 0;
		
		while(true) {
			//Main Menu Prompt
			System.out.println("\n-----------------MAIN MENU--------------");
			System.out.println("1. Read input decimal value");
			System.out.println("2. Run algorithm and display output");
			System.out.println("3. Exit program");
			System.out.print("\nEnter option number: ");
			
			input = scan.nextInt(); //Stores input
			
			if (input == 3) //Exits the program upon receiving a 3
				break;
			else if (input == 2) { //Runs the algorithm and displays results upon receiving a 2
				System.out.println("\nDecimal value: " + decimal);
				System.out.println("Output Binary value: " + getDecimalToBinary(decimal));
				System.out.println("Divisions: " + divisions);
			}
			else if (input == 1) { //Reads input decimal value upon receiving a 1
				System.out.print("\nPlease enter an integer: ");
				decimal = scan.nextInt();
			}
		}
		scan.close();
	}
	
	static String getDecimalToBinary(int decimal) {
		String binary = ""; //Output binary string
		divisions = 0; //Resets the division counter to 0
		
		if(decimal == 0)
			return "0"; //Edge case for when the decimal input is 0
		while (decimal > 0) {
			divisions++; //Increment the number of divisions
			binary = (decimal % 2) + binary; //Add to the binary number by figuring out whether the decimal number is divisible by two or not
			decimal /= 2; //Halve the decimal number and continue the loop
		}
		
		return binary;
	}

}
