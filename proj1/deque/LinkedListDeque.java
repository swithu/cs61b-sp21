package deque;

import java.util.Iterator;

/** Deque powered by a Doubly Linked List. */
public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    /** Class of Node, which is the element of Link List. */
    private class Node {
        public Node prev;
        public T item;
        public Node next;

        public Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    /** Fields of Linked List. */
    private static int size;

    /** The first new item will be added to SENTINEL.NEXT.
     *  SENTINEL.PREV points at the last Node.
     *  The last Node.NEXT points back at the SENTINEL Node. */
    private Node sentinel;

    /** Creates an empty Linked List. */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    public LinkedListDeque(T item) {
        sentinel = new Node(null, null, null);
        sentinel.next = new Node(sentinel, item, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    /** Add an item of type T to the front of the deque. */
    public void addFirst(T item) {
        sentinel.next = new Node(sentinel, item, sentinel.next);
        // Adds pointer from second first NODE to new first NODE
        sentinel.next.next.prev = sentinel.next;
        size++;
    }

    /** Add an item of type T to the end of the deque. */
    public void addLast(T item) {
        sentinel.prev = new Node(sentinel.prev, item, sentinel);
        // Adds pointer from second last NODE to new last NODE
        sentinel.prev.prev.next = sentinel.prev;
        size++;
    }

    /** Return TRUE if deque is empty, FALSE otherwise. */
    public boolean isEmpty() {
        return (size == 0);
    }

    /** Return the number of items in the deque. */
    public int size() {
        return size;
    }

    /** Print the items in the deque from first to last, separated by a space.
     *  Once all the items have been printed, print out a new line.
     */
    public void printDeque() {
        StringBuilder stringBuilder = new StringBuilder();
        for (T item : this) {
            stringBuilder.append(item);
            stringBuilder.append(' ');
        }
        System.out.println(stringBuilder.toString().trim());
        System.out.println(' ');
    }

    /** Remove and return the item at the front of the deque.
     *  If no such item exists, return NULL.
     */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else {
            Node firstNode = sentinel.next;
            Node secondNode = sentinel.next.next;
            T returnItem = firstNode.item;

            // Connects secondNode and sentinel
            secondNode.prev = sentinel;
            sentinel.next = secondNode;

            // Removes the pointers from firstNode
            firstNode.prev = null;
            firstNode.next = null;

            // Kills the final pointer pointing to firstNode
            firstNode = null;
            size--;
            return returnItem;
        }
    }

    /** Remove and return the item at the back of the deque.
     *  If no such item exists, return NULL.
     */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else {
            Node lastNode = sentinel.prev;
            Node secondLastNode = sentinel.prev.prev;
            T returnItem = lastNode.item;

            // Connects sentinel and secondLastNode
            sentinel.prev = secondLastNode;
            secondLastNode.next = sentinel;

            // Removes the pointers from lastNode
            lastNode.prev = null;
            lastNode.next = null;

            // Kills the final pointer pointing to lastNode
            lastNode = null;
            size--;
            return returnItem;
        }
    }

    /** Gets the item at the given index, where 0 is the front,
     *  1 is the next item and so forth.
     *  If no such item exists, return NULL.
     */
    public T get(int index) {
        // Creates a pointer pointing at sentinel
        Node pointer = sentinel;
        if (index < 0 || index >= size) {
            return null;
        }
        if (isIndexInFirstHalf(index)) {
            for (int x = 0; x < (index + 1); x++) {
                pointer = pointer.next;
            }
        } else {
            for (int x = 0; x < (size - index); x++) {
                pointer = pointer.prev;
            }
        }
        T returnItem = pointer.item;
        // Kills the pointer in case we need to remove that NODE later
        pointer = null;
        return returnItem;
    }

    /** Determines whether to retrieve an item from the front or back
     *  of the deque based on the index.
     *  If the index is less than half the size of the deque,
     *  it is more efficient to retrieve from the front.
     *  Otherwise, retrieval from the back is more efficient. */
    public boolean isIndexInFirstHalf(int index) {
        int halfSize = size / 2;
        return (index < halfSize);
    }

    /** Same as GET method, but using a recursion approach. */
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        if (isIndexInFirstHalf(index)) {
            return getRecursiveFromFront(index, sentinel.next);
        } else {
            return getRecursiveFromBack(size - index - 1, sentinel.prev);
        }
    }

    private T getRecursiveFromFront(int targetIndex, Node currentNode) {
        if (targetIndex == 0) {
            return currentNode.item;
        } else {
            return getRecursiveFromFront(targetIndex -1, currentNode.next);
        }
    }

    private T getRecursiveFromBack(int targetIndex, Node currentNode) {
        if (targetIndex == 0) {
            return currentNode.item;
        } else {
            return getRecursiveFromBack(targetIndex - 1, currentNode.prev);
        }
    }

    /** Return whether the parameter o is equal to the Deque. */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof LinkedListDeque)) {
            return false;
        }

        // Casting
        LinkedListDeque<T> other = (LinkedListDeque<T>) o;
        if (other.size() != this.size()) {
            return false;
        }

        // Checks every element is equal in same order
        Iterator<T> thisIterator = this.iterator();
        Iterator<T> otherIterator = other.iterator();

        while (thisIterator.hasNext() && otherIterator.hasNext()) {
            T thisItem = thisIterator.next();
            T otherItem = otherIterator.next();

            if (!(thisItem.equals(otherItem))) {
                return false;
            }
        }
        return true;
    }

    /** Returns an iterator. */
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private int position;
        private Node pointer;

        /** Sets a pointer that starts at the first item,
         *  counts the current position as index 0. */
        public LinkedListIterator() {
            position = 0;
            pointer = sentinel.next;
        }

        public T next() {
            T returnItem = pointer.item;
            position++;
            pointer = pointer.next;
            return returnItem;
        }

        public boolean hasNext() {
            return (position < size);
        }
    }
}
