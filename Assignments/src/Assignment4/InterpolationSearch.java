//Name:			Parker Smith
//Class:		CS 4306/1
//Term:			Spring 2022
//Instructor:	Dr. Haddad
//Assignment:	4
package Assignment4;

public class InterpolationSearch {
	public boolean Found = false;
	public int Index = -1;
	public int Divisions = 0;
	
	public InterpolationSearch(int input[], int key) {
		int low = 0;
		int mid;
		int high = input.length - 1;
		
		while(low <= high && key <= input[high] && key >= input[low]) {
			mid = low + (((key - input[low]) * (high - low)) / (input[high] - input[low]));
			
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
	}
}
