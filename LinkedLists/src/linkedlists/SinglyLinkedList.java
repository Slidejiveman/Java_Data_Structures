package linkedlists;

/**
 * A simple implementation of a SinglyLinkedList.
 * 
 * TODO: Find snazzier implementations/optimizations.
 *       Add equals and clone methods
 *       
 * @author HumanD1ffM4chin3
 * @param <E>: Handles generic types
 */
public class SinglyLinkedList<E> implements Cloneable {
    /** Nested inner Node class */
	private static class Node<E> {
		private E element;          // reference to element stored at this node
		private Node<E> next;       // reference to next Node in list
		public Node(E e, Node<E> n) {
			element = e;
			next = n;
		}
		
		/** Fetch next element in the list */
		public E getElement() {
			return element;
		}
		/** fetch next node */
		public Node<E> getNext() {
			return next;
		}
		/** set the next node in the list */
		public void setNext(Node<E> n) {
			next = n;
		}
	} // End of nested inner node class
	
	// instance variables of SinglyLinkedList object
	private Node<E> head = null;    // first node in list or null if empty
	private Node<E> tail = null;    // last node in list or null if empty
	private int size = 0;           // number of nodes
	
	/** Default Constructor creates an initially empty List */
	public SinglyLinkedList() {}
	
	// access methods
	/** Fetch the size of the list */
	public int size() {
		return size;
	}
	
	/** determine whether or not the list is empty */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/** fetch, but do not remove, first element in list */
	public E first() {
		if (isEmpty()) {
			return null;
		} else {
			return head.getElement();
		}
	}
	
	/** fetch, but do not remove, last element in list */
	public E last() {
		if (isEmpty()) {
			return null;
		} else {
			return tail.getElement();
		}
	}
	
	// update methods
	
	/** Adds element to the front of the list */
	public void addFirst(E e) {
		head = new Node<>(e, head); // create and link new node
		if (size == 0) {
			tail = head;            // in this special case tail is also head
		}
		size++;
	}
	
	/** Adds element to the back of the list */
	public void addLast(E e) {
		Node<E> newest = new Node<>(e, null); // node will eventually be tail
		if (isEmpty()) {
			head = newest;   // special case if the list was already empty
		} else {
			tail.setNext(newest); // add newest after the tail
		}
		tail = newest;            // update tail reference to new tail
		size++;
	}
	
	/** removes and returns the element at the head */
	public E removeFirst() {
		if (isEmpty()) {
			return null;
		} 
		E answer = head.getElement();
		head = head.getNext();     // Becomes null if only one node before
		size--;
		if (size == 0) {           // Special case if list becomes empty
			tail = null;
		}
		return answer;
	} 
	
	// equals and clone methods
	/** Equals method currently assumes that the list must be of the same
	 *  type in order to be equal. This means that a singly linked list will
	 *  not be equal to a circularly linked list or a doubly linked list even
	 *  if the elements are identical. Because of type erasure in Java, we have
	 *  to use Objects and casts to handle any type rather than generics. */
	@SuppressWarnings({ "rawtypes" })
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		
		// at this point, the classes have to be the same.
		if (getClass() != o.getClass()) {
			return false;
		}
		
		SinglyLinkedList other = (SinglyLinkedList) o;  // use non-parameterized type (erasure)
		
		// the size must be the same for them to be equal
		if (size != other.size) {
			return false;
		}
		
		Node walkA = head;                             // traverse primary list
		Node walkB = other.head;                       // traverse secondary list
		
		while (walkA != null) {
			if (!walkA.getElement().equals(walkB.getElement())) {
				return false; // mismatch
			}
			walkA = walkA.getNext();
			walkB = walkB.getNext();
		}
		return true;             // if we reach this, then they are equal.
	}
	
	/** The clone method that performs a deep clone of the list. */
	@SuppressWarnings("unchecked")
	public SinglyLinkedList<E> clone() throws CloneNotSupportedException {
		// always use inherited Object.clone() to create initial copy
		SinglyLinkedList<E> other = (SinglyLinkedList<E>) super.clone(); // safe cast
		if (size > 0) {                      // we need independent node chain
			other.head = new Node<>(head.getElement(), null);
			Node<E> walk = head.getNext();   // walk through remainder of original list
		    Node<E> otherTail = other.head;  // remember most recently created node
		    while (walk != null) {           // make new node storing same element
		    	Node<E> newest = new Node<>(walk.getElement(), null);
		    	otherTail.setNext(newest);   // link previous node to this one
		    	otherTail = newest;
		    	walk = walk.getNext();
		    }
		}
		return other;
	}
	
	/** Test driver for the singly linked list class */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void main(String args[]) {
		SinglyLinkedList theList = new SinglyLinkedList();
		SinglyLinkedList clonedList;
		theList.addFirst(1);
		theList.addFirst(2);
		theList.addLast(3);
		try {
			clonedList = theList.clone();
			System.out.println("Original List values");
			while(theList.first() != null) {
				System.out.println(theList.removeFirst());
			}
			
			System.out.println("Cloned List values");
			while(clonedList.first() != null) {
				System.out.println(clonedList.removeFirst());
			}
			System.out.println(theList.equals(clonedList));
		} catch (CloneNotSupportedException e) {
			System.err.println("I AM ERROR: List didn't clone.");
			e.printStackTrace();
		}
		
	}
} //------------end of singly linked list class------------