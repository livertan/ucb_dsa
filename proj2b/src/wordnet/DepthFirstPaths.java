package wordnet;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
//
public class DepthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private int s;
    //
    public DepthFirstPaths(WordGraph G, int s) {
        int size = G.size();
        marked = new boolean[size];
        edgeTo = new int[size];
        this.s = s;
        dfs(G, s);
    }
    //
    private void dfs(WordGraph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }
    //
    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        List<Integer> path = new ArrayList<>();
        for (int x = v; x != s; x = edgeTo[x]) {
            path.add(x);
        }
        path.add(s);
        Collections.reverse(path);
        return path;
    }
    //
    public boolean hasPathTo(int v) {
        return marked[v];
    }

}
