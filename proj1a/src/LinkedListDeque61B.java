import java.util.ArrayList;
import java.util.List;

public class LinkedListDeque61B<T> implements Deque61B<T> {
    private IntNode sentinel, front, back;
    private int size = 0;
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
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void removeFirst() {
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size--;
    }

    @Override
    public void removeLast() {
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next =sentinel;
        size--;
    }

    @Override
    public T get(int index) {
        if (index<0 || index>=size) {
            return null;
        } else {
            IntNode node = sentinel;
            for (int i = 0; i <= index; i++) {
                node = node.next;
            }
            return node.item;
        }
    }

    @Override
    public T getRecursive(int index) {
        if (index<0 || index>=size) {
            return null;
        }
        else {
            if (index == 0) {
                return sentinel.next.item;
            } else {
                return getNode(index).item;
            }
        }
    }
    //
    public IntNode getNode(int index) {
        if (index == 0) {
            return sentinel.next;
        } else {
            return getNode(index-1).next;
        }
    }
}
