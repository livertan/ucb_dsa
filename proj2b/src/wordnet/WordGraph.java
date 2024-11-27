package wordnet;

import java.util.*;

import edu.princeton.cs.algs4.In;

public class WordGraph {
    Map<Integer, List<String>> synsets = new HashMap<>();
    private int V, E;
    private List<Integer>[] adj;
    //
    //
    public WordGraph(String synsetsFilename, String hyponymsFilename) {
        In in = new In(synsetsFilename);
        List temp = new ArrayList();
        int NoOfWord = 0, NoOfEdge = 0;
        while (!in.isEmpty() && in.hasNextLine()) {
            String nextLine = in.readLine();
            String[] splitLine = nextLine.split(",");
            if (synsets.containsKey(splitLine[0])) {
                temp.addAll(synsets.get(splitLine[0]));
                Collections.addAll(temp, splitLine[1].split("\t"));
                synsets.put(Integer.parseInt(splitLine[0]),temp);
                temp = new ArrayList();
            } else {
                Collections.addAll(temp, splitLine[1].split("\t"));
                synsets.put(Integer.parseInt(splitLine[0]),temp);
                temp = new ArrayList();
            }
            NoOfWord++;
        }
        in.close();
        //
        this.V = NoOfWord;
        adj = (List<Integer>[]) new ArrayList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<Integer>();
        }
        //
        in = new In(hyponymsFilename);
        while (!in.isEmpty() && in.hasNextLine()) {
            String nextLine = in.readLine();
            String[] splitLine = nextLine.split(",");
            for (int i = 0; i < splitLine.length - 1; i++) {
                addEdge(Integer.parseInt(splitLine[0]), Integer.parseInt(splitLine[i + 1]));
                NoOfEdge++;
            }
        }
        this.E = NoOfEdge;
    }
    //
    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }
    //
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
    //
    public int size() {
        return V;
    }
    //
    public Iterable<String> hyponyms(String word) {
        List<String> hypoList = new ArrayList<>();
        List<Integer> keys = new ArrayList();
        List<Integer> hypoKeys = new ArrayList<>();
        keys = (getKeyByValue(this.synsets, word));
        for (int key : keys) {
            hypoKeys.addAll(adj[key]);
        }
        for (int key: hypoKeys) {
            hypoList.addAll(this.synsets.get(key));
        }
        return hypoList;
    }
    //
    public static <K, V> List<K> getKeyByValue(Map<Integer, List<String>> map, String value) {
        List<K> keys = new ArrayList<>();
        List<String> words;
        for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
            words = entry.getValue();
            if (words.get(0).equals(value)) {
                keys.add((K) entry.getKey());
            }
        }
        return keys;
    }
    //
}
