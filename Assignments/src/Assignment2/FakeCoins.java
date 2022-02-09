//Name:		Parker Smith
//Class:		CS 4306/1
//Term:		Spring 2022
//Instructor:	Dr. Haddad
//Assignment:	2

/* -----Algorithm Design Block-----
* 
* Problem 1: There are n stacks of n identical-looking coins.
* All of the coins in one of these stacks are counterfeit, while all the coins in the other
* stacks are genuine. Every genuine coin weighs 10 grams; every fake weighs 11 grams.
* You have an analytical scale that can determine the exact weight of any number of coins.
* Design an algorithm to identify the stack with the fake coins.
* 
* PSEUDOCODE:
* //n is the number of stacks of identical-looking coins
* //A[n] is an array of size n going from 0 to n-1
* //w is a counter of the number of weighings
* 
* for i <- 0 to n-1 do
*   A[i] <- the weight of the top most coin off of stack i from the n stacks
* w <- 0 
* for i <- 0 to n-1 do
*   w <- w + 1
*   if A[i] = 11 
*     return i //i is the number of the stack with the fake coins (because the weight is 11), so i is returned.
* return -1 //if somehow there were no fake coins in any stacks (11 was never found), return -1.
* 
* PERFORMANCE ANALYSIS:
* ##Special case on the minimum comparison, if true, return is called, ending the entire loop, so Min is not counted anymore.
* first i	|Assign	|second i	|Assign Min	|Assign Max	|Comp Min	|Comp Max
* 0			|1		|0			|1			|1			|1			|1
* 1			|1		|1			|0			|1			|0			|1
* 2			|1		|2			|0			|1			|0			|1
* n-2		|1		|n-2		|0			|1			|0			|1
* n-1		|1		|n-1		|0			|1			|0			|1
* ----------|-------|-----------|-----------|-----------|-----------|-------
* 			|n		|			|1			|n			|1			|n
* Best Case: n + 1 + 1 = n + 2
* Worst Case: n + 1 + n + n + 1 = 3n + 2
* Average Case: (4n + 4) / 2 = 2n + 2
* Big O Notation: O(n)
* 
* MINIMUM NUMBER OF WEIGHINGS & EXPLANATION:
* The minimum number of weighings that could possibly be achieved is 1.
* This is possible if the fake coin is the first coin that is weighed. In that case, there would be a single
* comparison (weighing) to determine if the weight of the coin is 11. The result would be true, returning the stack number
* containing the fake coins.
*/
package Assignment2;
import java.util.Scanner;

public class FakeCoins {
	static int weighings = 0;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int input;
		int numStacks = 0;
		int numCoinsPerStack = 0;
		int[] coinWeightInStack = new int[numStacks];
		
		while(true) {
			//Main Menu Prompt
			System.out.println("\n-----------------MAIN MENU---------------------------");
			System.out.println("1. Read number of coin stacks");
			System.out.println("2. Read number of coins in the stacks (all stacks have same size)");
			System.out.println("3. Read coin weight in each stack (10 or 11 grams)");
			System.out.println("4. Run algorithm and display output");
			System.out.println("5. Exit program");
			System.out.print("\nEnter option number: ");
			
			//Input error detection
			try {
				input = scan.nextInt(); //Stores input
			} catch(Exception e) {System.out.println("Please enter an integer number."); continue;}
			finally {scan.nextLine();}
			
			//Results from Input
			if (input == 1) { //Reads number of coin stacks
				System.out.print("\nPlease enter an integer: ");
				try {numStacks = scan.nextInt();}
				catch(Exception e) {System.out.println("Please enter an integer.");}
			}
			else if (input == 2) { //Reads number of coins in each stack
				System.out.print("\nPlease enter an integer: ");
				try {numCoinsPerStack = scan.nextInt();}
				catch(Exception e) {System.out.println("Please enter an integer.");}
			}
			else if (input == 3) { //Reads coin weight in each stack (10 or 11 grams)
				coinWeightInStack = new int[numStacks];
				for (int i = 0; i < numStacks; i++) {
					System.out.print("\nPlease enter an integer (10 or 11) for the weight of stack " + (i + 1) + ": ");
					try {coinWeightInStack[i] = scan.nextInt();}
					catch(Exception e) {System.out.println("Please only enter an integer. Previous values voided."); coinWeightInStack = new int[numStacks];}
				}
			}
			else if (input == 4) { //Runs the algorithm and displays output
				System.out.println("\nTotal number of stacks is:\t" + numStacks);
				System.out.println("Fake coins stack is stack #:\t" + getFakeCoinStack(coinWeightInStack));
				System.out.println("# of weighings required:\t" + weighings);
			}
			else if (input == 5) { //Exits the program
				break;
			}
			else
				System.out.println("Please enter an integer number from 1 to 5.");
		}
		scan.close();
	}
	
	static int getFakeCoinStack(int[] coinWeightInStack) {
		weighings = 0;
		for (int i = 0; i < coinWeightInStack.length; i++) {
			weighings++;
			if (coinWeightInStack[i] == 11)
				return i + 1; //i + 1 is the number of the stack with the fake coins.
		}
		return -1; //if somehow there were no fake coins in any stacks, return -1
	}
}
