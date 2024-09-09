package deque;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/** Test for ArrayDeque class. */
public class MyArrayDequeTest {

    /** Test addFirst using a small amount of items. */
    @Test
    public void testAddFirstSmall() {
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
    }

    /** Test addFirst using a large amount of items. */
    @Test
    public void testAddFirstLarge() {
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
    }

    /** Test addLast using a small amount of items. */
    @Test
    public void testAddLastSmall() {
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
    }


    /** Test addLast using a large amount of items. */
    @Test
    public void testAddLastLarge() {
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
    }

    // TODO: Test that Generate type of items can be added

}
