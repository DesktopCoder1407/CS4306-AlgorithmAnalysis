//Name: 		Parker Smith
//Class: 		CS 4306/1
//Term: 		Spring 2022
//Instructor: 	Dr. Haddad
//Assignment: 	5
package Assignment5;

public class Heapsort {
	public int[] sortedArray;
	public int comparisons = 0;
	
	private int[] heap;
	private int endOfHeap = 0;
	
	public Heapsort(int[] unsortedArray) {
		heap = new int[unsortedArray.length];
		sortedArray = new int[unsortedArray.length];
				
		for(int i = 0; i < unsortedArray.length; i++)
			add(unsortedArray[i]);
		for(int i = 0; i < unsortedArray.length; i++)
			sortedArray[i] = remove();
	}
	
	private void add(int val) {
		heap[endOfHeap] = val;
		int newNode = endOfHeap++;

		while(newNode > 0 && heap[getParent(newNode)] > val) {
			int temp = heap[newNode];
			heap[newNode] = heap[getParent(newNode)];
			heap[getParent(newNode)] = temp;
			newNode = getParent(newNode);
		}
	}
	
	private int remove() {
		int topNode = heap[0];
		heap[0] = heap[--endOfHeap];
		int currentNode = 0;
		
		while(currentNode < endOfHeap - (endOfHeap + 1) / 2) {
			if(getLeftChild(currentNode) < endOfHeap && getRightChild(currentNode) < endOfHeap) {
				if(heap[getLeftChild(currentNode)] < heap[getRightChild(currentNode)]) {
					int temp = heap[getLeftChild(currentNode)];
					heap[getLeftChild(currentNode)] = heap[currentNode];
					heap[currentNode] = temp;
					currentNode = getLeftChild(currentNode);
				}
				else {
					int temp = heap[getRightChild(currentNode)];
					heap[getRightChild(currentNode)] = heap[currentNode];
					heap[currentNode] = temp;
					currentNode = getRightChild(currentNode);
				}
			}
			else if (getLeftChild(currentNode) < endOfHeap && heap[getLeftChild(currentNode)] < heap[currentNode]) {
				int temp = heap[getLeftChild(currentNode)];
				heap[getLeftChild(currentNode)] = heap[currentNode];
				heap[currentNode] = temp;
				currentNode = getLeftChild(currentNode);
			}
			else if (getRightChild(currentNode) < endOfHeap && heap[getRightChild(currentNode)] < heap[currentNode]) {
				int temp = heap[getRightChild(currentNode)];
				heap[getRightChild(currentNode)] = heap[currentNode];
				heap[currentNode] = temp;
				currentNode = getRightChild(currentNode);
			}
			break;
		}
		
		return topNode;
	}
	
	private int getParent(int i) {return (i - 1) / 2;}
	
	private int getLeftChild(int i) {return (2 * i) + 1;}
	
	private int getRightChild(int i) {return 2 * i + 2;}
}
