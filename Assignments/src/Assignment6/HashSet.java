package Assignment6;

public class HashSet {
	public int comparisons = 0;
	private Node[] hashTable = new Node[26];
	
	private class Node {
		Node next = null;
		String contents;
		int count = 1;
		
		private Node(String contents) {
			this.contents = contents;
		}
	}
	
	public void add(String s) {
		//Hash the new item (ignore it if it is not from lowercase a to lowercase z).
		Node currentNode;
		try {currentNode = hashTable[hashFunction(s)];}
		catch(Exception e) {return;}
		
		//If there is no head, create a new head with the input item.
		comparisons++;
		if (currentNode == null) {
			hashTable[hashFunction(s)] = new Node(s);
		}
		else {  //Loop through each node, seeing whether the contents of one of the nodes is equal to the input item.
			while (currentNode.next != null) {
				comparisons += 2;
				if(currentNode.contents.equals(s)) {
					currentNode.count++;
					return;
				}
				
				currentNode = currentNode.next;
			}
			
			comparisons += 2;
			if(currentNode.contents.equals(s))
				currentNode.count++;
			else
				//If there are no equal items, append the node to the end of the list.
				currentNode.next = new Node(s);
		}
	}
	
	private int hashFunction(String s) {
		if ((int)s.charAt(0) < 97 || (int)s.charAt(0) > 122)
			return -1;
		return (int)(s.charAt(0)) - 97; //Returns the ASCII character of the string's first character and normalizes the returned value to a number between 0 and 25 inclusive.
	}
	
	@Override
	public String toString() {
		String returnString = "";
		
		for(Node currentNode : hashTable)
		{
			while (currentNode != null) {
				returnString += String.format("%-15s%7d\n", currentNode.contents, currentNode.count);
				currentNode = currentNode.next;
			}
		}
		
		return returnString;
	}
}
