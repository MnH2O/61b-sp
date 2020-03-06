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

    private int plusOne(int value){
        return (value + 1) % items.length;
    }

    private int minusOne(int value){
        return (value - 1 + items.length) % items.length;
    }

    private void resize(int newSize){
        T[] items_new = (T []) new Object[newSize];
        int oldIndex = plusOne(nextFirst);
        for(int newIndex = 0; newIndex < size; newIndex += 1)
        {
            items_new[newIndex] = items[oldIndex];
            oldIndex = plusOne(oldIndex);
        }
        items = items_new;
        nextFirst = newSize -1;
        nextLast = size;
    }


    public void addFirst(T item) {
        if(items.length == size) {
            resize(size * 2);
        }

        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    public void addLast(T item) {
        if(items.length == size) {
            resize(size * 2);
        }

        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size += 1;
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
        if(items.length > 16 && size * 4 < items.length)
            resize(size/2);
        nextFirst = plusOne(nextFirst);
        T value = items[nextFirst];
        items[nextFirst] = null;
        if(isEmpty() == false) {
            size -= 1;
        }
        return value;
    }

    public T removeLast() {
        if(items.length > 16 && size * 4 < items.length)
            resize(size/2);
        nextLast = minusOne(nextLast);
        T value = items[nextLast];
        items[nextLast] = null;
        if(isEmpty() == false){
            size -= 1;
        }
        return value;
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
