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
		
		//Node constructor with nextNode and the node's contents
		private Node(String contents, Node nextNode) {
			this.contents = contents;
			this.nextNode = nextNode;
		}
		
		//Node constructor with just the node's contents
		private Node(String contents) {
			this(contents, null);
		}
	}
	
	//Set the head of the HashList to be nothing at first
	Node head = null;
	
	public void add(String s) {
		//If there is no head, create a new head with the input item.
		comparisons++;
		if (head == null) {
			head = new Node(s); 
			return;
		}
		
		//Loop through each node, seeing whether it is the final node in the list or the contents of one of the nodes is equal to the input item.
		Node currentNode = head;
		while (currentNode.nextNode != null) {
			comparisons += 2;
			if (currentNode.contents.equals(s)) {
				currentNode.count++;
				return;
			}
			currentNode = currentNode.nextNode;
		}
		comparisons++;
		
		//At end of list. If final items's contents equal the input item, increase the count.
		comparisons++;
		if (currentNode.contents.equals(s))
			currentNode.count++;
		else //Otherwise, add the new item to the end of the list
			currentNode.nextNode = new Node(s);
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
