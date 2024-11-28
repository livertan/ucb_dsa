package main;
import browser.NgordnetQuery;
import browser.NgordnetQueryHandler;
import ngrams.NGramMap;
import ngrams.TimeSeries;
import wordnet.WordGraph;

import java.util.*;

public class HyponymsHandler extends NgordnetQueryHandler {
    WordGraph wordnet;
    NGramMap history;
    //
    public HyponymsHandler (NGramMap map, WordGraph graph) {
        history = map;
        wordnet = graph;
    }
    @Override
    public String handle(NgordnetQuery q) {
        List<String> words = q.words();
        int startYear = q.startYear();
        int endYear = q.endYear();
        int k = q.k();

        TimeSeries word_history;

        List<String> temp = new ArrayList<String>();
        List<String> output = new ArrayList<String>();
        HashMap<String, Double> wordCounts = new HashMap<>();
        double totalCounts = 0;
        String response;

        for (String word : words) {
            temp = (List<String>) wordnet.hyponyms(word);
            if (output.isEmpty()) {
                output = temp;
            } else {
                output.retainAll(temp);
            };
        }
        //
        for (String word : output) {
            word_history = history.countHistory(word, startYear, endYear);
            for (double count : word_history.data()) {
                totalCounts = totalCounts + count;
            }
            wordCounts.put(word, totalCounts);
            totalCounts = 0;
        }
        //
        Map<String, Double> hm1 = sortByValue(wordCounts);
        //
        int i = 1;
        response = "[";
        for (String word : hm1.keySet()) {
            if (i < k) {
                response = response + word + ", ";
                i++;
            } else if (i == k){
                response = response + word + "]";
                i++;
            } else {
                break;
            }
        }
        return response;
    }

    //
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> entries = new ArrayList<>(map.entrySet());
        entries.sort(Map.Entry.comparingByValue());

        Map<K, V> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : entries) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }
    //
}
