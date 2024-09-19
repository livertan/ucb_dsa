import java.util.ArrayList;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    private T[] items;
    private int size;
    private int nextFirst, nextLast;
    private int First0 = 3,Last0 = 4;//initial position of first and last.

    public ArrayDeque61B () {
        items = (T[]) new Object [8];
        size = 0;
        nextFirst = First0;
        nextLast = Last0;
    }

    public void ResizingUp(int ResizeFactor) {
        T[] a ;
        a = (T[]) new Object [items.length * ResizeFactor];
        if (nextFirst + 1 < First0 && nextFirst + 1 < nextLast - 1) {
            for (int i = nextFirst+1; i < nextLast; i++) {
                a[i] = items[i];
            }
        } else {
            if (nextFirst + 1 > First0) {
                for (int i = 0; i < nextLast; i++) {
                    a[i] = items[i];
                }
                for (int i = nextFirst + 1; i < items.length; i++) {
                    a[a.length - items.length + i] = items[i];
                }
                nextFirst = nextFirst + a.length - items.length;
            } else if (nextLast - 1 < Last0) {
                for (int i = 0; i < nextLast; i++) {
                    a[i] = items[i];
                }
                for (int i = nextFirst + 1; i < items.length; i++) {
                    a[a.length - items.length + i] = items[i];
                }
                nextFirst = nextLast - 1 + items.length;
            }
        }
        items = a;
    }

    @Override
    public void addFirst(T x) {
        if (size == items.length){
            ResizingUp(2);
        }
        if (nextFirst < 0){
            nextFirst = items.length-1;
        }
        items[nextFirst] = x;
        size++;
        nextFirst--;
    }

    @Override
    public void addLast(T x) {
        if (size == items.length){
            ResizingUp(2);
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
        if (nextFirst + 1 < First0 && nextFirst + 1 < nextLast - 1) {
            for (int i = nextFirst+1; i < nextLast; i++) {
                temp.add(items[i]);
            }
        } else {
                if (nextFirst + 1 > First0 || nextLast - 1 < Last0) {
                    for (int i = nextFirst+1; i < items.length; i++) {
                        temp.add(items[i]);
                    }
                    for (int i = 0; i < nextLast; i++) {
                        temp.add(items[i]);
                    }
            }
        }
        return temp;
    }
//
    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }
//
    @Override
    public int size() {
        return size;
    }

    @Override
    public T removeFirst() {
        T temp;
        temp = items[nextFirst+1];
        items[nextFirst + 1] = null;
        if (nextFirst + 1 > items.length - 1) {
            nextFirst = 0;
        } else {
            nextFirst++;
        }
        size--;
        return temp;
    }

    @Override
    public T removeLast() {
        T temp;
        temp = items[nextLast - 1];
        items[nextLast - 1] = null;
        if (nextLast - 1 < 0) {
            nextLast = 0;
        } else {
            nextLast--;
        }
        size--;
        return temp;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        if (nextFirst + 1 < First0 && nextFirst + 1 < nextLast - 1) {
            return items[index - (nextFirst+1)];
        } else if (nextFirst + 1 > First0 || nextLast - 1 < Last0) {
            if (index<=items.length-(nextFirst+1)){
                return items[nextFirst+1+index];
            } else {
                return items[index - (items.length-(nextFirst+1))];
            }
        } else {
            return null;
        }
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }
}
