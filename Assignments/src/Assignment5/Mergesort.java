//Name: 		Parker Smith
//Class: 		CS 4306/1
//Term: 		Spring 2022
//Instructor: 	Dr. Haddad
//Assignment: 	5
package Assignment5;

import java.util.Arrays;

public class Mergesort {
	public int[] sortedArray;
	public int comparisons = 0;
	
	public Mergesort(int[] unsortedArray) {
		sortedArray = Sort(unsortedArray.clone());
	}
	
	private int[] Sort(int[] unsortedArray) {
		comparisons++;
		if (unsortedArray.length > 1) { //If there is more than one element in the array,
			//Split the array into two evenly sized arrays.
			int[] leftArray = Arrays.copyOf(unsortedArray, unsortedArray.length / 2);
			int[] rightArray = Arrays.copyOfRange(unsortedArray, unsortedArray.length / 2, unsortedArray.length);
			
			//Sort each sub array recursively
			leftArray = Sort(leftArray);
			rightArray = Sort(rightArray);
			
			//Merge the results of both sub-sorts and return the result.
			return Merge(leftArray, rightArray);
		}
		else //If the array is of length one, it is already sorted, so it can just be returned.
			return unsortedArray;
	}
	
	private int[] Merge(int[] A, int[] B) {
		int[] sorted = new int[A.length + B.length]; //Empty array that can hold both A and B arrays.
		int i = 0; int j = 0; //A and B Pointers
		
		//While the pointers have not reached the end of their respective lists
		while (i < A.length && j < B.length) {
			comparisons += 2;
			
			//If A's value is smaller, place it in sorted first and increment A's pointer, otherwise place B's value in sorted and increment its pointer.
			comparisons++;
			if(A[i] <= B[j])
				sorted[i+j] = A[i++];
			else
				sorted[i+j] = B[j++];
		}
		comparisons++;
		
		//Once a list is depleted, find the list that contains the remaining values and copy it into sorted.
		comparisons++;
		if (i == A.length)
			for(int k = i + j; k < sorted.length; k++) {
				comparisons++;
				sorted[k] = B[j++];
			}
		else
			for(int k = i + j; k < sorted.length; k++) {
				comparisons++;
				sorted[k] = A[i++];
			}
		comparisons++;
		
		return sorted; //Return the final, sorted value.
	}
}
