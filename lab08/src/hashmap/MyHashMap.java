package hashmap;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

/**
 *  A hash table-backed Map implementation.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    int N = 0;
    int M = 16;
    double LF = 1.0;

    private Collection<Node>[] buckets;
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        if (1.0 * N / M < LF) {
            buckets = new Collection[M];
        } else {
            buckets = new Collection[(int)(2 * M)];
        }
    }

    public MyHashMap(int initialCapacity) {
        buckets = new Collection[initialCapacity];
    }

    /**
     * MyHashMap constructor that creates a backing array of initialCapacity.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialCapacity initial size of backing array
     * @param loadFactor maximum load factor
     */
    public MyHashMap(int initialCapacity, double loadFactor) {
        M = initialCapacity;
        if (N / M <= loadFactor) {
            buckets = new Collection[M];
        } else {
            buckets = new Collection[2 * M];
        }
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *  Note that that this is referring to the hash table bucket itself,
     *  not the hash map itself.
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        // TODO: Fill in this method.
        return new LinkedList<>();
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!
    @Override
    public void put(K key, V value) {
        int keyHash = key.hashCode();
        int i = Math.floorMod(keyHash, M);
        Node newNode = new Node(key, value);
        if (buckets[i] == null) {
            buckets[i] = this.createBucket();
        } else {
            for (Node node : buckets[i]) {
                if (node.key.equals(key)) {
                    node.value = value;
                    return;
                }
            }
        }
        buckets[i].add(newNode);
        N = N + 1;
    }

    @Override
    public V get(K key) {
        int keyHash = key.hashCode();
        int i = Math.floorMod(keyHash, M);
        if (buckets[i] == null) {
            return null;
        }
        for (Node node : buckets[i]) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        int keyHash = key.hashCode();
        int i = Math.floorMod(keyHash, M);
        if (buckets[i] == null) {
            return false;
        }
        for (Node node : buckets[i]) {
            if (node.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return N;
    }

    @Override
    public void clear() {
        for (int i = 0; i < M; i++) {
            buckets[i].clear();
        }
        N = 0;
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
