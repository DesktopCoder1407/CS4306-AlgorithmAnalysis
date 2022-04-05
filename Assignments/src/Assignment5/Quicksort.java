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
			int s = GetPartition(unsortedArray);
			Sort(unsortedArray, l, s - 1);
			Sort(unsortedArray, s + 1, r);
		}
	}
	
	private static int GetPartition(int[] unsortedArray) {
		int s = unsortedArray[0]; int i = 1; int j = unsortedArray.length - 1;
		while (i < j) {
			while(unsortedArray[i] <= s && i < j)
				i++;
			while(unsortedArray[j] >= s)
				j--;
			
			int temp = unsortedArray[i];
			unsortedArray[i] = unsortedArray[j];
			unsortedArray[j] = temp;
			i++; j--;
		}
		
		/*int temp = unsortedArray[i];
		unsortedArray[i] = unsortedArray[j];
		unsortedArray[j] = temp;*/
		
		unsortedArray[0] = unsortedArray[j];
		unsortedArray[j] = s;
		
		return j;
	}
}
