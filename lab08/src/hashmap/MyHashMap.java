package hashmap;

import java.util.*;

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
    double LF = 0.75;

    private Collection<Node>[] buckets;
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        buckets = new Collection[M];
    }

    public void resize() {
        Collection<Node>[] temp;
        if (1.0 * N / M > LF) {
            M = 2 * M;
            temp = new Collection[M];
            for (Collection<Node> bucket : buckets) {
                if (bucket == null) {
                    continue;
                }
                for (Node node : bucket) {
                    int i = Math.floorMod(node.key.hashCode(), M);
                    if (temp[i] == null) {
                        temp[i] = this.createBucket();
                    }
                    temp[i].add(node);
                }
            }
            buckets = temp;
        }
    }

    public MyHashMap(int initialCapacity) {
        M = initialCapacity;
        buckets = new Collection[M];
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
        LF = loadFactor;
        buckets = new Collection[M];
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
        this.resize();
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
            if (buckets[i] == null) {
                continue;
            }
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
