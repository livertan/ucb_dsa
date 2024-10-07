package deque;
import java.util.Comparator;
import java.util.Collections;

public class MaxArrayDeque61B<T> extends ArrayDeque61B<T>{
    private Comparator<T> newComparator;

    public MaxArrayDeque61B(Comparator<T> c) {
        super();
        newComparator = c;
    }

    public T max() {
        T returnmax = null;
        if (size == 0) {
            return null;
        } else {
            if (nextFirst + 1 < nextLast - 1) {
                for (int i = nextFirst + 1; i < nextLast - 1; i++) {
                    if (newComparator.compare(items[i], items[i+1]) <= 0) {
                        returnmax = items[i];
                    };
                }
            } else if (nextFirst + 1 > nextLast - 1) {
                for (int i = nextFirst + 1; i < items.length - 1; i++) {
                    if (newComparator.compare(items[i], items[i+1]) <= 0) {
                        returnmax = items[i];
                    };
                }
                for (int i = 0; i < nextLast; i++) {
                    if (newComparator.compare(returnmax, items[i]) <= 0) {
                        returnmax = items[i];
                    };
                }
            }

        }
       return returnmax;
    }

    public T max(Comparator<T> c) {
        return null;
    }

}
