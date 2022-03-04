//Name:			Parker Smith
//Class:		CS 4306/1
//Term:			Spring 2022
//Instructor:	Dr. Haddad
//Assignment:	3

/* -----Algorithm Design Block-----
* 
* Problem 1: Compare the implementation of the insertion sort algorithm given on page 134 to that given in the exercise.
* 
* PSEUDOCODE for algorithm on page 134:
* //A[n] is an array of n elements
* //c1 is a count of the first while loop condition
* //c2 is a count of the second while loop condition
* //c3 is a count of the first assignment statement in the while loop
* //c4 is a count of the second assignment statement in the while loop
* 
* for i <- 1 to n - 1 do
*   v <- A[i]
*   j <- i - 1
*   
*   while j >= 0 and A[j] > v do
*     c1 <- c1 + 1
*     c2 <- c2 + 1
*     A[j+1] <- A[j]
*     c3 <- c3 + 1
*     j <- j - 1
*     c4 <- c4 + 1
*   c1 <- c1 + 1
*   c2 <- c2 + 1
*   A[j+1] <- v
*   
* PSEUDOCODE for algorithm in the exercise:
* //A[n] is an array of n elements
* //c1 is a count of the first while loop condition
* //c2 is a count of the second while loop condition
* //c3 is a count of the swap
* //c4 is a count of the assignment statement in the while loop
* 
* for i <- 1 to n - 1 do
*   j <- i - 1
*   
*   while j >= 0 and A[j] > A[j+1]
*     c1 <- c1 + 1
*     c2 <- c2 + 1
*     swap(A[j], A[j+1])
*     c3 <- c3 + 1
*     j <- j - 1
*     c4 <- c4 + 1
*   c1 <- c1 + 1
*   c2 <- c2 + 1
*     
* 
* CODE RUN RESULTS:
* 
* Array Size	|Array Values		|Insertion Sort Operations	|Insertion Sort 2 Operations
* --------------|-------------------|---------------------------|---------------------------
* 3				|5, 14, 2			|4 + 4 + 2 + 2 = 12			|4 + 4 + 2 + 2 = 12
* 3				|2, 1, -12			|5 + 5 + 3 + 3 = 16			|5 + 5 + 3 + 3 = 16
* 5				|9, 32, 14, 12, 63	|7 + 7 + 3 + 3 = 20			|7 + 7 + 3 + 3 = 20
* 6				|42, 4, 62, 17, 1, 6|15 + 15 + 10 + 10 = 50		|15 + 15 + 10 + 10 = 50
* 
* From these results, It seems that both InsertionSort and InsertionSort2 take approximately the same time (when counting statements within the inner while loop)
* From a simple graph of these four results, both algorithms seem to run in O(n^2)
*/
package Assignment3;
import java.util.Scanner;

public class InsertionCompare {
	static int[] operations = new int[4];
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int input;
		int size = 0;
		int[] unsortedArray = new int[size];
		
		while(true) {
			//Main Menu Prompt
			System.out.println("\n-------------MAIN MENU--------------");
			System.out.println("1. Read array size");
			System.out.println("2. Read array values (integer type)");
			System.out.println("3. Run algorithm comparison and display results");
			System.out.println("4. Exit program");
			System.out.print("\nEnter option number: ");
			
			//Input error detection
			try {
				input = scan.nextInt(); //Stores input
			} catch(Exception e) {System.out.println("Please enter an integer number."); continue;}
			finally {scan.nextLine();}
			
			//Results from Input
			if (input == 1) { //Reads the array size
				System.out.print("\nPlease enter the number of integers in the array: ");
				try {size = scan.nextInt();}
				catch(Exception e) {System.out.println("Please enter an integer value.");}
			}
			else if (input == 2) { //Reads array values
				unsortedArray = new int[size];
				for (int i = 0; i < size; i++) {
					System.out.printf("\nPlease enter an array value (%d of %d):", i + 1, size);
					try {unsortedArray[i] = scan.nextInt();}
					catch(Exception e) {System.out.println("Please enter only integer values. The set of integer values has been cleared."); unsortedArray = new int[0]; break;}
				}
			}
			else if (input == 3) { //Runs algorithm comparison and displays results
				System.out.println("\nArray size:\t\t" + size);
				System.out.print("Array values:\t\t");
				for (int i : unsortedArray)
					System.out.print(i + " ");
				
				InsertionSort(unsortedArray);
				System.out.println("\nInsertionSort Stats:");
				System.out.println("\tj >= 0:\t\t" + operations[0]);
				System.out.println("\tA[j] > v:\t" + operations[1]);
				System.out.println("\tA[j+1] = a[j]:\t" + operations[2]);
				System.out.println("\tj = j-1:\t" + operations[3]);
				
				InsertionSort2(unsortedArray);
				System.out.println("\nInsertionSort2 Stats:");
				System.out.println("\tj >= 0:\t\t" + operations[0]);
				System.out.println("\tA[j] > A[j+1]:\t" + operations[1]);
				System.out.println("\tSwap:\t\t" + operations[2]);
				System.out.println("\tj = j-1:\t" + operations[3]);
			}
			else if (input == 4) { //Exits the program
				break;
			}
			else
				System.out.println("Please enter an integer number from 1 to 4.");
		}
		scan.close();
	}
	
	static int[] InsertionSort(int[] unsortedArray) {
		operations = new int[4]; //Reset all operations to 0.
		int[] input = unsortedArray.clone(); //Clone the unsorted array so it is unaffected by changes within the function.
		int n = input.length;
		
		for (int i = 1; i <= n - 1; i++) { //Loop through each element in the array, skipping the first because we count it as sorted.
			int temp = input[i]; //Assign the current value we are sorting to a temp variable
			int j = i - 1; //Last element in the sorted part of the list
			
			while(j >= 0 && input[j] > temp) { //While we are within bounds of the array AND the current sorted element is greater than the value we are sorting (This allows us to sort in ascending order)
				operations[0]++;
				operations[1]++;
				input[j+1] = input[j]; //Shift the currently sorted element to the right by one position
				operations[2]++;
				
				j = j - 1; //Move one element to the left in the sorted portion of the list
				operations[3]++;
			}
			operations[0]++;
			operations[1]++;
			input[j+1] = temp; //Place the current value we are sorting in its final place in the sorted portion of the array.
		}
		
		return input;
	}
	
	static int[] InsertionSort2(int[] unsortedArray) {
		operations = new int[4]; //Reset all operations to 0.
		int[] input = unsortedArray.clone(); //Clone the unsorted array so it is unaffected by changes within the function.
		int n = input.length;
		
		for (int i = 1; i <= n - 1; i++) { //Loop through each element in the array, skipping the first because we count it as sorted.
			int j = i - 1; //Last element in the sorted part of the list.
			
			while (j >= 0 && input[j] > input[j+1]) { //While we are within bounds of the array AND the current sorted element is greater than the element to the right (This allows us to sort in ascending order)
				operations[0]++;
				operations[1]++;
				//Swap input[j] and input[j+1], assuring these two elements are in ascending order
				int temp = input[j];
				input[j] = input[j+1];
				input[j+1] = temp;
				operations[2]++;
				
				j = j - 1; //Move one element to the left in the sorted portion of the list.
				operations[3]++;
			} //At the end of the while loop, the j'th element will always be in it's final position, and since this loop has run since i = 1 (due to this being decrease and conquer), all elements from i to 0 are in their final position.
			operations[0]++;
			operations[1]++;
		}
		
		return input;
	}
}
