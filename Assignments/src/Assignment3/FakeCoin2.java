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
* FindFake(A[n], l, r):
* //A[n] is an array of n elements containing the weights of the coins
* //l is the index of the leftmost element
* //r is the index of the rightmost element
* //d is the a count of the number of divisions currently taken
* 
* d <- 0
* numCoins <- r - l + 1
* 
* if numCoins = 1 //If there is only one coin, return the index.
*   return l
* if numCoins = 2 //If there are two coins, return the index of the smaller.
*   if A[l] < A[r]
*     return l
*   else
*     return r
* 
* //Set the split locations to determine the first and second thirds of the coins.
* split1 <- l + (numCoins/3) - 1
* split2 <- l + 2*(numCoins/3) - 1
* sumFirstThird <- 0
* sumSecondThird <- 0
* 
* //Sum the First and Second thirds.
* for i <- l to split1 do
*   sumFirstThird <- sumFirstThird + A[i]
* for i <- split1 + 1 to split2 do
*   sumSecondThird <- sumSecondThird + A[i]
* 
* //Recurrence: The third of the array that weights less is re-run through the FindFake function.
* if sumFirstThird < sumSecondThird
*   d <- d + 1
*   FindFake(A, l, split1)
* else if sumSecondThird < sumFirstThird
*   d <- d + 1
*   FindFake(A, split1 + 1, split2)
* else
*   d <- d + 1
*   FindFake(A, split2 + 1, r)
* 
* 
* PART B, RECURRENCE RELATION:
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
				System.out.println("\nArray size:\t\t" + size);
				System.out.print("Array values:\t\t");
				for (int i : coins)
					System.out.print(i + " ");
				divisions = 0;
				int fakeCoinIndex = FindFake(coins, 0, coins.length - 1);
				System.out.println("\n# of divisions:\t\t" + divisions);
				System.out.println("Fake coin index:\t" + fakeCoinIndex);
			}
			else if (input == 4) { //Exits the program
				break;
			}
			else
				System.out.println("Please enter an integer number from 1 to 4.");
		}
		scan.close();
	}
	
	static int FindFake(int[] coins, int l, int r) {
		int numCoins = r - l + 1;
		
		if (numCoins == 1) //If there is only one coin, return the index.
			return l;
		if (numCoins == 2) { //If there are two coins, return the index of the smaller coin.
			if (coins[l] < coins[r])
				return l;
			else
				return r;
		}
		
		//Set the splits between the thirds of the coins
		int split1 = l + (numCoins/3) - 1;
		int split2 = l + 2*(numCoins/3) - 1;
		int sumFirstThird = 0;
		int sumSecondThird = 0;
		
		//Sum the first two of the three thirds.
		for (int i = l; i <= split1; i++)
			sumFirstThird += coins[i];
		for (int i = split1 + 1; i <= split2; i++)
			sumSecondThird += coins[i];
		
		if (sumFirstThird < sumSecondThird) { //If the first third is smaller (has the fake) rerun the FindFake algorithm with that section of the array.
			divisions++;
			return FindFake(coins, l, split1);
		}
		else if (sumSecondThird < sumFirstThird) { //If the second third is smaller (has the fake) rerun the FindFake algorithm with that section of the array.
			divisions++;
			return FindFake(coins, split1 + 1, split2);
		}
		else { //If the rest of the array is smaller (has the fake) rerun the FindFake algorithm with that section of the array.
			divisions++;
			return FindFake(coins, split2 + 1, r);
		}
	}
}