//Name: 		Parker Smith
//Class: 		CS 4306/1
//Term: 		Spring 2022
//Instructor: 	Dr. Haddad
//Assignment: 	5
//
//ALGORITHM PERFORMANCE REVIEW:
//
//Best Case: O(n*logn)
//Worst Case: O(n^2)
//Average Case: O(n*logn)
//
//Thoughts/Observations:
//Quicksort was the best algorithm for the Random number tests. Besides that, it performed the worst, causing a stack overflow on n=100,000 and n=1,000,000. This is due to Quicksort's
//	quadratic performance in the worst case.
//
//In a best case scenario, the partition position is in the middle of the array every time, evenly splitting the array and allowing two equally sized sort functions to run on the subsets.
//	This allows for log(n) splits multiplied by n times running the partition function for each split: n*log(n).
//In a worst case scenario, the partition position is either at the beginning or end of the array, forcing one sort function to do nothing and another split function to rerun the entire
//	size of the array - 1. This results in n splits multiplied by n times running the partition function. The result is O(n^2) or quadratic performance, the same as basic sort algorithms, but
//	with the added cost of taking up stack space in memory.
//In the average case, as shown with random numbers in the test program, Quicksort leans towards optimal n*log(n) efficiency.
//
//In regards to comparisons, the Random array type turned in remarkably close numbers to n*log(n) comparisons. For the other array types, there was anywhere from 1/2n^2 to 3/4n^2 comparisons.
//	The higher values of n=100,000 and n=1,000,000 could not be run because of a stack overflow within memory. These discrepancies are due to the way Quicksort selects a partition position.
//
//For the Random array type, there was an average of 2.2n*log(n) comparisons. For the Increasing array type, there was an average of 1/2n^2 comparisons. For the Decreasing array type,
//	there was an average of 3/4n^2 comparisons.
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
