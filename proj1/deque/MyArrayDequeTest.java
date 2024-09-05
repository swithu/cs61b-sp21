package deque;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class MyArrayDequeTest {

    /** Test ArrayDeque.addFirst in a small number of items. */
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

    /** Test ArrayDeque.addFirst in a large number of items. */
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

}
