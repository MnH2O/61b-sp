public interface Deque<T> {
    // adds an item of type T to the front of the deque
    void addFirst(T item);

    // adds an item of type T to the back of the deque
    void addLast(T item);

    // returns true if deque is empty, false otherwise
    boolean isEmpty();

    // returns the number of items in the deque
    int size();

    // prints the items in the deque from first to last, separated by a space
    void printDeque();

    // removes and returns the item at the front of the deque, if no such item exists, returns null
    T removeFirst();

    // removes and returns the item at the back of the deque, if no such item exists, returns null
    T removeLast();

    // gets the item at the given index
    T get(int index);
}
