//Name: 		Parker Smith
//Class: 		CS 4306/1
//Term: 		Spring 2022
//Instructor: 	Dr. Haddad
//Assignment: 	5
package Assignment5;

public class Quicksort {
	public int[] sortedArray;
	public int divisions = 0;
	public int copies = 0;
	
	public Quicksort(int[] unsortedArray) {
		Sort(unsortedArray, 0, unsortedArray.length - 1);
		sortedArray = unsortedArray;
	}
	
	private void Sort(int[] unsortedArray, int l, int r) {
		if (r > l) {
			int s = GetPartition(unsortedArray, l, r);
			Sort(unsortedArray, l, s - 1);
			Sort(unsortedArray, s + 1, r);
		}
	}
	
	private static int GetPartition(int[] unsortedArray, int l, int r) {
		int s = unsortedArray[l]; int i = l; int j = r + 1;
		do {
			do
				i++;
			while (unsortedArray[i] < s);
			do
				j--;
			while (unsortedArray[j] > s);
			
			int temp = unsortedArray[i];
			unsortedArray[i] = unsortedArray[j];
			unsortedArray[j] = temp;
		} while (i < j);
		
		int temp = unsortedArray[i];
		unsortedArray[i] = unsortedArray[j];
		unsortedArray[j] = temp;
		
		unsortedArray[l] = unsortedArray[j];
		unsortedArray[j] = s;
		
		return j;
	}
}
