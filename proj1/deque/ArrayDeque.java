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

    /*
     * Tracks the positions of the front and back of an array.
     * Mainly for remove methods.
     */
    private int front;
    private int back;

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
     *
     * e.g.
     * array: [a, b, c]
     * index: 0  1  2  3  4  5  6  7
     * items: a  b  c
     *      front  back
     *
     * addFirst(d)
     * array: [d, a, b, c]
     * index: 0  1  2  3  4  5  6  7
     * items: a  b  c              d
     *            back           front
     *
     * addFirst(e)
     * array: [e, d, a, b, c]
     * index: 0  1  2  3  4  5  6  7
     * items: a  b  c           e  d
     *            back        front
     */
    public void addFirst(T item) {
        int newSize = size + 1;
        if (shouldResize(newSize)) {
            // Expands the array to double the capacity
            int newCapacity = capacity * 2;
            resize(newCapacity);

            // Resets the pointers after a resizing
            nextFirst = newCapacity - 1;
            nextLast = size;
            front = 0;
            back = size - 1;
        }
        items[nextFirst] = item;
        front = nextFirst;

        // First time calling addFirst will make front at index 0
        if (front == 0) {
            // nextFirst should loop back around to the end of the array
            nextFirst = capacity - 1;
        } else {
            /*
             * After first time calling addFirst,
             * the nextFirst already looped back around,
             * so simply moves it one index left.
             */
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
     * If an array is full of items,i.e. size == capacity,
     * then it needs to expand.
     * Besides, for arrays of length 16 or more, the usage factor
     * should always be at least 25%.
     * This means that before performing a remove operation that will
     * bring the number of elements in the array under 25% the length
     * of the array, you should resize the size of the array down.
     *
     * newSize is the size of items after performing a remove or
     * add operation.
     *
     * newSize > capacity may occur after an adding.
     * usageFactor < 0.25 may occur after a removing.
     */
    private boolean shouldResize(int newSize) {
        usageFactor = (float) newSize / capacity;
        return (capacity > 16 && usageFactor < 0.25) || newSize > capacity;
    }

    /** Resize the array to a new capacity. */
    private void resize(int newCapacity) {
        /*
         * New capacity is like capacity * 2 or * 0.5,
         * which will be set within add or remove methods.
         */
        T[] newArray = (T[]) new Object[newCapacity];

        // TODO: Should i check isEmpty here?
        /*
         * Front is at 0 if no addFirst has been called, i.e. only addLast.
         * e.g.
         * array: [a, b, c ,d, e]
         * index: 0  1  2  3  4  5  6  7
         * items: a  b  c  d  e
         *      front        back
         */
        if (front == 0) {
            System.arraycopy(items, front, newArray, 0, size - 1);
        } else {
            /*
             * addFirst has been called.
             * e.g.
             * array: [d, c, a, b, e]
             * index: 0  1  2  3  4  5  6  7
             * items: a  b  e           d  c
             *             back       front
             */
            System.arraycopy(items, front, newArray, 0, capacity - front);
            System.arraycopy(items, 0, newArray, capacity - front, back + 1);
        }
        items = newArray;
        capacity = newCapacity;
    }

    /** Returns TRUE if deque is empty, FALSE otherwise. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Returns the number of items in the deque. */
    public int size() {
        return size;
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
        // Need to check is not empty first, and use front and back to remove

        return null;
    }

    /** Removes and returns the item at the back of the deque.
     *  If no such item exists, returns NULL. */
    public T removeLast() {
        return null;
    }

    /**
     * Gets the item at the given index, where 0 is the front,
     * 1 is the next item and so forth.
     * If no such item exists, returns NULL.
     *
     * e.g.
     * array: [d, c, a, b]
     *
     * calling index: 0  1  2  3
     *                d  c  a  b
     *
     * actual index: 0  1  2  3  4  5  6  7
     *               a  b              d  c
     *                 back          front
     *
     * convert algorithm:
     * actual index = (calling index + front) % capacity
     * e.g. when calling item c: 7 = (1 + 6) % 8
     */
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        int actualIndex = (index + front) % capacity;
        return items[actualIndex];
    }

    /** Returns whether or not the parameter o is equal to the Deque. */
    public boolean equals(Object o) {
        return false;
    }
}
