package linkedlist;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

public class DListTest {

    @Test
    void test_toStringR() {
        DList<Integer> D= new DList<>();
        assertEquals("[]", D.toString());
        assertEquals("[]", D.toStringR());
        assertEquals(0, D.size());

    }

    @Test
    void testAppendAndToStringR() {
        DList<String> ll= new DList<>();
        // Test Empty String
        ll.append("CS2110");
        assertEquals("[CS2110]", ll.toStringR());
        assertEquals("[CS2110]", ll.toString());
        assertEquals(1, ll.size());

        assertEquals("CS2110", ll.first());
        assertEquals("CS2110", ll.last());

        // Test Non-Empty String
        ll.append("CS2800 is hard");
        assertEquals("[CS2800 is hard, CS2110]", ll.toStringR());
        assertEquals("[CS2110, CS2800 is hard]", ll.toString());
        assertEquals(2, ll.size());

        assertEquals("CS2110", ll.first());
        assertEquals("CS2800 is hard", ll.last());
        assertEquals("CS2800 is hard", ll.firstNode().succ().value());
        assertEquals("CS2110", ll.lastNode().pred().value());
        assertEquals(null, ll.lastNode().succ());
        assertEquals(null, ll.firstNode().pred());

    }

    @Test
    void testprepend() {
        DList<String> ll= new DList<>();
        // Test Empty String
        ll.prepend("CS2110");
        assertEquals("[CS2110]", ll.toStringR());
        assertEquals("[CS2110]", ll.toString());
        assertEquals(1, ll.size());

        assertEquals("CS2110", ll.first());
        assertEquals("CS2110", ll.last());
        assertEquals(null, ll.firstNode().pred());
        assertEquals(null, ll.firstNode().succ());

        // when list isn't empty
        ll.prepend("CS2800 is hard");
        assertEquals("[CS2110, CS2800 is hard]", ll.toStringR());
        assertEquals("[CS2800 is hard, CS2110]", ll.toString());
        assertEquals(2, ll.size());

        assertEquals("CS2800 is hard", ll.first());
        assertEquals("CS2110", ll.last());
        assertEquals("CS2110", ll.firstNode().succ().value());
        assertEquals("CS2800 is hard", ll.lastNode().pred().value());
        assertEquals(null, ll.lastNode().succ());
        assertEquals(null, ll.firstNode().pred());

    }

    @Test
    void testGetNode() {
        // Test Non-Empty doubly linked list
        DList<Integer> ll= new DList<>();

        for (int j= 0; j < 20; j++ ) {
            ll.append(j);
        }

        for (int k= 0; k < 20; k++ ) {
            assertEquals(k, ll.getNode(k).value());
        }
    }

    @Test
    void testDelete() {
        DList<String> ll= new DList<>();
        ll.append("CS2110");
        ll.append("testing");
        ll.append("how much longer");
        ll.append("I don't know");
        // Delete Head of List
        ll.delete(ll.firstNode());
        assertEquals("[testing, how much longer, I don't know]", ll.toString());
        assertEquals("[I don't know, how much longer, testing]", ll.toStringR());
        assertEquals(3, ll.size());

        assertEquals("testing", ll.first());
        assertEquals("I don't know", ll.last());

        // Delete middle of list
        ll.prepend("CS2110");
        ll.delete(ll.getNode(2));
        assertEquals("[CS2110, testing, I don't know]", ll.toString());
        assertEquals("[I don't know, testing, CS2110]", ll.toStringR());
        assertEquals(3, ll.size());

        // Delete Tail of list
        DList<String> l2= new DList<>();
        l2.append("CS2110");
        l2.append("testing");
        l2.append("how much longer");
        l2.append("I don't know");
        l2.delete(l2.lastNode());
        assertEquals("[CS2110, testing, how much longer]", l2.toString());
        assertEquals("[how much longer, testing, CS2110]", l2.toStringR());
        assertEquals(3, l2.size());
        assertEquals("CS2110", l2.first());
        assertEquals("how much longer", l2.last());

        // Delete only object in list
        DList<String> l3= new DList<>();
        l3.append("CS2110");
        l3.delete(l3.firstNode());
        assertEquals("[]", l3.toString());
        assertEquals("[]", l3.toStringR());
        assertEquals(0, l3.size());
        assertEquals(null, l3.firstNode());
        assertEquals(null, l3.lastNode());

        // Test assert statement
        assertThrows(AssertionError.class, () -> { ll.delete(null); });

    }

    @Test
    void testinsertAfter() {
        DList<String> ll= new DList<>();
        ll.append("testing");
        ll.append("how much longer");
        ll.append("I don't know");

        // insert after middle node
        ll.insertAfter("can the pain go on", ll.getNode(1));
        assertEquals("[testing, how much longer, can the pain go on, I don't know]",
            ll.toString());
        assertEquals("[I don't know, can the pain go on, how much longer, testing]",
            ll.toStringR());
        assertEquals("testing", ll.first());
        assertEquals("I don't know", ll.last());
        assertEquals(4, ll.size());

        // insert after last node
        ll.insertAfter("done", ll.lastNode());
        assertEquals("[testing, how much longer, can the pain go on, I don't know, done]",
            ll.toString());
        assertEquals("[done, I don't know, can the pain go on, how much longer, testing]",
            ll.toStringR());
        assertEquals("testing", ll.first());
        assertEquals("done", ll.last());
        assertEquals(5, ll.size());
        // Test Assert Statement
        assertThrows(AssertionError.class, () -> { ll.delete(null); });
    }
}