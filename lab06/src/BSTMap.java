import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private BSTMap T, left, right;
    private K key;
    private V value;
    private int count = 0;

    public BSTMap(K idk, V val, BSTMap idleft, BSTMap idright) {
        this.key = idk;
        this.value = val;
        this.left = idleft;
        this.right = idright;
    }

    public BSTMap(K idk, V val) {
        this.key = idk;
        this.value = val;
    }

    public BSTMap() {
        this.key = null;
        this.value = null;
    }

    @Override
    public void put(K idk, V val) {
        T = tput(idk, val, T);
        count++;
    }

    private BSTMap tput(K idk, V val, BSTMap newT) {
        if (newT == null) {
            return new BSTMap(idk, val);
        } else if (idk.compareTo((K)newT.key) < 0) {
            newT.left = tput(idk, val, newT.left);
        } else if (idk.compareTo((K)newT.key) > 0) {
            newT.right = tput(idk, val, newT.right);
        } else if (idk.equals(newT.key)) {
            newT.value = val;
            count--;
        }
        return newT;
    }

    @Override
    public V get(K key) {
        BSTMap treeNode = find(key, T);
        if (treeNode == null) {
            return null;
        }
        return (V)treeNode.value;
    }

    @Override
    public boolean containsKey(K key) {
        if (find(key,T) != null) {
            return true;
        }
        return false;
    }

    private BSTMap find(K key, BSTMap currentT) {
        if (currentT == null) {
            return null;
        } else if (key.equals(currentT.key)) {
            return currentT;
        } else if (key.compareTo((K)currentT.key) < 0) {
            return find(key, currentT.left);
        } else {
            return find(key, currentT.right);
        }
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public void clear() {
        T = null;
        count = 0;
    }

    @Override
    public Set<K> keySet() {
        return Set.of();
    }

    @Override
    public V remove(K key) {
        return null;
    }


    @Override
    public Iterator<K> iterator() {
        return null;
    }
}
