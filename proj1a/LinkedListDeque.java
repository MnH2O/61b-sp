public class LinkedListDeque<T>{
    // implementation of Tnode
    public class TNode{
        public TNode prev;
        public T item;
        public TNode next;

        public TNode(TNode p, T i, TNode n){
            prev = p;
            item = i;
            next = n;
        }
    }

    private int size;
    private TNode Sentinel;

    public LinkedListDeque(){
        size = 0;
        Sentinel = new TNode(null, null, null);
        Sentinel.next = Sentinel;
        Sentinel.prev = Sentinel;
    }

    // adds an item of type T to the front of the deque
    public void addFirst(T item){
        size += 1;
        Sentinel.next = new TNode(Sentinel, item, Sentinel.next);
        Sentinel.next.next.prev = Sentinel.next;
    }

    // adds an item of type T to the back of the deque
    public void addLast(T item){
        size += 1;
        Sentinel.prev = new TNode(Sentinel.prev, item, Sentinel);
        Sentinel.prev.prev.next = Sentinel.prev;
    }

    public boolean isEmpty(){
        if(size == 0)
            return true;
        return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
        int count = 0;
        TNode pointer = Sentinel;
        while(count < size)
        {
            pointer = pointer.next;
            System.out.print(pointer.item + " ");
            count += 1;
        }
    }

    public T removeFirst(){
        if (size == 0)
            return null;
        else {
            size -= 1;
            T value = Sentinel.next.item;
            Sentinel.next.next.prev = Sentinel;
            Sentinel.next = Sentinel.next.next;
            return value;
        }
    }

    public T removeLast(){
        if (size == 0)
            return null;
        else {
            size -= 1;
            T value = Sentinel.prev.item;
            Sentinel.prev.prev.next = Sentinel;
            Sentinel.prev = Sentinel.prev.prev;
            return value;
        }
    }

    // iteratively get the value of the node at index of index
    public T get(int index){
        if (index > size - 1)
            return null;
        else {
            TNode pointer = Sentinel.next;
            while(index > 0){
                pointer = pointer.next;
                index -= 1;
            }
            return pointer.item;
        }
    }

    // using a recursive helper function helper(int index, TNode node)
    public T getRecursive(int index){
        if (index > size - 1)
            return null;
        else {
            return helper(index, Sentinel.next);
        }
    }

    public T helper(int index, TNode node){
        if(index == 0)
            return node.item;
        else
            return helper(index - 1, node.next);
    }

}