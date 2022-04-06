//Name: 		Parker Smith
//Class: 		CS 4306/1
//Term: 		Spring 2022
//Instructor: 	Dr. Haddad
//Assignment: 	5
package Assignment5;

public class Quicksort {
	public int[] sortedArray;
	public int comparisons = 0;
	
	public Quicksort(int[] unsortedArray) {
		sortedArray = unsortedArray.clone();
		Sort(sortedArray, 0, unsortedArray.length - 1);
	}
	
	private void Sort(int[] unsortedArray, int l, int r) {
		comparisons++;
		if (r > l) { //If the size of the array to sort is greater than 1
			int s = GetPartition(unsortedArray, l, r); //Find the partition position
			Sort(unsortedArray, l, s - 1); //Sort the array left of the partition
			Sort(unsortedArray, s + 1, r); //Sort the array right of the partition
		}
	}
	
	private int GetPartition(int[] unsortedArray, int l, int r) {
		int s = unsortedArray[l]; int i = l; int j = r + 1;
		do { //Main loop body while i and j have not crossed.
			do { //Increment i while it finds elements less than s AND while i is within bounds
				i++; comparisons += 2;
			} while (i < r && unsortedArray[i] < s);
			
			do { //Decrement i while it finds elements greater than s
				j--; comparisons++;
			} while (unsortedArray[j] > s);
			
			//Swap the values at i and j
			int temp = unsortedArray[i];
			unsortedArray[i] = unsortedArray[j];
			unsortedArray[j] = temp;
			
			comparisons++;
		} while (i < j);
		
		//Revert previous swap since i and j crossed
		int temp = unsortedArray[i];
		unsortedArray[i] = unsortedArray[j];
		unsortedArray[j] = temp;
		
		//Swap s with the value at j
		unsortedArray[l] = unsortedArray[j];
		unsortedArray[j] = s;
		
		return j; //Return the partition position at j
	}
}
