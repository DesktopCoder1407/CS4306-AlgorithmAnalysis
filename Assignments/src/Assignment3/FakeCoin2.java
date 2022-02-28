//Name:			Parker Smith
//Class:		CS 4306/1
//Term:			Spring 2022
//Instructor:	Dr. Haddad
//Assignment:	3

/* -----Algorithm Design Block-----
* 
* Problem 2: There are n identical-looking coins with one fake coin that is lighter than the genuine coins.
*   Develop an algorithm that divides the set by factor of 3 and finds the fake coin.
*   
* PART A, PSEUDOCODE:
* FindFake(A[n]):
* //A[n] is an array of n elements containing the weights of the coins
* 
* if n = 2
*   if A[0] > A[1]
*   
* 
* sumFirstThird <- 0
* sumSecondThird <- 0
* for i <- 0 to floor(n/3) - 1 do
*   sumFirstThird <- sumFirstThird + A[i]
* for i <- floor(n/3) to 2 * floor(n/3) - 1 do
*   sumSecondThird <- sumSecondTHird + A[i]
* 
* if sumFirstThird < sumSecondThird
*   FindFake(A[0..floor(n/3) - 1)
* else if sumSecondThird < sumFirstThird
*   FindFake(A[floor(n/3)..2 * floor(n/3) - 1])
* else
*   FindFake(A[2 * floor(n/3)..n - 1])
* 
*/
package Assignment3;
import java.util.Scanner;

public class FakeCoin2 {
	static int divisions = 0;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int input;
		int size = 0;
		int[] coins = new int[size];
		
		while(true) {
			//Main Menu Prompt
			System.out.println("\n-----------------MAIN MENU---------------------------");
			System.out.println("1. Read number of coins (array size)");
			System.out.println("2. Read array values (all values are 5 except one value 2)");
			System.out.println("3. Run algorithm and display results");
			System.out.println("4. Exit program");
			System.out.print("\nEnter option number: ");
			
			//Input error detection
			try {
				input = scan.nextInt(); //Stores input
			} catch(Exception e) {System.out.println("Please enter an integer number."); continue;}
			finally {scan.nextLine();}
			
			//Results from Input
			if (input == 1) { //Reads the array size
				System.out.print("\nPlease enter the number of coins in the array: ");
				try {size = scan.nextInt();}
				catch(Exception e) {System.out.println("Please enter an integer value.");}
			}
			else if (input == 2) { //Reads array values
				coins = new int[size];
				for (int i = 0; i < size; i++) {
					System.out.printf("\nPlease enter an array value [must be a 2 or a 5] (%d of %d):", i + 1, size);
					try {coins[i] = scan.nextInt();}
					catch(Exception e) {System.out.println("Please enter only integer values. The set of integer values has been cleared."); coins = new int[0]; break;}
				}
			}
			else if (input == 3) { //Runs algorithm comparison and displays results
				System.out.println("\nArray size:\t" + size);
				System.out.print("Array values:\t");
				for (int i : coins)
					System.out.print(i + " ");
				//run algorithm
				System.out.println("\n# of divisions:\t" + divisions);
				System.out.println("Fake coin index:\t");
			}
			else if (input == 4) { //Exits the program
				break;
			}
			else
				System.out.println("Please enter an integer number from 1 to 4.");
		}
		scan.close();
	}
}