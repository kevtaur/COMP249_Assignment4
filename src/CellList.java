// -----------------------------------------------------
// Assignment 4
// Part 1: ArrayList & File I/O
// Written by: Kevin Ve 40032669
// -----------------------------------------------------

import java.util.NoSuchElementException;

/**CellList is a linkedlist specific to holding Cellphones
 * it contains an innerClass CellNode to handle individual nodes of the list
 * serialNum's of CellPhones added to the list must be unique or else it won't be added
 * @see CellPhone
 * @author KevinVe
 */
public class CellList {
	class CellNode {
		//attributes
		private CellPhone data;
		private CellNode next;
		
		/**Default Constructor creates a node with all null values
		 */
		public CellNode() {
			data = null;
			next = null;
		}
		
		/**Parameterized Constructor creates a node with given values
		 * @param data
		 * @param next
		 */
		public CellNode(CellPhone data, CellNode next) {
			this.data = data;
			this.next = next;
		}
		
		/**Copy Constructor creates a deep copy of a given node
		 * @param node
		 */
		public CellNode(CellNode node) {
			this.data = node.data;
			this.next = node.next;
		}
		
		/**clone method creates a deep copy of a given node
		 * @return new CellNode
		 */
		public CellNode clone(CellNode node) {
			return new CellNode(node.data.clone(), new CellNode(node.next));
		}
		
		public CellPhone getData() {return data;}
	}
	
	//attributes
	private CellNode head;
	private int size;
	
	/**Initializing Constructor
	 * creates an empty CellList
	 */
	public CellList(){
		head = new CellNode();
		size = 0;
	};
	
	/**Copy Constructor creates a deep copy of a given CellList retaining order
	 * @param other CellList to be copied
	 */
	public CellList(CellList other) {
		CellNode pointer = other.head.next;
		int count = 0;
		while (pointer.next != null) //for each node of other, insert to 
			this.insertAtIndex(new CellPhone(pointer.data, pointer.data.getSerialNum()), count);
		this.size = other.size;
	};
	
	public int getSize() {return size;}
	
	/**addToStart method adds a CellPhone to the beginning of the CellList. pushing all other nodes by one iteration.
	 * if list already contains the CellPhone's serialNum then it is not added to the list. serialNum must be unique
	 * @param cell CellPhone to be added to the list
	 */
	public void addToStart(CellPhone cell) {
		if (this.contains(cell.getSerialNum())) 
			System.out.println("List already contains "+cell.getSerialNum()+". Cellphone not added.");
		else {
			head.next = new CellNode(cell, head.next); //head points to new node and new node points to old head's next
			size++;
		}
	}
	
	/**insertAtIndex method adds a CellPhone to a specified index. pushing subsequent nodes by one iteration.
	 * if list already contains the CellPhone's serialNum then it is not added to the list. serialNum must be unique
	 * @param cell CellPhone to be added to the list
	 * @param index location in CellList to be inserted at
	 */
	public void insertAtIndex(CellPhone cell, int index) {
		if (isValidIndex(index)) {
			if (this.contains(cell.getSerialNum()))
				System.out.println("List already contains "+cell.getSerialNum()+". Cellphone not added.");
			else {
				CellNode pointerAtIndex = goToPointer(index); //go to index in list and insert
				pointerAtIndex.next = new CellNode(cell, pointerAtIndex.next);
				size++;
			}
		}
	}
	
	/**deleteFromIndex method deletes the node at a specified index 
	 * @param index index of CellNode to be deleted
	 */
	public void deleteFromIndex(int index) {
		if (isValidIndex(index)) {
			CellNode pointerAtIndex = goToPointer(index); //go to index and delete
			pointerAtIndex.next = pointerAtIndex.next.next;
			size--;
		}
	}
	
	/**deleteFromStart method deletes the node at the start of the CellList
	 */
	public void deleteFromStart() {
		if (size == 0)
			System.out.println("List is already empty. Nothing to delete.");
		else {
			head.next = head.next.next; //skip pointer of head
			size--;
		}
	}
	
	/**replaceAtIndex method replaces the CellPhone at the given index with another given CellPhone
	 * @param cell new CellPhone to replace old CellPhone
	 * @param index index of CellNode containing the old CellPhone
	 */
	public void replaceAtIndex(CellPhone cell, int index) {
		if (isValidIndex(index)) {
			CellNode pointerAtIndex = goToPointer(index); 
			pointerAtIndex.next.data = cell;
		}
	}
	
	/**find method searches the CellList for a CellNode that contains a CellPhone with a given serialNum
	 * returns first instance of the CellNode as serialNum is assumed to be unique
	 * this method causes a privacy leak as this method returns a reference to a node which can be used outside of this class
	 * @param serial serialNum of CellPhone to be searched for
	 * @return CellNode that contains the CellPhone with matching serialNum
	 */
	public CellNode find(Long serial) {
		if (size == 0)
			return null;
		int counter = 0;
		CellNode pointer = head.next; //loop through list until serial is found
		while(pointer != null && pointer.data.getSerialNum() != serial) { 
			pointer = pointer.next;
			counter++;
		}
		return pointer;
	}
	
	/**contains method searches the list for a CellPhone of a given serial number. True if found and false if not.
	 * @param serial serialNum of CellPhone to be searched for
	 * @return if CellList contains cellphone with serial
	 */
	public boolean contains(Long serial) {
		return (find(serial) != null);
	}
	
	/**equals method returns true if each CellNode's member's values are equal to this CellList's CellNode's member's values.
	 * order must be the equal aswell.
	 * @param obj object to be compared with
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;

		CellList otherList = (CellList) obj;
		CellNode pointer1 = head.next;
		CellNode pointer2 = otherList.head.next;
		
		while(pointer1 != null || pointer2 != null) { //check if each individual node's values are equal and in order
			if (!pointer1.data.equals(pointer2.data))
				return false;
			pointer1 = pointer1.next;
			pointer2 = pointer2.next;
		}
		
		return true;
	}
	
	/**showContents method returns a human readable string with all of this CellPhone's member attributes
	 * @return human readable string
	 */
	public String showContents() {
		String output = "";
		CellNode pointer = head;
		int x = 1;
		
		while (pointer.next != null) {
			pointer = pointer.next;
			output += pointer.data.toString() + " --> ";
			
			if (x%3 == 0)
				output += "\n";
			x++;
		}
		return output + "null\n";
	}
	
	/**goToPointer method iterates through the CellList and returns a CellNode specified by the index
	 * @param index index of CellNode to be found
	 * @return CellNode of given index
	 * @throws NoSuchElementException
	 */
	private CellNode goToPointer(int index) { //loop through list until (# of index times)
		CellNode pointer = head;
		if (isValidIndex(index)) {
			for (int i = 0; i < index; i++)
				pointer = pointer.next;
		}
		return pointer;
	}
	
	/**checks if index is valid
	 * @param index to be tested
	 * @return true if index is valid (less than size)
	 */
	private boolean isValidIndex(int index) {
		if (size < index || index < 0) {
			System.out.println(index + " is not a valid index.");
			throw new NoSuchElementException();
		}
		return true;
	}
}
