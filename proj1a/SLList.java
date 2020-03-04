public class SLList{
    public class IntNode{
        public int item;
        public IntNode next;
        public IntNode(int i, IntNode n){
            item = i;
            next = n;
        }
    }

    private int size = 0;
    public IntNode sentinel = new IntNode(100, null);

    public SLList(int x){
        sentinel.next = new IntNode(x, null);
    }
}