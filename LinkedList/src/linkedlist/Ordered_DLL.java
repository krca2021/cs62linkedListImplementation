package linkedlist;

/**
 * An OrderedDLL is a DLL that is guaranteed to store elements
 * in order (based on an ordinal instance variable).
 *
 * This means that an iteration can be abandonned once we encounter
 * an element with a higher ordinal than the one we are seeking.

 * additional well-formed list invariants:
 *   1. if n1.next == n2, then n2.value >= n1.value
 *   2. if n1.prev == n2, then n2.value <= n1.value
 *   3. an ordered list begins with (value 0) a header node 
 *
 * @author: YOUR NAME HERE
 */
public class Ordered_DLL extends DLL_Node {
	public int ordinal;		// positive integer, basis for ordering

	/**
	 * New Ordered_DLL Node, not in any list
	 *
	 * @param: (positive integer) sequencing value
	 */
	public Ordered_DLL(int value) {
		// super-class constructor has already been (automatically) called
		ordinal = value;
	}

	/**
	 * insert a node into the correct position of an ordered list
	 *
	 * @param head: 0-value node in the desired list
	 *
	 * @precondition: head is (0 value) node in a well-formed list
	 * @postcondition: this is properly inserted into that list
	 *				   (which might be between the last element and head)
	 */
	public void insert(Ordered_DLL head) {

		// NOTE: the prev/next fields are private to the DLL_Node
		//		 class.  We must use a DLL_Node iterator to find
		//		 our insertion point.  That iterator will not
		//		 return Ordered_DLL nodes, but rather DLL_Nodes.
		//		 Since we know that everything in our list is 
		//		 actually an instance of our sub-class, we have
		//		 to down-cast the returned DLL_Node back into
		//		 (its true) Ordered_DLL.
	}

	/**
	 * locate the first node with a specified value
	 *
	 * @param: desired (positive) sequencing value
	 * @return: node with ordinal==value, or null
	 *
	 * @precondition: this a (0 value) node in a well-formed list
	 */
	public Ordered_DLL find(int value) {
		return null;	// search list and return desired node
	}
}
