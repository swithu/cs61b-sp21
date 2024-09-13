package deque;

import java.util.Comparator;

/** ArrayDeque with comparator. */
public class MaxArrayDeque<T> extends ArrayDeque<T> {

    Comparator<T> comparator;

    /**
     * Constructor.
     * Creates a MaxArrayDeque with the given Comparator.
     */
    public MaxArrayDeque(Comparator<T> c) {
        super();
        comparator = c;
    }

    /**
     * Returns the maximum element in the deque as governed by the
     * Comparator that given in constructor previously.
     * If the MaxArrayDeque is empty, returns null.
     */
    public T max() {
        return null;
    }

    /**
     * Returns the maximum element in the deque as governed by the
     * parameter Comparator c given in this method call.
     * If the MaxArrayDeque is empty, returns null.
     */
    public T max(Comparator<T> c) {
        return null;
    }




}
