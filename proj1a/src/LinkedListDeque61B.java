import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private IntNode sentinel, first, last;
    private int size=0;
    public LinkedListDeque61B () {
        for (int i=0; i<size; i++) {
            sentinel=sentinel.next;
            sentinel=sentinel.prev;
        }
    }
    public class IntNode {
        public T item;
        public IntNode prev,next;
        public IntNode (T input, IntNode p, IntNode n) {
            item = input;
            prev = p;
            next = n;
        }

    }

    @Override
    public void addFirst(T x) {
        first = new IntNode (x,sentinel,null);
    }

    @Override
    public void addLast(T x) {
        last = new IntNode (x,last.prev,sentinel);
    }

    @Override
    public List<T> toList() {
        return List.of();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public T removeFirst() {
        return null;
    }

    @Override
    public T removeLast() {
        return null;
    }

    @Override
    public T get(int index) {
        return null;
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
