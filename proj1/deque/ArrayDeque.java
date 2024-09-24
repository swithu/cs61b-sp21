package deque;

import java.util.Iterator;

/** Deque powered by an Array. */
public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;

    // Size of the array itself, starting from 8
    private int capacity = 8;
    // Number of items in the array
    private int size;

    // Tracks the positions of where to add the next item
    private int nextFirst;
    private int nextLast;

    /*
     * Tracks the front and back positions in an array.
     * Mainly for remove methods.
     */
    private int front;
    private int back;

    /** Constructor. */
    public ArrayDeque() {
        // Initial capacity of an array is 8
        items = (T[]) new Object[capacity];
        size = 0;

        /*
         * When an array is empty, the first item will be added to index 0,
         * no matter it is addFirst or addLast.
         */
        nextFirst = 0;
        nextLast = 0;
    }

    /** For making ArrayDeque iterable. */
    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    /** Iterator class for ArrayDeque. */
    private class ArrayDequeIterator implements Iterator<T> {
        private int pointer;
        private int operation; // Tracks the operation times

        ArrayDequeIterator() {
            pointer = front;
            operation = 0;
        }

        @Override
        public T next() {
            T returnItem = items[pointer];
            pointer = (pointer + 1) % capacity;
            operation += 1;
            return returnItem;
        }

        @Override
        public boolean hasNext() {
            return operation < size;
        }
    }

    /**
     * Adds an item of type T to position nextFirst.
     * Since here using a circular array approach, the new front
     * is looping back around to the end of the array.
     *
     * E.g.
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
    @Override
    public void addFirst(T item) {
        // Check whether the array needs to resize
        int afterSize = size + 1;
        if (shouldResize(afterSize)) {
            // Expands the array to double the capacity
            int newCapacity = capacity * 2;
            resize(newCapacity);
        }

        items[nextFirst] = item;
        size += 1;

        // Resets pointers
        // Code below works no matter the front has looped back around or not.
        front = nextFirst;
        // Writing (front - 1) % capacity will bug when front == 0
        nextFirst = (front - 1 + capacity) % capacity;
        back = (front + size - 1) % capacity;
        nextLast = (back + 1) % capacity;
    }

    /** Adds an item of type T to position nextLast. */
    @Override
    public void addLast(T item) {
        // Check if resize is necessary
        int afterSize = size + 1;
        if (shouldResize(afterSize)) {
            // Expands the array to double the capacity
            int newCapacity = capacity * 2;
            resize(newCapacity);
        }

        items[nextLast] = item;
        size += 1;

        // Resets pointers
        back = nextLast;
        nextLast = (back + 1) % capacity;
        front = (back - size + 1 + capacity) % capacity;
        nextFirst = (front - 1 + capacity) % capacity;
    }

    /**
     * Check if an array needs to resize before adding or removing item.
     * If an array is full of items,i.e. size == capacity,
     * then it needs to expand.
     * Besides, for arrays of length 16 or more, the usage factor
     * should always be at least 25%.
     * This means that before performing a remove operation that will
     * bring the number of elements in the array under 25% the length
     * of the array, you should resize the size of the array down.
     *
     * afterSize is the size of items after performing a remove or
     * add operation.
     *
     * afterSize > capacity may occur after an adding.
     * usageFactor < 0.25 may occur after a removing.
     */
    private boolean shouldResize(int afterSize) {
        // Track the usage of the array
        float usageFactor = (float) afterSize / capacity;
        return (capacity > 16 && usageFactor < 0.25) || afterSize > capacity;
    }

    /**
     * Resize the array to a new capacity.
     * After resizing, the front will be put at index 0 in the new array.
     */
    private void resize(int newCapacity) {
        /*
         * New capacity is like capacity * 2 or * 0.5,
         * which will be set within add or remove methods.
         */
        T[] newArray = (T[]) new Object[newCapacity];

        if (front <= back) {
            // No looping-back-around
            System.arraycopy(items, front, newArray, 0, size);
        } else {
            // Has looping-back-around
            System.arraycopy(items, front, newArray, 0, capacity - front);
            System.arraycopy(items, 0, newArray, capacity - front, back + 1);
        }

        items = newArray;
        capacity = newCapacity;

        // Resets the pointers after a resizing
        front = 0;
        back = size - 1;
        nextFirst = capacity - 1; // When front is at index 0, nextFirst loops back around
        nextLast = size;
    }

    /** Returns the number of items in the deque. */
    @Override
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from front to back, separated by a space.
     * Once all the items have been printed, prints out a new line.
     */
    @Override
    public void printDeque() {
        StringBuilder sB = new StringBuilder();

        int pointer = front;
        for (int i = 0; i < size; i++) {
            sB.append(items[pointer]);
            sB.append(' ');
            pointer = (pointer + 1) % capacity;
        }
        System.out.println(sB.toString().trim());
        System.out.println(' ');
    }

    /** Removes and returns the item at the front of the deque.
     *  If no such item exists, returns NULL. */
    @Override
    public T removeFirst() {
        int afterSize = size - 1;

        if (isEmpty()) {
            return null;
        } else if (shouldResize(afterSize)) {
            int newCapacity = capacity / 2;
            resize(newCapacity);
        }

        T removedItem = items[front];
        items[front] = null;
        size -= 1;

        // Resets pointers
        nextFirst = front;
        front = (front + 1) % capacity;
        back = (front + size - 1) % capacity;
        nextLast = (back + 1) % capacity;

        return removedItem;
    }

    /** Removes and returns the item at the back of the deque.
     *  If no such item exists, returns NULL. */
    @Override
    public T removeLast() {
        int afterSize = size - 1;

        if (isEmpty()) {
            return null;
        } else if (shouldResize(afterSize)) {
            int newCapacity = capacity / 2;
            resize(newCapacity);
        }

        T removedItem = items[back];
        items[back] = null;
        size -= 1;

        // Resets pointers
        nextLast = back;
        back = (back - 1 + capacity) % capacity;
        front = (back - size + 1 + capacity) % capacity;
        nextFirst = (front - 1 + capacity) % capacity;

        return removedItem;
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
    @Override
    public T get(int index) {
        if (index >= size || index < 0) {
            return null;
        }
        int actualIndex = (index + front) % capacity;
        return items[actualIndex];
    }

    /** Returns whether the parameter o is equal to the Deque. */
    @Override
    public boolean equals(Object o) {
        // If they are actually the same object
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Deque)) {
            return false;
        }

        // Casts the Object to Deque
        Deque<T> other = (Deque<T>) o;
        if (other.size() != this.size()) {
            return false;
        }

        // Checks every item is equal in the same order
        Iterator<T> thisIterator = this.iterator();
        Iterator<T> otherIterator = ((Iterable<T>) other).iterator();

        while (thisIterator.hasNext() && otherIterator.hasNext()) {
            T thisItem = thisIterator.next();
            T otherItem = otherIterator.next();

            // Handles null cases
            if (thisItem == null && otherItem != null) {
                return false;
            }
            if (thisItem != null && otherItem == null) {
                return false;
            }
            if (thisItem != null
                    && !(thisItem.equals(otherItem))) {
                return false;
            }
        }
        return true;
    }
}
