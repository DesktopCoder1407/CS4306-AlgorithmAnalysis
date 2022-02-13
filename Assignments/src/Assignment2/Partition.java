//Name:		Parker Smith
//Class:		CS 4306/1
//Term:		Spring 2022
//Instructor:	Dr. Haddad
//Assignment:	2

/* -----Algorithm Design Block-----
* 
* Problem 3: Design an algorithm that, when given n positive integers, partition them into two disjoint subsets with the same sum of their elements.
* 
* PSEUDOCODE:
* //n number of positive integers
* //A[n] is an array of size n filled with the inputed integers
* //B[n] is an array of size n tracking our test subset
* //target is the sum of all input values divided by two
* sum <- 0 //the sum of all values we are currently tracking
* c <- 1 //the number of comparisons
* 
* if target % 1 != 0
*   return that no disjoint subsets with the same sum exist //Math behind this: target = 2x where x equals the sum of one subset, therefore target MUST be even.
* i <- 0
* while i < n do
*   c <- c + 1
*   sum <- sum + A[i]
*   c <- c + 1
*   if sum = target
*     B[i] <- 1
*     return two separate subsets, one of all the values from A[n] where B[n] is 1, and one of all the values from A[n] where B[n] is 0.
*   c <- c + 1
*   if sum > target
*     sum <- sum - A[i]
*     c <- c + 1
*     if i < n-1
*       i <- i + 1
*       continue
*     foundTracked <- 0
*     for j <- n-1 to 0 do
*       c <- c + 1
*       if B[j] = 1
*         foundTracked <- 1
*         B[j] <- 0
*         sum <- sum - A[j]
*         i <- j + 1
*         break
*     c <- c + 1
*     if foundTracked = 0
*       return that no disjoint subsets with the same sum exist
*     continue
*   B[i] <- 1
*   i <- i + 1
* return that no disjoint subsets with the same sum exist
* 
* PERFORMANCE ANALYSIS: //Sorry this looks messy, I really don't know how to do a performance analysis for a large while loop controlled by a variable that is changed inside the while loop.
* 							The Best Case and Worst Case I got from placing markers in my code and testing with multiple different values to see what sort of general shape comes out. 
* 							They are not by any means accurate, but I feel they give a close enough glipse into the rate of gtowth that I can feel confident writing it.
* 						|WHILE LOOP ------------------------------------------------------------------------------------------------------------------------------------------------------------|
* Assigns+Comp	|Assign	|Min (Comp)	|Assign	|Comp	|Assign	(Max)	|Comp^	|Assign (Max)	|Comp+		|Assign (Max)	|Assign (Max)	|j		|Comp*	|Assign x4 (Max)	|Comp	|			|Assign x2	|
* 2				|1		|1			|1		|1		|1, return		|1		|1				|1			|1, continue+	|1				|n-1	|1		|4, break*			|1		|continue^	|2			|
* -				|-		|1			|1		|1		|1, return		|1		|1				|1			|1, continue+	|1				|n-2	|1		|4, break*			|1		|continue^	|2			|
* -				|-		|1			|1		|1		|1, return		|1		|1				|1			|1, continue+	|1				|n-3	|1		|4, break*			|1		|continue^	|2			|
* -				|-		|1			|1		|1		|1, return		|1		|1				|1			|1, continue+	|1				|1		|1		|4, break*			|1		|continue^	|2			|
* -				|-		|1			|1		|1		|1, return		|1		|1				|1			|1, continue+	|1				|0		|1		|4, break*			|1		|continue^	|2			|
* --------------|-------|-----------|-------|-------|---------------|-------|---------------|-----------|---------------|---------------|-------|-------|-------------------|-------|-----------|-----------|
* 2				|1		|			|		|		|				|		|				|			|
*
* Best Case: 2 = 2
* Worst Case: Approximately 2^n
* Average Case: 2^n+2/2
* Big O Notation: O(2^n)
*/
package Assignment2;
import java.util.Scanner;
import java.util.LinkedList;

public class Partition {
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
			System.out.println("3. Run algorithm and display output");
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
				System.out.printf("\nSet size:\t\t\t%d integers\n", size);
				System.out.print("Integer values:\t\t\t");
				for (int i : integerValues)
					System.out.print(i + " ");
				System.out.println();
				
				System.out.print("Disjoint subsets with same sum:\t");
				System.out.println(partitionProblem(integerValues));
			}
			else if (input == 4) { //Exits the program
				break;
			}
			else
				System.out.println("Please enter an integer number from 1 to 4.");
		}
		scan.close();
	}
	
	static String partitionProblem(int[] input) {
		//Variables
		int n = input.length;
		double targetSum = 0;
		//-Tracking
		boolean[] trackedSet = new boolean[n];
		int trackedSum = 0;
		
		//Set the target to be the sum of all input values divided by 2
		for (int i : input)
			targetSum += i;
		targetSum /= 2;
		
		//Check to make sure that the sum of the input values are even.
		//  If not, we can return immediately without needing the full algorithm.
		//  Math behind this: targetSum = 2x where x equals the sum of one subset, therefore targetSum MUST be even.
		if (targetSum % 1 != 0)
			return "No disjoint subsets with the same sum of their elements found.";
		
		//Primary loop
		int i = 0;
		while (i < n) {
			trackedSum += input[i]; //Add the currently selected input to the subset's sum
			if(trackedSum == targetSum) { //If the tracked sum equals the target sum (result successfully found)
				trackedSet[i] = true; //Add the currently selected input to the tracked subset
				LinkedList<Integer> subset1 = new LinkedList<Integer>();
				LinkedList<Integer> subset2 = new LinkedList<Integer>();
				for(int j = 0; j < n; j++) {
					if (trackedSet[j])
						subset1.add(input[j]); //Add all items in the tracked input to subset 1
					else
						subset2.add(input[j]); //Add all other items to subset 2
				}
				return String.format("%s\n\t\t\t\t%s", subset1.toString(), subset2.toString()).replace('[', '{').replace(']', '}'); //Formatted Output
			}
			if(trackedSum > targetSum) { //If the tracked sum is larger than the target sum
				trackedSum -= input[i]; //Remove the newly added input from the tracked subset
				if(i < n-1) { //If this is not the last element in the input, skip it.
					i++;
					continue;
				}
				//If this is the last element in the input
				boolean foundTracked = false;
				for (int j = n - 1; j >= 0; j--) { //Loop backwards through each element
					if(trackedSet[j] == true) { //If the element is in the tracked subset
						foundTracked = true;
						trackedSet[j] = false; //Remove it from the tracked subset
						trackedSum -= input[j];
						i = j+1; //Set the next element to be iterated to be the one after the element just removed from the tracked subset
						break; //Break out of the loop
					}
				}
				if (foundTracked == false)
					//If no tracked items were found, the entire input has been searched.
					return "No disjoint subsets with the same sum of their elements found.";
				continue;
			}
			trackedSet[i] = true;
			i++;
		}
		return "No disjoint subsets with the same sum of their elements found.";
	}
}