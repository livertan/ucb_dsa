package deque;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArrayDeque61B<T> implements Deque61B<T> {
    protected T[] items;
    protected int size;
    protected int nextFirst, nextLast;
    protected int First0 = 3, Last0 = 4;//initial position of first and last.

    public ArrayDeque61B () {
        items = (T[]) new Object [8];
        size = 0;
        nextFirst = First0;
        nextLast = Last0;
    }

    public void ResizingUp(int ResizeFactor) {
        T[] a ;
        a = (T[]) new Object [items.length * ResizeFactor];
        if (nextFirst + 1 <= First0 && nextFirst + 1 < nextLast - 1) {
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

    public void ResizingDown() {
        T[] b;
        b = (T[]) new Object [size];
        int j = 0;
        if (nextFirst + 1 < nextLast - 1) {
            for (int i = nextFirst + 1; i < nextLast; i++) {
                b[j] = items[i];
                j++;
            }
            nextFirst = -1;
            nextLast = b.length;
            items = b;
        } else if (nextFirst + 1 > nextLast - 1 ) {
            for (int i = nextFirst + 1; i < items.length; i++) {
                b[j] = items[i];
                j++;
            }
            for (int i = 0; i < nextLast; i++) {
                b[j] = items[i];
                j++;
            }
            nextFirst = -1;
            nextLast = b.length;
            items = b;
        }
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
        if (nextFirst + 1 < nextLast - 1) {
            for (int i = nextFirst + 1; i < nextLast; i++) {
                if (items[i] != null) {
                    temp.add(items[i]);
                }
            }
            return temp;
        } else {
            if (nextFirst + 1 > nextLast - 1) {
                for (int i = nextFirst+1; i < items.length; i++) {
                    if (items[i] != null) {
                        temp.add(items[i]);
                    }
                }
                for (int i = 0; i < nextLast; i++) {
                    if (items[i] != null) {
                        temp.add(items[i]);
                    }
                }
                return temp;
            }
        }
        temp.add(items[nextFirst+1]);
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
        if (items.length > 16 && (double) size/(double)items.length <= 0.25) {
            ResizingDown();
        }
        T temp;
        if (nextFirst + 1 > items.length - 1) {
            nextFirst = -1;
        }
        temp = items[nextFirst+1];
        items[nextFirst + 1] = null;
        nextFirst++;
        size--;
        return temp;
    }

    @Override
    public T removeLast() {
        if (items.length > 16 && (double) size/(double)items.length <= 0.25) {
            ResizingDown();
        }
        T temp;
        if (nextLast - 1 < 0) {
            nextLast = items.length;
        }
        temp = items[nextLast - 1];
        items[nextLast - 1] = null;
        nextLast--;
        size--;
        return temp;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        if (nextFirst + 1  < nextLast - 1) {
            return items[(nextFirst + 1) + index];
        } else if (nextFirst + 1 > nextLast - 1 ) {
            if (index + (nextFirst + 1) < items.length){
                return items[nextFirst + 1 + index];
            } else {
                return items[index + (nextFirst + 1)- items.length];
            }
        } else {
            return null;
        }
    }

    @Override
    public T getRecursive(int index) {
        throw new UnsupportedOperationException("No need to implement getRecursive for proj 1b");
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDeque61BIterator();
    }

    private class ArrayDeque61BIterator implements Iterator<T> {
        private int wizPos;

        public ArrayDeque61BIterator() {
            wizPos = nextFirst + 1;
        }

        @Override
        public boolean hasNext() {
            if (nextFirst + 1 < nextLast - 1) {
                return wizPos < nextLast;
            } else if (nextFirst + 1 > nextLast - 1) {
                if (wizPos >= nextFirst + 1) {
                    return true;
                } else if (wizPos < nextLast) {
                    return true;
                }
            }
            return false;
        }


        @Override
        public T next() {
            T returnItem;
            if (wizPos >= items.length) {
                wizPos = 0;
            }
            returnItem = items[wizPos];
            wizPos++;
            return returnItem;
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof ArrayDeque61B) {
            ArrayDeque61B<T> otherArray = (ArrayDeque61B<T>) other;
            if (this.size() != otherArray.size()) {
                return false;
            }
            for (T x : this) {
                if (!otherArray.contains(x)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    //@Override
    public boolean contains(T item) {
        for (T x : this) {
            if (item == x) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String output = "[";
        int i = 0;
        for (T x : this) {
            output = output + x.toString() + ",";
            i++;
            if (i == size - 1) {
                break;
            }
        }
        output = output + this.get(size - 1) + "]";
        return output;
    }
}
