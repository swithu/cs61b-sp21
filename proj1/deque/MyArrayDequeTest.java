package deque;

import edu.princeton.cs.algs4.StdRandom;
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

    /** Tests adding, removing and resizing in randomized calls. */
    @Test
    public void randomizedTest() {
        ArrayDeque<Integer> ad1 = new ArrayDeque<>();
        List<Integer> std = new ArrayList<>(); // Sets an ArrayList as standard

        int N = 500000;
        for (int i = 0; i < N; i++) {
            int ope = StdRandom.uniform(0, 4);
            int randVal = StdRandom.uniform(0, 100);

            if (ope == 0) {
                // addFirst
                ad1.addFirst(randVal);
                std.add(0, randVal); // An ArrayList has add(0, item) to mimic addFirst
                assertEquals(ad1.size(), std.size());
                System.out.println("addFirst(" + randVal + ")");
            } else if (ope == 1) {
                // addLast
                ad1.addLast(randVal);
                std.add(randVal); // Uses add(item) to mimic addLast
                assertEquals(ad1.size(), std.size());
                System.out.println("addLast(" + randVal + ")");
            } else if (ope == 2 && !std.isEmpty()) { // Will get into NullPointerError without this [&& std.size() > 0]
                // removeFirst
                int firstInAd1 = ad1.removeFirst();
                int firstInStd = std.remove(0); // Uses remove(0) to mimic removeFirst
                assertEquals(ad1.size(), std.size());
                assertEquals(firstInAd1, firstInStd);
                System.out.println("removeFirst(" + firstInAd1 + ")");
            } else if (ope == 3 && !std.isEmpty()) { // Will get into NullPointerError without this [&& std.size() > 0]
                // removeLast
                int lastInAd1= ad1.removeLast();
                int lastInStd = std.remove(std.size() - 1); // Uses remove(size - 1) to mimic removeLast
                assertEquals(ad1.size(), std.size());
                assertEquals(lastInAd1, lastInStd);
                System.out.println("removeFirst(" + lastInAd1 + ")");
            }
        }
    }

    /** Check if you can create ArrayDeque with different parameterized types*/
    @Test
    public void multipleParamTest() {
        ArrayDeque<String> ad1 = new ArrayDeque<>();
        ArrayDeque<Double> ad2 = new ArrayDeque<>();
        ArrayDeque<Boolean> ad3 = new ArrayDeque<>();

        ad1.addFirst("string");
        ad2.addFirst(3.14159);
        ad3.addFirst(true);

        String s = ad1.removeFirst();
        double d = ad2.removeFirst();
        boolean b = ad3.removeFirst();
    }

}
