package linkedlists;

/**
 * Circularly linked lists have a tail that points back to the head.
 * 
 * TODO: Think about methods that could be added to the linked lists
 *       in order to make them more extensible.
 *       
 * @author HumanD1ffM4chin3
 *
 * @param <E>: handles generic types
 */
public class  CircularlyLinkedList<E> implements Cloneable {
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
	
	// instance variables of the CircularlyLinkedList
	private Node<E> tail = null;     // we store the tail, but not the head
	private int size = 0;
	
	/** Constructor creates initially empty list */
	public CircularlyLinkedList() {}
	
	//access methods
	
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
			return tail.getNext().getElement(); // Have to traverse to the head
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
	
	/** Rotate the first element to the back of the list */
	public void rotate() {
		if (tail != null) {
			tail = tail.getNext(); // Old head becomes new tail
		}
	}
	
	/** Adds element to the front of the list */
	public void addFirst(E e) {
		if (size == 0) {
			tail = new Node<>(e, null);
			tail.setNext(tail);  // circularly link to itself
		} else{
			Node<E> newest = new Node<>(e, tail.getNext());
			tail.setNext(newest);
		}
		size ++;
	}
	
	/** Adds element to the back of the list */
	public void addLast(E e) {
		addFirst(e);            // insert new element at the front of the list
		tail = tail.getNext();  // now new element becomes the tail.
	}
	
	/** removes and returns the element at the head */
	public E removeFirst() {
		if (isEmpty()) {
			return null;
		} 
		Node<E> head = tail.getNext();
		if (head == tail) {
			tail = null;   // only node left needs to be removed
		} else {
			tail.setNext(head.getNext()); // removes "head" from the list
		}
		size--;
		return head.getElement();         // return the element from the head that will be removed
	}
	
	// equals and clone methods
		/** Equals method currently assumes that the list must be of the same
		 *  type in order to be equal. This means that a circularly linked list will
		 *  not be equal to a singly linked list or a doubly linked list even
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
			
			CircularlyLinkedList other = (CircularlyLinkedList) o;  // use non-parameterized type (erasure)
			
			// the size must be the same for them to be equal
			if (size != other.size) {
				return false;
			}
			
			Node walkA = tail;                             // traverse primary list
			Node walkB = other.tail;                       // traverse secondary list
			
			// Have to change the loop to accomodate something that will never point
			// to null. A for loop should suffice.
			for(int i = 0; i < size; i++) {
				if (!walkA.getElement().equals(walkB.getElement())) {
					return false; // mismatch
				}
				walkA = walkA.getNext();
				walkB = walkB.getNext();
			}
			return true;             // if we reach this, then they are equal.
		}
		
		/** The clone method that performs a deep clone of the list */
		@SuppressWarnings("unchecked")
		public CircularlyLinkedList<E> clone() throws CloneNotSupportedException {
			// always use inherited Object.clone() to create initial copy
			CircularlyLinkedList<E> other = (CircularlyLinkedList<E>) super.clone(); // safe cast
			if (size > 0) {                      // we need independent node chain
				other.tail = new Node<>(tail.getElement(), null);
				Node<E> walk = tail.getNext();   // walk through remainder of original list
			    Node<E> otherTail = other.tail;  // remember most recently created node
			    for (int i = 0; i < size; i++) {           // make new node storing same element
			    	Node<E> newest = new Node<>(walk.getElement(), null);
			    	otherTail.setNext(newest);   // link previous node to this one
			    	otherTail = newest;
			    	walk = walk.getNext();
			    }
			}
			return other;
		}
		
		/** Test driver for the circularly linked list class */
		@SuppressWarnings({ "rawtypes", "unchecked" })
		public static void main(String args[]) {
			CircularlyLinkedList theList = new CircularlyLinkedList();
			CircularlyLinkedList clonedList;
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
} //------------end of circularly linked list class------------
