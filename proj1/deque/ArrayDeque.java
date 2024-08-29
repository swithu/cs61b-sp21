package deque;


import java.util.Iterator;

/** Deque powered by an Array. */
public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private static int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        public T next() {
            return null;
        }

        public boolean hasNext() {
            return false;
        }
    }

    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        return;
    }

    /** Adds an item of type T to the end of the deque. */
    public void addLast(T item) {
        return;
    }

    /** Returns TRUE if deque is empty, FALSE otherwise. */
    public boolean isEmpty() {
        return false;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return 0;
    }

    /** Prints the items in the deque from first to last, separated by a space.
     *  Once all the items have been printed, prints out a new line. */
    public void printDeque() {
        return;
    }

    /** Removes and returns the item at the front of the deque.
     *  If no such item exists, returns NULL. */
    public T removeFirst() {
        return null;
    }

    /** Removes and returns the item at the back of the deque.
     *  If no such item exists, returns NULL. */
    public T removeLast() {
        return null;
    }

    /** Gets the item at the given index, where 0 is the front,
     *  1 is the next item and so forth.
     *  If no such item exists, returns NULL. */
    public T get(int index) {
        return null;
    }

    /** Returns whether or not the parameter o is equal to the Deque. */
    public boolean equals(Object o) {
        return false;
    }
}
