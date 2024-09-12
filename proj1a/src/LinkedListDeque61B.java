import java.util.ArrayList;
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
        if (size==0) {
            first = new IntNode (x,sentinel,null);
        } else {
            for (int i = 0; i < size; i++) {
                first = new IntNode (x, first, null);
            }
        }
        size++;
    }
    @Override
    public void addLast(T x) {
        last = new IntNode (x,last.prev,sentinel);
        size++;
    }

    @Override
    public List<T> toList() {
        List <T> temp = new ArrayList();
        IntNode node=first;
        if (size==0) {
            return List.of();
        } else {
            for (int i=0; i < size; i++) {
                temp.add(node.item);
                node=node.next;
            }
        }
        return temp;
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
