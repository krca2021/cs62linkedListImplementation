package linkedlist;
import java.util.Iterator;

/**
 * The DLL_Node is a super-class for nodes in a circular Doubly Linked List,
 * and implements only the most basic operations.  It is expected
 * that it will be extended to support application-specific DLLs.
 * 
 * Since all operations are implemented entirely on the DLL_Node,
 * there is no class for Lists of DLL_Nodes.
 *
 * well-formed list invariants:
 *	 1. if n1.prev == n2 then n2.next == n1
 *   2. if n1.next == n2 then n2.prev == n1
 *	 3. circularity:
 *      if there are N elements in the list 
 *      following n.next N times leads back to n
 *      following n.prev N times leads back to n
 * 
 * @author Katie Chao
 */
public class DLL_Node implements Iterable<DLL_Node> {
	private DLL_Node next;	// forward pointer ... must remain private
	private DLL_Node prev;	// backwards pointer ... must remain private

	/**
	 * New DLL_Node, not in any list
	 */
	public DLL_Node() {
		// a newly allocated DLL_Node is a list unto itself
		next = this;
		prev = this;
	}

	/**
	 * Add this node to a list AFTER the indicated insertion point
	 *
	 * @param after: node after which this is to be inserted
	 *
	 * @precondition: after is node in a well-formed list
	 * @postcondition: after.next = this, in a well-formed list
	 */
	public void insert(DLL_Node after) {
		// assign .prev for node that will follow 'this' to 'this'
		after.next.prev = this;
		// assign current node's .next pointer to val after 'after'
		this.next = after.next;
		// assign .prev pointer for current node to 'after'
		this.prev = after;
		// REassign .next pointer for 'after' to current node
		after.next = this;

	} 

	/**
	 * Remove this node from whatever list it is in
	 * 
	 * @precondition: this is an element of a well-formed list
	 * @postcondition: that list no longer contains this
	 *				   after removing an element from a list,
	 *				   its next/prev pointers should be null
	 */
	public void remove() {
		if (this.next == this) {
			this.next = null;
			this.prev = null;
		} else {
			this.prev.next = this.next;
			this.next.prev = this.prev;
			this.next = null;
			this.prev = null;
		}
	}

	/**
	 * return an iterator for the list containing this node
	 *
	 * @precondition: this node in a well-formed list
	 */
	public Iterator<DLL_Node> iterator() {
		return new DLL_Node_Iterator(this);
	}

	/**
	 * return a string reprentation of the list this node is in
	 *
	 * Note: this method will be very useful for testing,
	 *	     and is a good place to work out the circular
	 *		 list enumeration problems you will have to 
	 *		 solve in the iterator.
	 */
	public String listToString() {

		// if list is not well formed, consider it to be empty
		if (this.prev == null || this.next == null)
			return("Empty");

		String list = "";
		//	    walk the list
		//		appending comma-separated toString() of each node
		//		stopping when we come back around to our starting point.

		DLL_Node finger = this;
		Boolean flag = false;

		while(finger != this || !flag){
			flag = true;
			list += finger.toString() + ", ";
			finger = finger.next;
		}

		list = list.substring(0, list.length() - 2);
		return "[" + list + "]";
	}

	/**
	 * a sub-class that defines the iterator for this class
	 */
	 public class DLL_Node_Iterator implements Iterator<DLL_Node> {
	
		private DLL_Node head;		// start/end of this iteration
		private DLL_Node current;	// current position in the iteration

		/**
		 * instantiate an Iterator with the specified starting point
		 *
		 * @param start: starting point for the iteration
		 *
		 * @precondition: start is node in a well-formed list
		 *				  if start.next is null, it is not well-formed
		 *				  if start.prev is null, it is not well-formed
		 * @postcondition: successive calls to next will visit entire list
		 *
		 * NOTE:
		 *	This is a circular list.  We should vist every node
		 *	in the list (even a single-node-list), but should stop 
		 *	when we reach the starting point for the 2nd time.
	     */
		public DLL_Node_Iterator(DLL_Node start) {
			head = start;
			current = null;
		}

		/**
		 * returns whether or not there is another Node in the iteration
		 *
		 * @return boolean: are there more nodes to be returned
		 *
		 * DO NOT ASSUME that head is well formed (non-null pointers)
		 *		protect yourself from this error on the caller's part
		 *	    Always return false for such a list.
		 */
		public boolean hasNext() {
			if (head == null || head.next == null || head.prev == null) {
				return false;
			} else if (current == null) {
				return true;
			} else if (current.next == head) {
				return false;
			} else {
				return true;
			}
		}
		
		/**
		 * return next node in list, and advance the current pointer
		 *
		 * @return: reference to next node in list, or null after wrap-around
		 *
		 * DO NOT ASSUME that head is well formed (non-null pointers)
		 *		protect yourself from this error on the caller's part
		 *	    Always return null for such a list.
		 */

		 public DLL_Node next() {

			if (head.next == null || head.prev == null) {
				return null;
			} else {
				if (current == null) {
					current = head;
					return current;
				} else if (current.next != head) {
					current = current.next;
					return current;
				} else {
					return null;
				}
		 	}
		}
	}

	 /*
	  * Description of sugested test cases:
	  *
	  *		operations				expected iteration
	  *		----------				------------------	
	  *	    new(a)					a: [a]
	  *		new(b); b.insert(a)		a: [a,b]
	  *		b.remove()				a: [a], b: []
	  *		... add your own to fully exercise insert/remove
	  */
	  
	public static void main(String args[]) {
		DLL_Node a = new DLL_Node();
		System.out.println("a: " + a + " = " + a.listToString());

		DLL_Node b = new DLL_Node();
		System.out.println("b: " + b + " = " + b.listToString());

		b.insert(a);
		System.out.println("a + b = " + a.listToString());
		
		b.remove();
		System.out.println("[a,b] - b = " + a.listToString());
		System.out.println("        b = " + b.listToString());
	}
}