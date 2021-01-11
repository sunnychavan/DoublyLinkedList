package linkedlist;

import java.util.NoSuchElementException;

/* Name:Tyler Carreja, Sunny Chavan
 * Netid:tcc86,sc2448
 * What I thought about this assignment: This assignment was a great introduction to the important linked list data structure and doubly linked 
 * list data structure. In doing this assignment, we felt much more at ease in applying the ideas of constant and linear time 

 *
 *
 */

/** An instance is a doubly linked list. */
public class DList<E> {

    /** Replace "-1" by the time you spent on A3 in hours.<br>
     * Example: for 3 hours 15 minutes, use 3.25<br>
     * Example: for 4 hours 30 minutes, use 4.50<br>
     * Example: for 5 hours, use 5 or 5.0 */
    public static double timeSpent= 6;

    /** First node of linked list (null if size is 0) */
    private Node head;
    /** Last node of linked list (null if size is 0) */
    private Node tail;
    /** Number of nodes in the linked list */
    private int size;

    /** Constructor: an empty linked list. */
    public DList() {} // Do not change this constructor. It is correct.

    /** = the number of values in this list. <br>
     * This function takes constant time. */
    public int size() {
        return size;
    }

    /** First value in the list. <br>
     * Throw a NoSuchElementException if list is empty. */
    public E first() {
        if (size == 0) throw new NoSuchElementException();
        return head.value;
    }

    /** = the last value of the list. <br>
     * Throw a NoSuchElementException if list is empty. */
    public E last() {
        if (size == 0) throw new NoSuchElementException();
        return tail.value;
    }

    /** = the first node of the list (null if the list is empty). */
    public Node firstNode() {
        return head;
    }

    /** = the last node of the list (null if the list is empty). */
    public Node lastNode() {
        return tail;
    }

    /** = the value of node n of this list. This function takes constant time.<br>
     * Precondition: n is a node of this list; it may not be null. */
    public E value(Node n) {
        assert n != null;
        return n.value;
    }

