package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE

    /** Add three elements into both lists,
      * do remove three times and check outputs are the same. */
    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> constantList = new AListNoResizing<>();
        BuggyAList<Integer> buggyList = new BuggyAList<>();
        for (int i = 0; i < 3; i++) {
            constantList.addLast(i);
            buggyList.addLast(i);
        }
        for (int i = 0; i < 3; i++) {
            int a = constantList.removeLast();
            int b = buggyList.removeLast();
            assertEquals(a, b);
        }
    }

    /** Do randomize test. */
    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> BL = new BuggyAList<>();

        int N = 50000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                BL.addLast(randVal);
                assertEquals(L.size(), BL.size());
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1 && L.size() > 0) {
                // getLase
                int lastInL = L.getLast();
                int lastInBL = BL.getLast();
                assertEquals(lastInL, lastInBL);
                System.out.println("getLast(" + lastInL + ")");
            } else if (operationNumber == 2 && L.size() > 0) {
                // removeLase
                int removeLaseInL = L.removeLast();
                int removeLaseInBL = BL.removeLast();
                assertEquals(L.size(), BL.size());
                assertEquals(removeLaseInL, removeLaseInBL);
                System.out.println("removeLast(" + removeLaseInBL + ")");
            }
        }
    }
}
