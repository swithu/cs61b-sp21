package deque;

import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the LinkedListDeque class. */
public class MyLinkedListDequeTest {
    /** Tests the LinkedListDeque.addFirst method. */
    @Test
    public void testAddFirst() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addFirst(99);
        // Check if the item was added
        assertFalse("Item hasn't been added", deque.isEmpty());
        // Check if the added item is indeed the first item
        int firstItem = deque.get(0);
        assertEquals("Added item is not at the first", 99, firstItem);
        // Check the size
        assertEquals(1, deque.size());
    }

    /** Tests the LinkedListDeque.addLast method. */
    @Test
    public void testAddLast() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addLast(99);
        // Check if the item was added
        assertFalse("Item hasn't been added", deque.isEmpty());
        // Check if the added item is indeed the first item
        int lastItem = deque.get(0);
        assertEquals("Added item is not at the last", 99, lastItem);
        // Check the size
        assertEquals(1, deque.size());
    }

    /** Tests the LinkedListDeque.printDeque method. */
    @Test
    public void testPrintDeque() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        deque.addFirst(2);
        deque.addFirst(1);
        deque.addLast(3);
        deque.addLast(4);
        deque.addLast(5);
        deque.printDeque();
        System.out.println("end");
    }

    /** Tests the LinkedListDeque.remove methods. */
    @Test
    public void testRemove() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        for (int i = 1; i < 6; i++) {
            deque.addFirst(i);
        }
        deque.printDeque();
        int firstItem = deque.removeFirst();
        int lastItem = deque.removeLast();
        assertEquals(5, firstItem);
        assertEquals(1, lastItem);
        deque.printDeque();
        System.out.println(deque.get(2));
    }

    /** Tests the LinkedListDeque.get method. */
    @Test
    public void testGet() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        for (int i = 0; i < 100; i++) {
            deque.addLast(i);
        }
        deque.printDeque();
        assertEquals(77, (long) deque.get(77));
        assertEquals(50, (long) deque.get(50));
    }

    /** Tests the LinkedListDeque.getRecursive method. */
    @Test
    public void testGetRecursive() {
        LinkedListDeque<Integer> deque = new LinkedListDeque<>();
        for (int i = 0; i < 100; i++) {
            deque.addLast(i);
        }
        deque.printDeque();
        assertEquals(77, (long) deque.getRecursive(77));
        assertEquals(50, (long) deque.getRecursive(50));
    }

}
