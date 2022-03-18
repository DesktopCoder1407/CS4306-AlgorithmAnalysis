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
		int high = input.length - 1;
		int mid = input.length / 2;
		
		while(low != high && input[high] - input[low] > 0) {
			mid = low + ((high - low) / (input[high] - input[low])) * (key - input[low]);
			
			if(key < input[mid]) {
				Divisions++;
				high = mid - 1;
			}
			else if (key > input[mid]) {
				Divisions++;
				low = mid + 1;
			}
			else {
				Found = true;
				Index = mid;
				return;
			}
		}
		
		if (key == input[mid]) {
			Found = true;
			Index = mid;
		}
		else {
			Found = false;
			Index = -1;
		}
	}
}
