public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;


    public ArrayDeque() {
        this.items = (T []) new Object[8];
        this.size = 0;
        this.nextFirst = 4;
        this.nextLast = 5;
    }

    /* we need to call this method (add one spot for the array) when nextFirst == nextLast */
    private void resizeAdd() {
        T[] items_new = (T []) new Object[items.length + 1];
        System.arraycopy(items, 0, items_new, 0, nextFirst);
        System.arraycopy(items, nextFirst, items_new, nextFirst + 1, items.length - nextFirst);
        items = items_new;
    }

    /* Whenever we remove an item, we should decrease the length of the array by 1 */
    private void resizeRemoveFirst() {
        T[] items_new = (T []) new Object[items.length - 1];
        System.arraycopy(items, 0, items_new, 0, nextFirst+1);
        System.arraycopy(items, nextFirst+2, items_new, nextFirst+1, items.length - nextFirst - 2);
        items = items_new;
    }

    private void resizeRemoveLast() {
        T[] items_new = (T []) new Object[items.length - 1];
        System.arraycopy(items, 0, items_new, 0, nextLast-1);
        System.arraycopy(items, nextLast, items_new, nextLast-1, items.length - nextLast);
        items = items_new;
    }

    public void addFirst(T item) {
        size += 1;
        if (nextFirst == -1)
            nextFirst = items.length - 1;
        if (nextFirst == nextLast) {
            resizeAdd();
            nextFirst += 1;
        }

        items[nextFirst] = item;
        nextFirst -= 1;
    }

    public void addLast(T item) {
        size += 1;
        if (nextLast == items.length)
            nextLast = 0;
        if (nextFirst == nextLast) {
            resizeAdd();
            nextFirst += 1;
        }

        items[nextLast] = item;
        nextLast += 1;
    }

    public boolean isEmpty() {
        return (size == 0);
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        for(T item: items) {
            System.out.print(item + " ");
        }
    }

    public T removeFirst() {
        if(size == 0)
            return null;
        else {
            size -= 1;
            T value = items[nextFirst + 1];
            resizeRemoveFirst();

            if (nextLast > nextFirst)
                nextLast -= 1;

            return value;
        }
    }

    public T removeLast() {
        if (size == 0)
            return null;
        else {
            size -= 1;
            T value = items[nextLast - 1];
            resizeRemoveLast();

            nextLast -= 1;
            if (nextFirst >= nextLast)
                nextFirst -= 1;

            return value;
        }
    }

    public T get(int index) {
        int first = nextFirst + 1;
        if ((size == 0) | (index >= items.length)) {
            return null;
        }
        else if (first + index < items.length){
            return items[first + index];
        } else {
            return items[first + index - items.length];
        }
    }

//    public static void main(String[] args) {
//        ArrayDeque<Integer> x = new ArrayDeque<>();
//    }

}
