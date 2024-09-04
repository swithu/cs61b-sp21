package deque;


import java.util.Iterator;

/** Deque powered by an Array. */
public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;

    // Size of the array itself, starting from 8
    private int capacity = 8;
    // Number of items in the array
    private int size;
    // Track the usage of the array
    private float usageFactor;

    // Tracks the positions of where to add the next item
    private int nextFirst;
    private int nextLast;

    // Tracks the positions of the front and back of an array
    private int first;
    private int last;

    // Whether it is the first time calling addFirst method
    private boolean isFirstTimeAddFirst = true;

    /** Constructor. */
    public ArrayDeque() {
        items = (T[]) new Object[capacity];
        size = 0;

        // First time using addFirst, item should be added to index 0
        nextFirst = 0;
        // First time using addNext, item should be added to index 1
        nextLast = 1;
    }

    /** For making ArrayDeque iterable. */
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

    /**
     * Adds an item of type T to position nextFirst.
     * Since here using a circular array approach, the new front
     * is looping back around to the end of the array.
     * e.g., after calling addFirst to index 0,
     * the new nextFirst becomes index 8 - 1 = 7.
     * And the next is 7 - 1 = 6, and so on.
     */
    public void addFirst(T item) {
        // TODO: add resize method

        items[nextFirst] = item;
        first = nextFirst;

        if (isFirstTimeAddFirst) {
            // Loops back around to the end of the array
            nextFirst = capacity - 1;
            isFirstTimeAddFirst = false;
        } else {
            // Already looped back around, so simply moves one index left
            nextFirst -= 1;
        }
        size += 1;
    }

    /** Adds an item of type T to the end of the deque. */
    public void addLast(T item) {
        return;
    }

    /**
     * Check if array needs to resize before adding or removing item.
     * If an array is full of items,i.e., size == capacity,
     * then it needs to expand.
     * Besides, for arrays of length 16 or more, the usage factor
     * should always be at least 25%.
     * This means that before performing a remove operation that will
     * bring the number of elements in the array under 25% the length
     * of the array, you should resize the size of the array down.
     *
     * afterSize is the size after performing a remove or add operation.
     */
    private boolean shouldResize(int afterSize) {
        usageFactor = (float) afterSizeize / capacity;
        return usageFactor < 0.25 || afterSize > capacity;
    }

    /** Resize the array to a new capacity. */
    private void resize(int newCapacity) {
        T[] newArray = (T[]) new Object[newCapacity];

        // TODO: This will work in expanding array situation, but wont in shrink situation
        System.arraycopy(items, 0, newArray, 0, size);
    }

    /** Returns TRUE if deque is empty, FALSE otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return 0;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space.
     * Once all the items have been printed, prints out a new line.
     */
    public void printDeque() {
        return;
    }

    /** Removes and returns the item at the front of the deque.
     *  If no such item exists, returns NULL. */
    public T removeFirst() {
        // TODO: If an array only contains one item, then first and last should be that same item
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
