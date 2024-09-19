package deque;

import java.util.Comparator;

/** ArrayDeque with comparator. */
public class MaxArrayDeque<T> extends ArrayDeque<T> {

    Comparator<T> thisComparator;

    /**
     * Constructor.
     * Creates a MaxArrayDeque with the given Comparator.
     */
    public MaxArrayDeque(Comparator<T> c) {
        super();
        thisComparator= c;
    }

    /**
     * Returns the maximum element in the deque as governed by the
     * Comparator that given in constructor previously,
     * i.e., thisComparator.
     * If the MaxArrayDeque is empty, returns null.
     */
    public T max() {
        /*
         * Writing maxItem = null will get into NullPointerError at
         * compare(T a, T b) {return a - b} since b is null at the
         * first place.
         * Also, we cannot write T maxItem = 0, since we don't have
         * an exact minimum start point with type T.
         */

        if (this.isEmpty()) {
            return null;
        }

        T maxItem = this.get(0); // Index 0 is where the front at
        for (T item : this) {
            // Enhanced for loop will also start at the front
            if (thisComparator.compare(item, maxItem) > 0) {
                maxItem = item;
            }
        }
        return maxItem;
    }

    /**
     * Returns the maximum element in the deque as governed by the
     * parameter Comparator c given in this method call,
     * i.e., otherComparator.
     * If the MaxArrayDeque is empty, returns null.
     */
    public T max(Comparator<T> c) {
        Comparator<T> otherComparator = c;

        T maxItem = this.get(0);
        for (T item : this) {
            if (otherComparator.compare(item, maxItem) > 0) {
                maxItem = item;
            }
        }
        return maxItem;
    }

}
