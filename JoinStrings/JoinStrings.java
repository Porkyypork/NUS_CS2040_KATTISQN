package JoinStrings;

import java.util.*;
import java.io.*;

class JoinStrings {
  public static void main(String[] args) {
    FastIO fio = new FastIO();
    int num = fio.nextInt();
    String[] strings = new String[num];
    for (int i = 0; i < num; i++) {
      strings[i] = fio.next();
    }
    ArrayList<TailedLinkedList> orders = new ArrayList<TailedLinkedList>(num);
    for (int i = 0; i < num; i++) {
      orders.add(new TailedLinkedList());
    }
    for (int i = 0; i < num; i++) {
      orders.get(i).addFront(i);
    }

    int num1 = 0;
    int num2 = 0;
    for (int i = 0; i < num - 1; i++) {
      num1 = fio.nextInt() - 1;
      num2 = fio.nextInt() - 1;
      orders.get(num1).insertAll(orders.get(num2));
    }
    ListNode cur = orders.get(num1).head;
    for (int i = 0; i < orders.get(num1).size(); i++) {
      fio.print(strings[cur.getItem()]);
      cur = cur.getNext();
    }
    fio.close();
  }

}

class FastIO extends PrintWriter {
  BufferedReader br;
  StringTokenizer st;

  public FastIO() {
    super(new BufferedOutputStream(System.out));
    br = new BufferedReader(new InputStreamReader(System.in));
  }

  String next() {
    while (st == null || !st.hasMoreElements()) {
      try {
        st = new StringTokenizer(br.readLine());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return st.nextToken();
  }

  int nextInt() {
    return Integer.parseInt(next());
  }

  long nextLong() {
    return Long.parseLong(next());
  }

  double nextDouble() {
    return Double.parseDouble(next());
  }

  String nextLine() {
    String str = "";
    try {
      str = br.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return str;
  }
}

class TailedLinkedList implements ListInterface {
  // attributes
  public ListNode head;
  public ListNode tail;
  public int num_nodes;

  // interface methods
  // Return true if list is empty; otherwise return false.
  public boolean isEmpty() {
    return (num_nodes == 0);
  }

  // Return number of items in list
  public int size() {
    return num_nodes;
  }

  // return index of item if item is found in the list, otherwise return -1
  public int indexOf(int item) {
    int index = 0;

    for (ListNode cur = head; cur != null; cur = cur.getNext()) {
      if (cur.getItem() == item)
        return index;
      else
        index++;
    }
    return -1;
  }

  // return true if item is in the list false otherwise
  public boolean contains(int item) {
    if (indexOf(item) != -1)
      return true;
    return false;
  }

  // get item at index
  public int getItemAtIndex(int index) {
    int counter = 0;
    int item = 0;

    if (index < 0 || index > size() - 1) {
      System.out.println("invalid index");
      System.exit(1);
    }
    if (index == size() - 1)
      item = tail.getItem();
    else {
      for (ListNode cur = head; cur != null; cur = cur.getNext()) {
        if (counter == index) {
          item = cur.getItem();
          break;
        }
        counter++;
      }
    }
    return item;
  }

  // Return first item
  public int getFirst() {
    return getItemAtIndex(0);
  }

  // Return last item
  public int getLast() {
    return getItemAtIndex(size() - 1);
  }

  // add item at position index, shifting all current items from index onwards to
  // the right by 1
  // pre: 0 <= index <= size()
  public void addAtIndex(int index, int item) {
    ListNode cur;
    ListNode newNode = new ListNode(item);

    if (index >= 0 && index <= size()) {
      if (index == 0) // insert in front
        insert(null, newNode);
      else if (index == size()) // insert at the back, don't have to move all the way to the back
        insert(tail, newNode);
      else {
        cur = getNodeAtIndex(index - 1); // access node at index-1
        insert(cur, newNode);
      }
    } else { // index out of bounds
      System.out.println("invalid index");
      System.exit(1);
    }
  }

  // Add item to front of list
  public void addFront(int item) {
    addAtIndex(0, item);
  }

  // Add item to back of list
  public void addBack(int item) {
    addAtIndex(size(), item);
  }

  // remove item at index and return it
  // pre: 0 <= index < size()
  public int removeAtIndex(int index) {
    ListNode cur;
    int item = 0;

    // index within bounds and list is not empty
    if (index >= 0 && index < size() && head != null) {
      if (index == 0) // remove 1st item
        item = remove(null);
      else {
        cur = getNodeAtIndex(index - 1); // access node at index-1
        item = remove(cur);
      }
    } else { // index out of bounds
      System.out.println("invalid index or list is empty");
      System.exit(1);
    }
    return item;
  }

  // Remove first node of list
  public int removeFront() {
    return removeAtIndex(0);
  }

  // Remove last node of list
  public int removeBack() {
    return removeAtIndex(size() - 1);
  }

  // Print values of nodes in list.
  public void print() {
    if (head == null)
      System.out.println("Nothing to print...");
    else {
      ListNode cur = head;
      System.out.print("List is: " + cur.getItem());
      for (int i = 1; i < num_nodes; i++) {
        cur = cur.getNext();
        System.out.print(", " + cur.getItem());
      }
      System.out.println(".");
    }
  }

  // non-interface helper methods
  public ListNode getHead() {
    return head;
  }

  public ListNode getTail() {
    return tail;
  }

  /* return the ListNode at index */
  public ListNode getNodeAtIndex(int index) {
    int counter = 0;
    ListNode node = null;

    if (index < 0 || index > size() - 1) {
      System.out.println("invalid index");
      System.exit(1);
    }
    if (index == size() - 1) // return tail if index == size()-1
      return tail;
    for (ListNode cur = head; cur != null; cur = cur.getNext()) {
      if (counter == index) {
        node = cur;
        break;
      }
      counter++;
    }
    return node;
  }

  // insert newNode after the node referenced by cur
  public void insert(ListNode cur, ListNode n) {
    // insert in front
    if (cur == null) {
      n.setNext(head);
      head = n; // update head
      if (tail == null) // update tail if list originally empty
        tail = head;
    } else { // insert anywhere else
      n.setNext(cur.getNext());
      cur.setNext(n);
      if (cur == tail) // update tail if inserted new last item
        tail = tail.getNext();
    }
    num_nodes++;
  }

  // remove the node referenced by cur.next, and return the item in the node
  // if cur == null, remove the first node
  public int remove(ListNode cur) {
    int value;

    if (cur == null) { // remove 1st node
      value = head.getItem();
      head = head.getNext(); // update head
      if (num_nodes == 1) // update tail to null if only item in list is removed
        tail = null;
    } else { // remove any other node
      value = cur.getNext().getItem();
      cur.setNext(cur.getNext().getNext());
      if (cur.getNext() == null) // update tail if last item is removed
        tail = cur;
    }
    num_nodes--;

    return value;
  }

  public void insertAll(TailedLinkedList temp) {
    this.tail.next = temp.head;
    this.tail = temp.tail;
    this.num_nodes += temp.num_nodes;
  }
}
