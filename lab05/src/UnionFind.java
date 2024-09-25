import java.util.ArrayList;

public class UnionFind {
    // TODO: Instance variables
    private int[] ids;
    private int size;

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        // TODO: YOUR CODE HERE
        size = N;
        ids = new int[size];
        for (int i = 0; i < N; i++) {
            ids[i] = - 1;
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        // TODO: YOUR CODE HERE
        return -ids[find(v)];
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        // TODO: YOUR CODE HERE
        if (ids[v] < 0) {
            return -sizeOf(v);
        } else {
            return ids[v];
        }
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO: YOUR CODE HERE
        return find(v1) == find(v2);
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        // TODO: YOUR CODE HERE
        if (v >= 0 && v < size) {
            int root = ids[v] ;
            if (ids[v] <= -1) {
                return v;
            } else {
                while (ids[root] >= 0) {
                    root = ids[root];
                }
                ids[v] = root; //connect the node to the root directly.
                return root;
            }
        } else {
            throw new IllegalArgumentException("the input value not existed in any sets");
        }
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        if (v1 != v2 && find(v1) != find(v2)) {
            int root;
            int size1, size2;
            size1 = sizeOf(find(v1));
            size2 = sizeOf(find(v2));
            if (size1 == size2) {
                if (v1 > v2) {
                    root = find(v1);
                    ids[find(v2)] = root;
                    ids[v2] = root;
                } else {
                    root = find(v2);
                    ids[find(v1)] = root;
                    ids[v1] = root;
                }
                ids[root] = -(size1+size2);
            } else if (size1 > size2) {
                root = find(v1);
                ids[find(v2)] = root;
                ids[v2] = root;
                ids[root] = -(size1+size2);;
            } else {
                root = find(v2);
                ids[find(v1)] = root;
                ids[v1] = root;
                ids[root] = -(size1+size2);
            }
        }
        // TODO: YOUR CODE HERE
    }

}
