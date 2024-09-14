import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    public IntNode sentinel, front, back;
    public int size=0;
    public LinkedListDeque61B () {
        sentinel=new IntNode (null, sentinel, sentinel);
        sentinel.prev=sentinel;
        sentinel.next=sentinel;
    }
    public class IntNode {
        public T item;
        public IntNode prev, next;
        public IntNode (T input, IntNode p, IntNode n) {
            item = input;
            prev = p;
            next = n;
        }

    }

    @Override
    public void addFirst(T x) {
        if (size == 0) {
            front = new IntNode (x, sentinel, sentinel);
            sentinel.next = front;
            sentinel.prev = front;
            size++;
        } else {
            back = sentinel.next;
            front = new IntNode (x, sentinel, back);
            sentinel.next = front;
            back.prev = front;
            size++;
        }
    }
    @Override
    public void addLast(T x) {
        if (size == 0) {
            back = new IntNode (x, sentinel, sentinel);
            sentinel.next = back;
            sentinel.prev = back;
            size++;
        } else {
            front = sentinel.prev;
            back = new IntNode (x, front, sentinel);
            front.next = back;
            sentinel.prev = back;
            size++;
        }
    }

    @Override
    public List<T> toList() {
        List <T> temp = new ArrayList();
        IntNode node = sentinel.next;
        if (node != null) {
            if (size == 0) {
                return List.of();
            } else {
                for (int i = 0; i < size; i++) {
                    temp.add (node.item);
                    node = node.next;
                }
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
