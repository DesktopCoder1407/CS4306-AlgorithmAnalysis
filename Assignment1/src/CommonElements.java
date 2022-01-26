// Name:		Parker Smith
// Class:		CS 4306/1
// Term:		Spring 2022
// Instructor:	Dr. Haddad
// Assignment:	1

/* -----Algorithm Design Block-----
 * 
 * Problem 1: Design an algorithm to find all the common elements in two sorted lists of numbers.
 * 
 * For this problem, I felt the simplest way to solve would be to loop through the first list 
 * with m elements, then loop through the second list with n elements, checking whether element
 * m equals element n. If a match is found while looping through the second list, remove the
 * matching element from the second list and add it to the list of common values. The loop
 * through the first list will continue to the next element.
 * 
 * Pseudocode:
 * //m is the length of the first list
 * //n is the length of the second list
 * //L1 is the first list
 * //L2 is the second list
 * //CL is the common list
 * 
 * for i <- 0 to m - 1 do
 *   for j <- 0 to n - 1 do
 *     if L1[i] == L2[j] then
 *       CL.Add(L2[j])
 *       L2.Remove(j)
 *       break
 * return CL
 * 
 * The maximum number of comparisons that my algorithm would make if the lengths of the two given
 * lists are m and n is m*n. This would only happen if neither algorithm had common elements.
 * 
 */
import java.util.Scanner;
import java.util.ArrayList;

public class CommonElements {
	static int comparisons = 0;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int input;
		ArrayList<Integer> list1 = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		
		while(true) {
			//Main Menu Prompt
			System.out.println("\n-----------------MAIN MENU--------------");
			System.out.println("1. Read input lists (integer values)");
			System.out.println("2. Run algorithm and display output");
			System.out.println("3. Exit program");
			System.out.print("\nEnter option number: ");
			
			input = scan.nextInt(); //Stores input
			scan.reset();
			
			if (input == 3) //Exits the program upon receiving a 3
				break;
			else if (input == 2) { //Runs the algorithm and displays results upon receiving a 2
				System.out.println("\nList 1: " + list1.toString());
				System.out.println("List 2: " + list2.toString());
				System.out.println("Common Values: " + getCommonElements(list1, list2).toString());
				System.out.println("Comparisons: " + comparisons);
			}
			else if (input == 1) { //Reads two input lists upon receiving a 1
				list1.clear();
				list2.clear();
				System.out.print("\nPlease enter the first list of integers separated by spaces: ");
				scan.nextLine();
				for(String i : scan.nextLine().split(" "))
					list1.add(Integer.parseInt(i));
				System.out.print("Please enter the second list of integers separated by spaces: ");
				for(String i : scan.nextLine().split(" "))
					list2.add(Integer.parseInt(i));
			}
		}
		scan.close();
	}

	static ArrayList<Integer> getCommonElements(ArrayList<Integer> list1, ArrayList<Integer> list2) {
		ArrayList<Integer> commonList = new ArrayList<Integer>(); //List of all common integers
		comparisons = 0; //Resets the comparison counter to 0
		
		for(int i = 0; i < list1.size(); i++) { //Outer for loop, will always loop list1.size() times.
			for(int j = 0; j < list2.size(); j++) { //Inner for loop, best case: loops 0 times, worst case: loops list2.size() times.
				comparisons++; //Increments the number of comparisons completed during this algorithm.
				if(list1.get(i) == list2.get(j)) { //If list1 has a common element is list2 in this inner loop cycle,
					commonList.add(list2.get(j)); //Add it to the common list
					list2.remove(j); //remove it from list2 so it doesn't get counted again
					break; //break from the inner loop and continue to the next value in the outer loop
				}
			}
		}
		return commonList;
	}
}
