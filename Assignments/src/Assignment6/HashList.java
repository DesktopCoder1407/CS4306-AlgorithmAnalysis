//Name: 		Parker Smith
//Class: 		CS 4306/1
//Term: 		Spring 2022
//Instructor: 	Dr. Haddad
//Assignment: 	6
package Assignment6;

public class HashList {
	public int comparisons = 0;

	//Node class within the HashList
	private class Node{
		Node nextNode = null;
		String contents;
		int count = 1;
		
		private Node(String contents) {
			this.contents = contents;
		}
	}
	
	//Set the head and tail of the HashList to be nothing at first
	Node head = null;
	Node tail = null;
	
	public void add(String s) {
		//If there is no head, create a new head with the input item.
		comparisons++;
		if (head == null) {
			head = new Node(s); 
			tail = head;
		}
		else {  //Loop through each node, seeing whether the contents of one of the nodes is equal to the input item.
			Node currentNode = head;
			do {
				comparisons++;
				if(currentNode.contents.equals(s)) {
					currentNode.count++;
					return;
				}
				currentNode = currentNode.nextNode;
				comparisons++;
			} while(currentNode != null);
			
			//If there are no equal items, append the node to the end of the list.
			tail.nextNode = new Node(s);
			tail = tail.nextNode;
		}
	}
	
	@Override
	public String toString() {
		String returnString = "";
		
		Node currentNode = head;
		while (currentNode != null) {
			returnString += String.format("%-15s%7d\n", currentNode.contents, currentNode.count);
			currentNode = currentNode.nextNode;
		}
		
		return returnString;
	}
}
