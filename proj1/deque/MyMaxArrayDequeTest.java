package deque;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Comparator;

/** Tests for MaxArrayDeque. */
public class MyMaxArrayDequeTest {

    /** Comparator for int. */
    private static class IntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer a, Integer b) {
            return a - b;
        }
    }

    public static Comparator<Integer> getIntComparator() {
        return new IntComparator();
    }

    /** Tests max() in an int array. */
    @Test
    public void intArrayMaxTest() {
        Comparator<Integer> intComparator = getIntComparator();
        MaxArrayDeque<Integer> ad1 = new MaxArrayDeque<>(intComparator);
        for (int i = 0; i < 100; i = i + 2) {
            ad1.addLast(i);
        }
        ad1.printDeque();

        int maxItem = ad1.max();
        assertEquals(98, maxItem);
    }

}
