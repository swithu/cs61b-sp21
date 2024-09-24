package deque;

import java.util.Iterator;

/** Interface representing a generic DEQUE. */
public interface Deque<T> {
    /** Adds an item of type T to the front of the deque. */
    void addFirst(T item);

    /** Adds an item of type T to the end of the deque. */
    void addLast(T item);

    /** Returns TRUE if deque is empty, FALSE otherwise. */
    default boolean isEmpty() {
        return size() == 0;
    }

    /** Returns the number of items in the deque. */
    int size();

    /**
     * Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, prints out a new line.
     */
    void printDeque();

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns NULL.
     */
    T removeFirst();

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns NULL.
     */
    T removeLast();

    /**
     * Gets the item at the given index, where 0 is the front,
     * 1 is the next item and so forth.
     * If no such item exists, returns NULL.
     */
    T get(int index);

    /** Returns whether the parameter o is equal to the Deque. */
    boolean equals(Object o);

    /** Returns an iterator. The Deque objects are iterable. */
    Iterator<T> iterator();
}