    /** Return a representation of this list: its values, with adjacent <br>
     * ones separated by ", ", with "[" at the beginning, and with "]" at the end. <br>
     * Note: Exactly one blank separates a ',' from the next value.<br>
     * Takes time proportional to the length of this list.<br>
     * E.g. for the list containing 4 7 8 in that order, the result is "[4, 7, 8]". <br>
     * E.g. for the list containing two empty strings, the result is "[, ]" <br>
     * E.G. for the empty list, the result is "[]". */
    @Override
    public String toString() {
        StringBuilder sb= new StringBuilder("[");
        Node n= head;
        // inv: sb contains "[" and values of nodes before node n (all of them if n = null),
        // .... with ", " after each one (except for the last value of the list).
        while (n != null) {
            sb.append(n.value);
            n= n.next;
            if (n != null) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    /** Return a representation of this list: its values in reverse, with adjacent <br>
     * ones separated by ", ", "[" at the beginning, and "]" at the end. <br>
     * Note: Exactly one blank separates a ',' from the next value.<br>
     * Takes time proportional to the length of this list. <br>
     * E.g. for the list containing 4 7 8 in that order, the result is "[8, 7, 4]". <br>
     * E.g. for the list containing two empty strings, the result is "[, ]". <br>
     * E.G. for the empty list, the result is "[]". */
    public String toStringR() { // Note:
        // TODO 1. Look at toString() to see how this method should be written. It
        // should be like that one except it processes elements in reverse order.
        // You can't do much testing of it until you have completed method append.
        // Test the two of them together, in one testing procedure.
        StringBuilder sb= new StringBuilder("[");
        Node n= tail;
        while (n != null) {
            sb.append(n.value);
            n= n.prev;
            if (n != null) sb.append(", ");

        }
        sb.append("]");
        return sb.toString();
    }

    /** add value v to the end of the list. <br>
     * This operation takes constant time. */
    public void append(E v) {
        // TODO 2. After writing this method, test this method and method toStringR
        // thoroughly before starting on the next method. These two methods must be
        // correct in order to be able to write and test all the others.

        // Need two cases: one for empty and non-empty
        // Empty: Head, Tail get new item, size += 1
        // non-empty: Tail, next2 gets new item, prev gets second to last, next gets null, size += 1

        if (size == 0) {
            Node n= new Node(null, v, null);
            head= n;
            tail= n;
            size+= 1;
        } else {
            Node n= new Node(tail, v, null);
            tail.next= n;
            tail= n;
            size+= 1;
        }

    }

    /** Add value v to the front of the list. <br>
     * This operation takes constant time. */
    public void prepend(E v) {
        // TODO 3. Write and test this method before moving on to method getNode.
        // Empty Case
        if (size == 0) {
            Node n= new Node(null, v, null);
            head= n;
            tail= n;
            size+= 1;
            // Non-Empty Case
        } else {
            Node n= new Node(null, v, head);
            head.prev= n;
            head= n;
            size+= 1;
        }
    }

    /** Return node number h: If h is 0, return first node;<br>
     * if h = 1, return second node, etc.<br>
     * Throw an IllegalArgumentException if h <0 or h >= size of list */
    protected Node getNode(int h) {
        // TODO 4. This method should take time proportional to min(h, size-h).
        // For example, if h < size/2, search from the beginning of the
        // list, otherwise search from the end of the list.
        // You will probably write two loops.
        // In testing this method, you should ensure that both loops work properly,
        // including the extreme cases when they execute 0 iterations.
        // One thing you can do in your testing program is to create a list with
        // 20 elements --use a loop to fill them with values. Then, write another
        // loop to test getNode(0) through getNode(19) all work properly.
        if (h < 0 || h >= size) throw new IllegalArgumentException();
        // Two loops: one for h < size/2 and another for h >= size/2
        if (h < size / 2) {
            Node j= firstNode();
            for (int q= 0; q < h + 1; q++ ) {
                if (q == h) { return j; }
                j= j.next;
            }
        } else {
            Node j= lastNode();
            for (int q= size - 1; q > h - 1; q-- ) {
                if (q == h) { return j; }
                j= j.prev;

            }
        }
        Node k= firstNode();
        return k;
    }

    /** Delete node n from this list. <br>
     * This operation must take constant time.<br>
     * Precondition: n must be a node of this list; it may not be null. */
    public void delete(Node n) {
        // TODO 5. Make sure this method takes constant time.
        assert n != null;
        // Three different cases: n is first, n is last and n is non-first and non-last
        if (head.equals(n) && tail.equals(n)) {
        	head = null;
        	tail = null;}
        
        else if (head.equals(n)) {
            n.next.prev= null;
            head= n.next;
            n.next= null;
        }
        
        else if (tail.equals(n)) {
            n.prev.next= null;
            tail= n.prev;
            n.prev= null;} 
   
        else   {
            n.prev.next= n.next;
            n.next.prev= n.prev;
            n.next= null;
            n.prev= null;
         
        }
        size= size - 1;

    }

    /** Insert value v in a new node after node n.<br>
     * This operation takes constant time.<br>
     * Precondition: n must be a node of this list; it may not be null. */
    public void insertAfter(E v, Node n) {
        // TODO 6. Make sure this method takes constant time.
        assert n != null;
        Node a= new Node(null, v, null);
        if (tail.equals(n)) {
            a.next= null;
            n.next= a;
            a.prev= n;
            tail= a;
        } else {
            a.next= n.next;
            n.next.prev= a;
            n.next= a;
            a.prev= n;

        }
        size+= 1;
    }

    /*********************/

    /** An instance is a node of this list. */
    class Node {
        private Node prev;   // Previous node on list (null if this is first node)
        private E value;     // The value of this element
        private Node next;   // Next node on list (null if this is last node)

        /** Constructor: an instance with previous node p (can be null), <br>
         * value v, and next node n (can be null). */
        Node(Node p, E v, Node n) {
            prev= p;
            value= v;
            next= n;
        }

        /** Return the node previous to this one (null if this is the first node of the list). */
        Node pred() {
            return prev;
        }

        /** Return the value of this node. */
        E value() {
            return value;
        }

        /** Return the next node in this list (null if this is the last node of this list). */
        Node succ() {
            return next;
        }
    }

}
