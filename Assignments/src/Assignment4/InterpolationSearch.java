//Name:			Parker Smith
//Class:		CS 4306/1
//Term:			Spring 2022
//Instructor:	Dr. Haddad
//Assignment:	4
package Assignment4;

public class InterpolationSearch {
	public boolean Found;
	public int Index;
	public int Divisions = 0;
	
	public InterpolationSearch(int input[], int key) {
		int low = 0;
		int mid;
		int high = input.length - 1;
		
		while(low <= high && key <= input[high] && key >= input[low]) {
			mid = (int)(low + ((double)(high - low) / (input[high] - input[low])) * (key - input[low]));
			
			if(key == input[mid]) {
				Found = true;
				Index = mid;
				return;
			}
			Divisions++;
			if(key > input[mid])
				low = mid + 1;
			else
				high = mid - 1;
		}
		
		Found = false;
		Index = -1;
	}
}
