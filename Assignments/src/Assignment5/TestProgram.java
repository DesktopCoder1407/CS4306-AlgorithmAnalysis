//Name: 		Parker Smith
//Class: 		CS 4306/1
//Term: 		Spring 2022
//Instructor: 	Dr. Haddad
//Assignment: 	5
package Assignment5;

import java.util.Scanner;
import java.util.Random;

public class TestProgram {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int input;
		
		int[] Random10_3 = new int[0];
		int[] Random10_4 = new int[0];
		int[] Random10_5 = new int[0];
		int[] Random10_6 = new int[0];
		
		int[] Increasing10_3 = new int[0];
		int[] Increasing10_4 = new int[0];
		int[] Increasing10_5 = new int[0];
		int[] Increasing10_6 = new int[0];
		
		int[] Decreasing10_3 = new int[0];
		int[] Decreasing10_4 = new int[0];
		int[] Decreasing10_5 = new int[0];
		int[] Decreasing10_6 = new int[0];
		
		//3D results array. 1st dimension is array type. 2nd dimension is algorithm. 3rd dimension is array size.
		int[][][] Results = new int[3][3][4];
		
		while(true) {
			//Main Menu Prompt
			System.out.println("\n---------------MAIN MENU---------------");
			System.out.println("1. Populate Arrays");
			System.out.println("2. Run Algorithms");
			System.out.println("3. Display outputs");
			System.out.println("4. Exit program");
			System.out.print("\nEnter option number: ");
			
			//Input error detection
			try {
				input = scan.nextInt(); //Stores input
			} catch(Exception e) {System.out.println("Please enter an integer number."); continue;}
			finally {scan.nextLine();}
			
			//Results from Input
			if (input == 1) { //Populate the Arrays
				Random10_3 = RandomIntegers(Random10_3, 1000);
				Random10_4 = RandomIntegers(Random10_4, 10000);
				Random10_5 = RandomIntegers(Random10_5, 100000);
				Random10_6 = RandomIntegers(Random10_6, 1000000);
				
				Increasing10_3 = IncreasingIntegers(Increasing10_3, 1000);
				Increasing10_4 = IncreasingIntegers(Increasing10_4, 10000);
				Increasing10_5 = IncreasingIntegers(Increasing10_5, 100000);
				Increasing10_6 = IncreasingIntegers(Increasing10_6, 1000000);
				
				Decreasing10_3 = DecreasingIntegers(Decreasing10_3, 1000);
				Decreasing10_4 = DecreasingIntegers(Decreasing10_4, 10000);
				Decreasing10_5 = DecreasingIntegers(Decreasing10_5, 100000);
				Decreasing10_6 = DecreasingIntegers(Decreasing10_6, 1000000);
			}
			else if (input == 2) { //Runs the Algorithms
				Results[0][0][0] = new Mergesort(Random10_3).comparisons;
				Results[0][0][1] = new Mergesort(Random10_4).comparisons;
				Results[0][0][2] = new Mergesort(Random10_5).comparisons;
				Results[0][0][3] = new Mergesort(Random10_6).comparisons;
				
				Results[0][1][0] = new Quicksort(Random10_3).comparisons;
				Results[0][1][1] = new Quicksort(Random10_4).comparisons;
				Results[0][1][2] = new Quicksort(Random10_5).comparisons;
				Results[0][1][3] = new Quicksort(Random10_6).comparisons;
				
				Results[0][2][0] = new Heapsort(Random10_3).comparisons;
				Results[0][2][1] = new Heapsort(Random10_4).comparisons;
				Results[0][2][2] = new Heapsort(Random10_5).comparisons;
				Results[0][2][3] = new Heapsort(Random10_6).comparisons;
				
				
				Results[1][0][0] = new Mergesort(Increasing10_3).comparisons;
				Results[1][0][1] = new Mergesort(Increasing10_4).comparisons;
				Results[1][0][2] = new Mergesort(Increasing10_5).comparisons;
				Results[1][0][3] = new Mergesort(Increasing10_6).comparisons;
				
				Results[1][1][0] = new Quicksort(Increasing10_3).comparisons;
				Results[1][1][1] = new Quicksort(Increasing10_4).comparisons;
				//Results[1][1][2] = new Quicksort(Increasing10_5).comparisons; //ERROR DUE TO STACK OVERFLOW AND LACK OF MEMORY SPACE
				//Results[1][1][3] = new Quicksort(Increasing10_6).comparisons; //ERROR DUE TO STACK OVERFLOW AND LACK OF MEMORY SPACE
				
				Results[1][2][0] = new Heapsort(Increasing10_3).comparisons;
				Results[1][2][1] = new Heapsort(Increasing10_4).comparisons;
				Results[1][2][2] = new Heapsort(Increasing10_5).comparisons;
				Results[1][2][3] = new Heapsort(Increasing10_6).comparisons;
				
				
				Results[2][0][0] = new Mergesort(Decreasing10_3).comparisons;
				Results[2][0][1] = new Mergesort(Decreasing10_4).comparisons;
				Results[2][0][2] = new Mergesort(Decreasing10_5).comparisons;
				Results[2][0][3] = new Mergesort(Decreasing10_6).comparisons;
				
				Results[2][1][0] = new Quicksort(Decreasing10_3).comparisons;
				Results[2][1][1] = new Quicksort(Decreasing10_4).comparisons;
				//Results[2][1][2] = new Quicksort(Decreasing10_5).comparisons; //ERROR DUE TO STACK OVERFLOW AND LACK OF MEMORY SPACE
				//Results[2][1][3] = new Quicksort(Decreasing10_6).comparisons; //ERROR DUE TO STACK OVERFLOW AND LACK OF MEMORY SPACE
				
				Results[2][2][0] = new Heapsort(Decreasing10_3).comparisons;
				Results[2][2][1] = new Heapsort(Decreasing10_4).comparisons;
				Results[2][2][2] = new Heapsort(Decreasing10_5).comparisons;
				Results[2][2][3] = new Heapsort(Decreasing10_6).comparisons;
			}
			else if (input == 3) { //Displays the Outputs
				for(int i = 0; i < Results.length; i++) {
					if (i == 0)
						System.out.println("\nArray Type: Random");
					else if (i == 1)
						System.out.println("\nArray Type: Increasing");
					else
						System.out.println("\nArray Type: Decreasing");
					System.out.println("Algorithm     n=1000     n=10000     n=100000     n=1000000");
					System.out.println("-----------------------------------------------------------");
					
					for(int j = 0; j < Results[i].length; j++) {
						if (j == 0)
							System.out.print("Mergesort");
						else if (j == 1)
							System.out.print("Quicksort");
						else
							System.out.print("Heapsort ");
						
						for(int k = 0; k < Results[i][j].length; k++) {
							System.out.printf("%11d  ", Results[i][j][k]);
						}
						
						System.out.println();
					}
				}
			}
			else if (input == 4) { //Exits the program
				break;
			}
			else
				System.out.println("Please enter an integer number from 1 to 4.");
		}
		scan.close();
	}
	
	public static int[] RandomIntegers(int[] a, int n) {
		Random rand = new Random();
		
		a = new int[n];
		for(int i = 0; i < n; i++)
			a[i] = rand.nextInt(n);
		
		return a;
	}
	
	public static int[] IncreasingIntegers(int[] a, int n) {
		a = new int[n];
		for(int i = 1; i <= n; i++)
			a[i - 1] = i;
		
		return a;
	}

	public static int[] DecreasingIntegers(int[] a, int n) {
		a = new int[n];
		for(int i = 0; i < n; i++)
			a[i] = n - i;
		
		return a;
	}
}
