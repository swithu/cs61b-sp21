package deque;

import org.checkerframework.checker.units.qual.A;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/** Test for ArrayDeque class. */
public class MyArrayDequeTest {

    /** Test addFirst using a small amount of items. */
    @Test
    public void testAddFirstSmallTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        for (int i = 0; i < 5; i++) {
            ad1.addFirst(i);
        }

        List<Integer> ad2 = new ArrayList<>();
        for (int i = 4; i >= 0; i--) {
            ad2.add(i);
        }

        for (int i = 0; i < 5; i++) {
            assertEquals(ad1.get(i), ad2.get(i));
        }

        ad1.printDeque();
    }

    /** Test addFirst using a large amount of items. */
    @Test
    public void testAddFirstLargeTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        for (int i = 0; i < 10000; i++) {
            ad1.addFirst(i);
        }

        List<Integer> ad2 = new ArrayList<>();
        for (int i = 9999; i >= 0; i--) {
            ad2.add(i);
        }

        for (int i = 0; i < 10000; i++) {
            assertEquals(ad1.get(i), ad2.get(i));
        }

        ad1.printDeque();
    }

    /** Test addLast using a small amount of items. */
    @Test
    public void testAddLastSmallTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        for (int i = 0; i < 5; i++) {
            ad1.addLast(i);
        }

        List<Integer> ad2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ad2.add(i);
        }

        for (int i = 0; i < 5; i++) {
            assertEquals(ad1.get(i), ad2.get(i));
        }

        ad1.printDeque();
    }


    /** Test addLast using a large amount of items. */
    @Test
    public void testAddLastLargeTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        for (int i = 0; i < 10000; i++) {
            ad1.addLast(i);
        }

        List<Integer> ad2 = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            ad2.add(i);
        }

        for (int i = 0; i < 10000; i++) {
            assertEquals(ad1.get(i), ad2.get(i));
        }

        ad1.printDeque();
    }

    /** adds a few things to array, checking isEmpty() and size() are correct. */
    @Test
    public void addIsEmptySizeTest() {
        ArrayDeque<String> ad1 = new ArrayDeque<>();

        assertTrue(ad1.isEmpty());

        ad1.addFirst("a");
        assertEquals(1, ad1.size());
        assertFalse(ad1.isEmpty());

        ad1.addLast("b");
        assertEquals(2, ad1.size());

        ad1.addLast("c");
        assertEquals(3, ad1.size());

        ad1.printDeque();
    }

    /** Tests adding and removing. */
    @Test
    public void addRemoveTest() {
        ArrayDeque<String> ad1 = new ArrayDeque<>();
        ad1.addLast("c");
        ad1.addFirst("b");
        ad1.addFirst("a");
        ad1.addLast("d");
        ad1.printDeque();
        assertEquals(4, ad1.size());

        assertEquals("a", ad1.removeFirst());
        assertEquals(3, ad1.size());

        assertEquals("d", ad1.removeLast());
        assertEquals(2, ad1.size());

        assertEquals("b", ad1.removeFirst());
        assertEquals(1, ad1.size());

        assertEquals("c", ad1.removeFirst());
        assertEquals(0, ad1.size());

        ad1.printDeque();
        System.out.println("end");
    }

    /** Tests removing from an empty array. */
    @Test
    public void removeEmptyTest() {
        ArrayDeque<String> ad1 = new ArrayDeque<>();
        ad1.addLast("c");
        ad1.addFirst("b");

        ad1.removeFirst();
        assertFalse(ad1.isEmpty());

        ad1.removeLast();
        assertTrue(ad1.isEmpty());

        assertNull(ad1.removeFirst());
        assertNull(ad1.removeLast());
    }

    /** Tests resizing after performing remove. */
    @Test
    public void removeResizeTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        for (int i = 0; i < 40; i++) {
            ad1.addLast(i);
        }

        for (int i = 0; i < 5; i++) {
            ad1.addFirst(i);
        }

        for (int i = 0; i < 20; i++) {
            ad1.removeLast();
        }

        for (int i = 0; i < 20; i++) {
            ad1.removeFirst();
        }
    }



    // TODO: Test that Generate type of items can be added

}
