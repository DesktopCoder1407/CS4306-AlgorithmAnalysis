package Assignment4;
import java.util.Random;
import java.util.Scanner;

public class TestIS {
	public static int[] RandomDistinct() {
		//Variables:
		int[] Values = new int[1024];
		Random rand = new Random();
		int randomValue;
		boolean containsValue;
		
		//Distinct, Random values from 1 to 9999:
		for(int i = 0; i < Values.length; i++) {
			do { //Loop while the randomizer has created a non-distinct value.
				randomValue = rand.nextInt(1, 10000); //New random value between 1 and 9999
				containsValue = false;
				
				//Loops through the filled part of the list, checking whether the random value has already been added or not.
				for(int j = 0; j < i; j++)
					if(Values[j] == randomValue)
						containsValue = true;
				
			} while (containsValue == true);
			Values[i] = randomValue; //Add the random value to the array. (Is guaranteed to be unique)
		}
		
		//InsertionSort:
		for (int i = 1; i < Values.length; i++) {
			int j = i - 1; //Pointer to the last element in the sorted part of the array.
			while(j >= 0 && Values[j] > Values[j+1]) //Loop while we are within bounds & the array is unsorted
			{
				//Swap unsorted values.
				int temp = Values[j];
				Values[j] = Values[j + 1];
				Values[j + 1] = temp;
				
				j--; //Move pointer one position to the left.
			}
		}
		
		return Values;
	}
	
	private static void DisplayArray(int[] input, int valsPerRow) {
		int counter = 0;
		for(int i = 0; i < input.length; i++) {
			if(counter++ % valsPerRow == 0) {
				System.out.print("\n" + input[i]);
			}
			else {
				System.out.print("\t" + input[i]);
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int input;
		int[] Values = new int[0];
		int outputTableSize = 0;
		
		while(true) {
			//Main Menu Prompt
			System.out.println("\n---------------MAIN MENU---------------");
			System.out.println("1. Create and display array Values[]");
			System.out.println("2. Read output table size");
			System.out.println("3. Run algorithm and display outputs");
			System.out.println("4. Exit program");
			System.out.print("\nEnter option number: ");
			
			//Input error detection
			try {
				input = scan.nextInt(); //Stores input
			} catch(Exception e) {System.out.println("Please enter an integer number."); continue;}
			finally {scan.nextLine();}
			
			//Results from Input
			if (input == 1) { //Creates and displays the array Values
				Values = RandomDistinct(); //Sets Values
				DisplayArray(Values, 30); //Displays Values with 30 values per row
			}
			else if (input == 2) { //Reads output table size
				try {outputTableSize = scan.nextInt();}
				catch(Exception e) {System.out.println("Please enter only an integer."); break;}
			}
			else if (input == 3) { //Runs algorithm and displays results
				
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
