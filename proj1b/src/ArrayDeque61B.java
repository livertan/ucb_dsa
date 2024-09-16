import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] items;
    private int size;
    private int nextFirst, nextLast;
    private int First0 = 4,Last0 = 5;//initial position of first and last.

    public ArrayDeque61B () {
        items = (T[]) new Object [8];
        size = 0;
        nextFirst = First0;
        nextLast = Last0;
    }

    @Override
    public void addFirst(T x) {
        if (size == items.length){
            return;
        }
        if (nextFirst < 0){
            nextFirst=items.length-1;
        }
        items[nextFirst] = x;
        size++;
        nextFirst--;
    }

    @Override
    public void addLast(T x) {
        if (size == items.length){
            return;
        }
        if (nextLast >= items.length) {
            nextLast = 0;
        }
        items[nextLast] = x;
        size++;
        nextLast++;
    }

    @Override
    public List<T> toList() {
        List<T> temp = new ArrayList <>();
        if (nextFirst + 1 < nextLast - 1) {
            for (int i = nextFirst+1; i < nextLast; i++) {
                temp.add(items[i]);
            }
        } else {
                if (nextFirst-1 > First0) {
                    for (int i = nextFirst-1;i < items.length; i++) {
                        temp.add(items[i]);
                    }
                    for (int i = 0; i < nextLast; i++) {
                        temp.add(items[i]);
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
        if (index < 0 || index >= size) {
            return null;
        } else {
            return items[index];
        }
    }

    @Override
    public T getRecursive(int index) {
        return null;
    }
}
