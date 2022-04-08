//Name: 		Parker Smith
//Class: 		CS 4306/1
//Term: 		Spring 2022
//Instructor: 	Dr. Haddad
//Assignment: 	5
//
//ALGORITHM PERFORMANCE REVIEW:
//
//Best Case: O(n*logn)
//Worst Case: O(n*logn)
//Average Case: O(n*logn)
//
//Thoughts/Observations:
//Through all the tests, Heapsort was an overall average algorithm. It never explicitly beat any other algorithms, but it was never explicitly bad either. On addition of an item to a heap,
//	bottom-up reheapification is performed, with log(n) comparisons. This action is repeated n times, resulting in n*log(n) comparisons. Upon removal of an item from a heap,
//	top-down reheapification is performed, with log(n) comparisons repeated n times for each item removed. This results in n*log(n) comparisons. Added together, this results in 2n*log(n),
//	or O(n*logn) comparisons.
//
//For all array types, Heapsort had more comparisons than Mergesort. However, the increase in comparisons comes with the benefit of Heapsort being an in-place algorithm, 
//	meaning that it does not require extra memory space.
//
//Overall, there was an average of 4.6n*log(n) comparisons.
package Assignment5;

public class Heapsort {
	public int[] sortedArray;
	public int comparisons = 0;
	
	private int[] heap;
	private int endOfHeap = 0;
	
	public Heapsort(int[] unsortedArray) {
		heap = new int[unsortedArray.length];
		sortedArray = new int[unsortedArray.length];
		
		//Build the Heap
		for(int i = 0; i < unsortedArray.length; i++) {
			comparisons++; add(unsortedArray[i]);}
		
		//Remove the topmost (smallest) element from the heap and place it in the sorted array.
		for(int i = 0; i < unsortedArray.length; i++) {
			comparisons++; sortedArray[i] = remove();}
		comparisons += 2;
	}
	
	private void add(int val) {
		heap[endOfHeap] = val;
		int newNode = endOfHeap++;

		//While the node we are point to is not the root AND the node is less than (has a higher priority) than it's parent
		while(newNode > 0 && heap[getParent(newNode)] > val) {
			comparisons += 2;
			
			//Swap the node with it's parent
			int temp = heap[newNode];
			heap[newNode] = heap[getParent(newNode)];
			heap[getParent(newNode)] = temp;
			newNode = getParent(newNode); //Move the node pointer to it's new position
		}
		comparisons += 2;
	}
	
	private int remove() {
		//Remove top node and replace it with the node at the end of the heap
		int topNode = heap[0];
		heap[0] = heap[--endOfHeap];
		int currentNode = 0;
		
		while (true) { //Infinite loop broken from within
			comparisons++;
			if(hasLeftChild(currentNode) && hasRightChild(currentNode)) { //If both children exist
				comparisons++;
				if(heap[getLeftChild(currentNode)] < heap[getRightChild(currentNode)]) { //Pick the smallest of the two
					//Swap the left child with the currentNode
					int temp = heap[getLeftChild(currentNode)];
					heap[getLeftChild(currentNode)] = heap[currentNode];
					heap[currentNode] = temp;
					currentNode = getLeftChild(currentNode); //Update currentNode's position
				}
				else {
					//Swap the right child with the currentNode
					int temp = heap[getRightChild(currentNode)];
					heap[getRightChild(currentNode)] = heap[currentNode];
					heap[currentNode] = temp;
					currentNode = getRightChild(currentNode); //Update currentNode's position
				}
			}
			else if (hasLeftChild(currentNode) && heap[currentNode] > heap[getLeftChild(currentNode)]) { //If left child exists AND left child is smaller than currentNode
				comparisons++;
				//Swap the left child with the currentNode
				int temp = heap[getLeftChild(currentNode)];
				heap[getLeftChild(currentNode)] = heap[currentNode];
				heap[currentNode] = temp;
				currentNode = getLeftChild(currentNode); //Update currentNode's position
			}
			else if (hasRightChild(currentNode) && heap[currentNode] > heap[getRightChild(currentNode)]) { //If right child exists AND right child is smaller than currentNode
				comparisons += 2;
				//Swap the right child with the currentNode
				int temp = heap[getRightChild(currentNode)];
				heap[getRightChild(currentNode)] = heap[currentNode];
				heap[currentNode] = temp;
				currentNode = getRightChild(currentNode); //Update currentNode's position
			}
			else { //currentNode is a leaf OR is in it's correct position, break the loop. Reheapification is complete.
				comparisons += 2;
				break;
			}
		}
		
		return topNode;
	}
	
	private int getParent(int i) {return (i - 1) / 2;}
	
	private int getLeftChild(int i) {return (2 * i) + 1;}
	
	private int getRightChild(int i) {return 2 * i + 2;}
	
	private boolean hasLeftChild(int i) {comparisons++; return (getLeftChild(i) < endOfHeap) ? true : false;}
	
	private boolean hasRightChild(int i) {comparisons++; return (getRightChild(i) < endOfHeap) ? true : false;}
}
