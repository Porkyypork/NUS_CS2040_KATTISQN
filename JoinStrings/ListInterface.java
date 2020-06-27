package JoinStrings;

public interface ListInterface {

	public boolean isEmpty(); // Return true if list is empty; otherwise return false.

	public int size(); // Return number of items in the list

	// access items in the list
	public int indexOf(int item); // return index of item if item is found in the list, otherwise return -1

	public boolean contains(int item); // return true if item is in the list false otherwise

	public int getItemAtIndex(int index); // get item at index

	public int getFirst(); // get first item

	public int getLast(); // get last item

	// add items to the list
	public void addAtIndex(int index, int item); // add item at position index,
													// shifting all current items from index onwards to the right by 1
													// add item to the back if index == size()

	public void addFront(int item); // add item to front of list

	public void addBack(int item); // add item to back of list

	public void insertAll(TailedLinkedList temp);

	// remove items from the list
	public int removeAtIndex(int index); // remove item at index and return it

	public int removeFront(); // remove 1st item and return it

	public int removeBack(); // remove last item and return it

	// print out the list from index 0 to index N-1
	public void print();
}

class ListNode {
	/* attributes */
	public int item;
	public ListNode next;

	/* constructors */
	public ListNode(int val) {
		this(val, null);
	}

	public ListNode(int val, ListNode n) {
		item = val;
		next = n;
	}

	/* get the next ListNode */
	public ListNode getNext() {
		return next;
	}

	/* get the item of the ListNode */
	public int getItem() {
		return item;
	}

	/* set the item of the ListNode */
	public void setItem(int val) {
		item = val;
	}

	/* set the next reference */
	public void setNext(ListNode n) {
		next = n;
	}
}